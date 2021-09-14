/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.mekonnen.ejb;

import it.unitn.disi.webarch.mekonnen.exception.InsufficientFundsException;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.Remote;

/**
 *
 * @author ephrem
 */
@Stateful
@Remote(Account.class)
public class AccountEJB implements Account {

    long money;

    @Override
    public long getMoney() {
        return money;

    }

    public void createAccount(long amount) {
        this.money = amount;

    }

    @Override
    public void deposit(long amount) {

        this.money += amount;

        System.out.println("Money deposit. total is " + money);
    }

    @Override
    public void withdraw(long amount) throws InsufficientFundsException {

        long newAmount = money - amount;
        if (newAmount < 0) {
            throw new InsufficientFundsException("Unsufficient funds for account! ");
        }

        money = newAmount;
        System.out.println("Money withdrawal. total is " + money);

    }
}
