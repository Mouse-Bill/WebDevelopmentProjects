package org.bill.webhomework01;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.*;

public class MyResume extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>个人简历</title>\n");
        out.println("<style>.attribute {\n" +
                "      display: inline-block;\n" +
                "      width: 75px;\n" +
                "      text-align: justify;\n" +
                "      text-align-last:justify;\n" +
                "      text-justify: distribute-all-lines;\n" +
                "    }\n" +
                "  </style>");
        out.println("</head>");
        out.println("<body style=\"background-color: #f0f0f0; font-family: sans-serif;\">");
        out.println("  <h1>个人简历</h1>\n");
        out.println("  <p><strong><span class=\"attribute\">姓名</span>: </strong> 罗皓年</p>\n" +
                "  <p><strong><span class=\"attribute\">性别</span>: </strong>男</p>\n" +
                "  <p><strong><span class=\"attribute\">年龄</span>: </strong>20 </p>\n" +
                "  <p><strong><span class=\"attribute\">就读学校</span>: </strong>东北大学 </p>\n" +
                "  <p><strong><span class=\"attribute\">就读学院</span>: </strong>软件学院</p>\n" +
                "  <p><strong><span class=\"attribute\">专业</span>: </strong>软件工程 </p>\n" +
                "  <p><strong><span class=\"attribute\">学号</span>: </strong>20216910 </p>\n" +
                "  <p><strong><span class=\"attribute\">掌握技能</span>: </strong> </p>\n");
        out.println("<ul>\n" +
                "    <li>HTML</li>\n" +
                "    <li>CSS</li>\n" +
                "    <li>C</li>\n" +
                "    <li>Python</li>\n" +
                "    <li>Java</li>\n" +
                "    <li>Adobe Photoshop</li>\n" +
                "    <li>Adobe Premiere Pro</li>\n" +
                "  </ul>\n");
        out.println("</body>");
        out.println("</html>");
    }

    public void destroy() {
    }

}
