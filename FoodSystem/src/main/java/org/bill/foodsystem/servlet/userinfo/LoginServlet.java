package org.bill.foodsystem.servlet.userinfo;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.bill.foodsystem.entity.Userinfo;
import org.bill.foodsystem.service.UserinfoService;
import org.bill.foodsystem.service.impl.UserinfoServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/userinfo/login")
public class LoginServlet extends HttpServlet {
    private UserinfoService userinfoService = new UserinfoServiceImpl();
    private Userinfo userinfo = null;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");


        String reqBody = request.getReader().readLine();
        if (reqBody != null) {
            userinfo = new Gson().fromJson(reqBody, Userinfo.class);
        }else {
            String username = request.getParameter("username");
            String passwd = request.getParameter("passwd");
            userinfo = new Userinfo(null, username, passwd, null);
        }

        Userinfo user = userinfoService.login(userinfo);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("isOK", false);

        if (user != null) {
            map.put("isOK", true);
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);
            response.addHeader("token", session.getId());
        }

        response.getWriter().write(new Gson().toJson(map));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}

