package org.bill.webhomework02;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get parameters
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String[] hobby = req.getParameterValues("hobby");
        String school = req.getParameter("school");

        // Respond to the client
        resp.setContentType("text/html");
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<div>Username: " + username + "</div>");
        resp.getWriter().println("<div>Password: " + password + "</div>");
        resp.getWriter().println("<div>Sex: " + sex + "</div>");
        resp.getWriter().println("<div>Hobby: " + String.join(", ", hobby) + "</div>");
        resp.getWriter().println("<div>School: " + school + "</div>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
