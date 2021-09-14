package it.unitn.disi.webarch.mekonnen;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ephrem
 */
public class EventServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        org.json.simple.JSONObject obj = new org.json.simple.JSONObject();
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        String mesg = "There is nothing new";
        System.out.println("Entering ..");
        String token = request.getParameter("token");
        //System.out.println("Debugging1" + token);
        List<EventPost> events = EventPost.getEvents();
        if (!events.isEmpty()) {
            //Call the function named maxId to get the maximum id from the list
            int maxId = maxId(events);
            System.out.println("max id:" + maxId);
            
            if (Integer.parseInt(token) >= maxId) {
                obj.put("id", events.get(maxId - 1).getId());
                obj.put("event", mesg);
                response.getWriter().write(obj.toJSONString());

            } else if (maxId > Integer.parseInt(token)) {
                //System.out.println("Debugging2" + token + events.get(maxId - 1).getEvent());
                obj.put("id", events.get(maxId - 1).getId());
                obj.put("event", events.get(maxId - 1).getEvent());
                response.getWriter().write(obj.toJSONString());
            }

        } else {

            obj.put("id", token);
            obj.put("event", mesg);
            response.getWriter().write(obj.toJSONString());
            //response.getWriter().write("There is nothing new");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String liveEvent = request.getParameter("event");
        HttpSession session = request.getSession(false);
        List<EventPost> events = EventPost.getEvents();
        if (session != null) {
            if (!(liveEvent.isEmpty() || liveEvent == null)) {

                int maxId = maxId(events);
                maxId++;

                EventPost.addEvents(new EventPost(maxId, liveEvent));
                out.println("Successfuly Added!" + maxId + liveEvent);
            } else {
                out.println("<font color='red'><b>Field can't be empty </b></font>");
                RequestDispatcher rd = request.getRequestDispatcher("liveEvent.html");
                rd.include(request, response);
            }

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.forward(request, response);
        }
    }

  
    // Function to get the maximum id from the list passed from the caller
    public int maxId(List<EventPost> events) {
        int maxId = 0;
        if (!events.isEmpty()) {
            for (EventPost event : events) {

                if (maxId <= event.getId()) {
                    maxId = event.getId();

                }
            }
        }
        return maxId;
    }

}
