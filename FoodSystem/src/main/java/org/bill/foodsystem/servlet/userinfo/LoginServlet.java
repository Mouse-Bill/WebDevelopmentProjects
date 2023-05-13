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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/userinfo/login")
public class LoginServlet extends HttpServlet {
    private UserinfoService userinfoService = new UserinfoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");

        Userinfo user = userinfoService.login(new Userinfo(null, username, passwd, null));

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("isOK", false);

        if (user != null) {
            map.put("isOK", true);
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", user);
        }

        response.getWriter().write(new Gson().toJson(map));
    }

}

