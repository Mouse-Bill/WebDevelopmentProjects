package org.bill.webhomework08;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletContextTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String initParam = context.getInitParameter("initParamTest");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body style=\"font-family: sans-serif\">");
        out.println("<h1>ServletContextTest</h1>");
        out.println("<p>initParamTest: " + initParam + "</p>");
        out.println("</body></html>");

    }
}
