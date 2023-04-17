package org.bill.webhomework03;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class InitTest02 extends HttpServlet {
    private String message;

    public void init() {

        // Get the value of the initialization parameter
        message = getServletConfig().getInitParameter("msg02");
        System.out.println("Servlet init() called");
        System.out.println("InitTest02 loaded");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
