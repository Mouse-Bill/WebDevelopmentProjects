package org.bill.webhomework09;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class CountTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ServletContext app = req.getServletContext();
        resp.setContentType("text/html");
        resp.getWriter().println("<html><body style = \"font-family: sans-serif\">");
        resp.getWriter().println("<h1>Count: " + app.getAttribute("count") + "</h1>");
        resp.getWriter().println("<h2>New session: "+ session.isNew()+"</h2>");
    }
}
