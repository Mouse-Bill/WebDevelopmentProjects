package org.bill.webhomework07;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class SessionTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        boolean sessionFound = false;
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body style=\"background-color: #f0f0f0; font-family: sans-serif;\">");
        out.println("<h1>Session Test</h1>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<p>Session Creation Time: " + session.getCreationTime() + "</p>");
        out.println("<p>Session Last Accessed Time: " + session.getLastAccessedTime() + "</p>");
        out.println("<p>Session Max Inactive Interval: " + session.getMaxInactiveInterval() + "</p>");
        out.println("<p>Session Is New: " + session.isNew() + "</p>");

        for (Iterator<String> it = session.getAttributeNames().asIterator(); it.hasNext(); ) {
            String name = it.next();
            out.println("<p>" + name + " : " + session.getAttribute(name) + "</p>");
            if (name.equals("test_session")){
                sessionFound = true;
            }
        }
        if (!sessionFound){
            session.setAttribute("test_session", "test_value");
            out.println("<p>Session attribute added</p>");
            out.println("<p>Reload the page to see the session attribute.</p>");
        }
        out.println("</body></html>");




    }
}
