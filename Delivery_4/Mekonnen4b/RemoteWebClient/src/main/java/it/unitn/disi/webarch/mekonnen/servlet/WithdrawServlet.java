/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.mekonnen.servlet;

import it.unitn.disi.webarch.mekonnen.ejb.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ephrem
 */

public class WithdrawServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (!(request.getParameter("account_number").isEmpty() || request.getParameter("amount").isEmpty())) {
            int account_number = Integer.parseInt(request.getParameter("account_number"));

            int amount = Integer.parseInt(request.getParameter("amount"));
            try {
                response.setContentType("text/html;charset=UTF-8");
                Account account = lookupAccountEJB();
                int result = account.withdraw(account_number, amount);
                switch (result) {
                    case 1:
                        {
                            String current_balance = String.valueOf(account.getMoney(account_number));
                            out.println("<font color='green'><b>The operation was successful! <br/> Thank you for banking with us! </b></font>");
                            out.println("<font color='green'><b>Now, your balance is:" + current_balance + "</b></font>");
                            RequestDispatcher rd = request.getRequestDispatcher("withdrawPage.html");
                            rd.include(request, response);
                            break;
                        }
                    case -1:
                        {
                            out.println("<font color='red'><b>Dear customer you have insufficient balance!</b></font>");
                            RequestDispatcher rd = request.getRequestDispatcher("withdrawPage.html");
                            rd.include(request, response);
                            break;
                        }
                    default:
                        {
                            out.println("<font color='red'><b>Sorry, The transaction was  unsuccessful, your account number may be wrong!</b></font>");
                            RequestDispatcher rd = request.getRequestDispatcher("withdrawPage.html");
                            rd.include(request, response);
                            break;
                        }
                }
            } catch (NamingException ex) {
                Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            out.println("<font color='red'><b>please, input fields can't be empty!</b></font>");

            RequestDispatcher rd = request.getRequestDispatcher("withdrawPage.html");
            rd.include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static Account lookupAccountEJB() throws NamingException {

        final Hashtable jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        final Context context = new InitialContext(jndiProperties);

        return (Account) context
                .lookup("ejb:/ejb-server-1.0/AccountEJB!it.unitn.disi.webarch.mekonnen.ejb.Account");
    }
}
