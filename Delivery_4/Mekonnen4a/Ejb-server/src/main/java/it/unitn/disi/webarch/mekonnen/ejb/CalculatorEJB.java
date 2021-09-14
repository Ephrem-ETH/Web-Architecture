package it.unitn.disi.webarch.mekonnen.ejb;

import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.Remote;

/**
 *
 * @author ephrem
 */
@Stateful
@Remote(Calculator.class)
public class CalculatorEJB implements Calculator {

    float interest = 5;

    @Override
    public float calculateInterest(long money) {

        return money * (1 + (interest / 100));

    }
}
