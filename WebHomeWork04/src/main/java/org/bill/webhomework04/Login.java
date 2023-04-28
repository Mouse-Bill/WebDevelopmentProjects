package org.bill.webhomework04;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username"); // 获取用户名
        String password = req.getParameter("password"); // 获取密码
        if (username.equals("bill") && password.equals("123456")) {
            resp.sendRedirect("welcome.jsp"); // 重定向到 welcome.jsp
        } else {
            resp.sendRedirect("login.jsp"); // 重定向到 login.jsp
        }
    }
}
