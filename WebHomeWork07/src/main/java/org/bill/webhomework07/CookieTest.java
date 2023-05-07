package org.bill.webhomework07;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body style=\"background-color: #f0f0f0; font-family: sans-serif;\">");
        out.println("<h1>Cookie Test</h1>");
        Cookie[] cookies = req.getCookies();
        boolean cookieFound = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                out.println("<p>" + cookie.getName() + " : " + cookie.getValue() + "</p>");
                if (cookie.getName().equals("test_cookie")) {
                    cookieFound = true;
                }
            }
        }
        if (!cookieFound) {
            Cookie cookie = new Cookie("test_cookie", "test_value");
            cookie.setMaxAge(60 * 60);
            resp.addCookie(cookie);
            out.println("<p>Cookie created.</p>");
            out.println("<p>Reload the page to see the cookie.</p>");
        }
        out.println("</body></html>");
    }
}
