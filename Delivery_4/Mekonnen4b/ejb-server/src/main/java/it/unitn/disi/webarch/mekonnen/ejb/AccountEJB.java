package it.unitn.disi.webarch.mekonnen.ejb;

import it.unitn.disi.webarch.mekonnen.persistence.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Ephrem
 */
@Stateless
@Remote(Account.class)
@TransactionManagement(value = TransactionManagementType.BEAN)
public class AccountEJB implements Account {

    @Resource
    private UserTransaction userTransaction;
    @PersistenceContext(unitName = "bankPU")
    EntityManager em;

    @Override
    public float getMoney(int account_number) {
        User account = em.find(User.class, account_number);
        if (account != null) {
            return account.getBalance();

        }
        return 0;

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public boolean deposit(int account_number, float amount) {
        boolean is_deposited = false;
        try {
            userTransaction.begin();
            User account = em.find(User.class, account_number);

            if (account != null) {

                float money = account.getBalance();
                float newAmount = money + amount;
                account.setBalance(newAmount);
                em.persist(account);
                System.out.println("Money deposit. total is " + money);
                userTransaction.commit();
                is_deposited = true;
            } else {
                userTransaction.commit();
            }

        } catch (IllegalStateException ex) {
            try {
                userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(AccountEJB.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException ex) {
            Logger.getLogger(AccountEJB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return is_deposited;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public int withdraw(int account_number, float amount) {
        int is_withdrawn = 0;
        try {
            userTransaction.begin();
            User account = em.find(User.class, account_number);
            if (account != null) {

                float balance = account.getBalance();
                float newAmount = balance - amount;
                if (newAmount < 0) {

                    is_withdrawn = -1; //throw new InsufficientFundsException("Unsufficient funds for account! ");
                    userTransaction.commit();
                } else {

                    account.setBalance(newAmount);
                    em.persist(account);
                    userTransaction.commit();
                    is_withdrawn = 1;
                    System.out.println("Money withdrawal. total is " + account.getBalance());
                }
            } else {
                userTransaction.commit();
            }
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException notSupportedException) {
            try {
                userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                Logger.getLogger(AccountEJB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return is_withdrawn;
    }

    @Override
    public boolean createAccount(int id, int amount, String ownerName) {
        boolean is_created = false;
        User account = em.find(User.class, id);
        if (account != null) {
            account.setBalance(amount);
            account.setAccountNumber(id);
            account.setOwnerName(ownerName);
            em.persist(account);
            is_created = true;
        }
        return is_created;
    }

}
