 
package it.unitn.disi.webarch.mekonnen.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import it.unitn.disi.webarch.mekonnen.ejb.Account;
import it.unitn.disi.webarch.mekonnen.ejb.Calculator;
import it.unitn.disi.webarch.mekonnen.exception.InsufficientFundsException;



import javax.naming.spi.NamingManager;
public class RemoteEJBClient {

	public static void main(String[] args) throws Exception {
		Calculator calculator = lookupCalculatorEJB();
                 

		Account account = lookupAccountEJB();
		System.out.println("Create Account with 1000$ ");

		account.createAccount(1000l);
		System.out.println("Deposit 250$ ");
		account.deposit(250);

		try {
			System.out.println("Withdraw 500$ ");
			account.withdraw(500);
		} catch (InsufficientFundsException e) {

			e.printStackTrace();
		}
		long money = account.getMoney();
		System.out.println("Money left " + money);
		float totalMoney = calculator.calculateInterest(money);
		System.out.println("Money plus interests " + totalMoney);


	}

	private static Account lookupAccountEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
 
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
                jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		final Context ctx = new InitialContext(jndiProperties);
		return (Account) ctx
				.lookup("ejb:/Ejb-server-1.0/AccountEJB!it.unitn.disi.webarch.mekonnen.ejb.Account?stateful");
	}

	private static Calculator lookupCalculatorEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
                jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (Calculator) context
				.lookup("ejb:/Ejb-server-1.0/CalculatorEJB!it.unitn.disi.webarch.mekonnen.ejb.Calculator?stateful");
	}
}
