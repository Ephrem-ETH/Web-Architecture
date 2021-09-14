
package it.unitn.disi.webarch.mekonnen.ejb;

import javax.ejb.Remote;

/**
 *
 * @author Ephrem
 */
@Remote
public interface Account {

    public boolean deposit(int id, float money);

    public int withdraw(int id, float money);

    public float getMoney(int id);

    public boolean createAccount(int id, int balance, String ownerName);

}
