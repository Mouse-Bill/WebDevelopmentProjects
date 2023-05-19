package org.bill.webhomework10;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private List<Userinfo> userinfos = new ArrayList<Userinfo>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userinfos.add(new Userinfo("admin", "admin", "admin"));
        userinfos.add(new Userinfo("user1", "user1", "user"));
        userinfos.add(new Userinfo("user2", "user2", "user"));

        resp.setContentType("Application/json;charset=utf-8");
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        for (Userinfo userinfo : userinfos) {
            if (userinfo.getUsername().equals(username) && userinfo.getPassword().equals(password)) {
                resp.getWriter().println("{\"status\": \"success\", \"role\": \"" + userinfo.getRole() + "\"}");
                session.setAttribute("username", username);
                session.setAttribute("role", userinfo.getRole());
                resp.sendRedirect("admin/adminPage");
                return;
            }
        }
        resp.getWriter().println("{\"status\": \"fail\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
