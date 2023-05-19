package org.bill.webhomework10;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "adminPageServlet", value = "/admin/adminPage")
public class AdminPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        HttpSession session = req.getSession(false);
        resp.getWriter().println("<!DOCTYPE html>");
        resp.getWriter().println("<html lang=\"en\">");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<meta charset=\"UTF-8\">");
        resp.getWriter().println("<title>Admin Page</title>");
        resp.getWriter().println("</head><body style=\"background-color: #f0f0f0; font-family: sans-serif;\">");
        resp.getWriter().println("<h1>Admin Page</h1>");
        resp.getWriter().println("<h2>Welcome，" + session.getAttribute("username") + "</h2>");
        resp.getWriter().println("</body></html>");
    }
}
