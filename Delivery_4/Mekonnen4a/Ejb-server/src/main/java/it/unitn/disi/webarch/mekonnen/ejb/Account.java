package it.unitn.disi.webarch.mekonnen.ejb;

import it.unitn.disi.webarch.mekonnen.exception.InsufficientFundsException;

/**
 *
 * @author ephrem
 */
public interface Account {

    public void deposit(long amount);

    public void withdraw(long amount) throws InsufficientFundsException;

    public long getMoney();

    public void createAccount(long amount);
}
