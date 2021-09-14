/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.mekonnen.ejb;

import javax.ejb.Stateless;
import javax.ejb.Remote;

/**
 *
 * @author Ephrem
 */
@Stateless
@Remote(Calculator.class)
public class CalculatorEJB implements Calculator {
   float interest=5;
    @Override
    public float calculateIntrest(long money) {
      return money *(1+(interest/100));
    }

    
}
