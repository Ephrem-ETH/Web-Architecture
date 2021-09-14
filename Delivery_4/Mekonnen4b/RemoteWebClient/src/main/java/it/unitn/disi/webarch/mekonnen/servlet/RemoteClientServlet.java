package it.unitn.disi.webarch.mekonnen.servlet;

import it.unitn.disi.webarch.mekonnen.ejb.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ephrem
 */
public class RemoteClientServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            // PrintWriter out = response.getWriter();
            Account account = lookupAccountEJB();
            PrintWriter out = response.getWriter();
            if (request.getParameter("btn_deposit") != null) {
                RequestDispatcher req = request.getRequestDispatcher("/depositPage.html");
                req.forward(request, response);
            } else if (request.getParameter("btn_withdraw") != null) {
                RequestDispatcher req = request.getRequestDispatcher("/withdrawPage.html");
                req.forward(request, response);
            } else if (request.getParameter("btn_see") != null) {
                String current_balance = String.valueOf(account.getMoney(1));
                
                out.println( " <font color='green'><b>Your total balance is :"+String.valueOf(account.getMoney(1)+"</b></font>"));
                RequestDispatcher req = request.getRequestDispatcher("/index.html");
                req.include(request, response);
                
                
                
            }

        } catch (NamingException ex) {
            Logger.getLogger(RemoteClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static Account lookupAccountEJB() throws NamingException {

        final Hashtable jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        final Context context = new InitialContext(jndiProperties);

        return (Account) context
                .lookup("ejb:/ejb-server-1.0/AccountEJB!it.unitn.disi.webarch.mekonnen.ejb.Account");
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

}
