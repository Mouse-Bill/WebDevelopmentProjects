package org.bill.webhomework05;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String passwd = req.getParameter("password");
        //不区分大小写比较字符串
        if (username.equalsIgnoreCase("admin") && passwd.equalsIgnoreCase("admin")) {
            resp.sendRedirect("welcome.html");
        } else {
            resp.sendRedirect("erroruser.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
