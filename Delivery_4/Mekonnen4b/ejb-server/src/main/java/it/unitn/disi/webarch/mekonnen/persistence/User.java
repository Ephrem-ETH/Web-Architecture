/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.mekonnen.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ephrem
 */
@Entity
@Table(name="Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="accountNumber")
    private int accountNumber;
    @Column(name="ownerName")
    private String ownerName;
     @Column(name="balance")
    private float balance;

    public User() {
    }

     public User(int accountNumber,String ownerName,float balance){
         this.accountNumber=accountNumber;
         this.ownerName = ownerName;
         this.balance = balance;
     }
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
   
}
