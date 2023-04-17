package org.bill.webhomework03;

import java.io.*;

import jakarta.servlet.http.*;

public class InitTest01 extends HttpServlet {
    private String message;

    public void init() {

        // Get the value of the initialization parameter
        message = getServletConfig().getInitParameter("msg01");
        System.out.println("Servlet init() called");
        System.out.println("InitTest01 loaded");

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