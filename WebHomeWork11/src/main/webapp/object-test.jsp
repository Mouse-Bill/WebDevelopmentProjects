<%--
  Created by IntelliJ IDEA.
  User: bill
  Date: 2023/5/20
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="jakarta.servlet.*" %>
<%@ page import="jakarta.servlet.http.*" %>
<%@ page import="java.util.Map" %>

<html>
<head>
    <title>Verify JSP Embedded Object</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="font-family: sans-serif">

<%
    // Request verify
    Map<String, String[]> requestMap = request.getParameterMap();
    out.println("<h1>Request verify：</h1>");
    for (String key : requestMap.keySet()) {
        String[] values = requestMap.get(key);
        for (String value : values) {
            out.println("Request Parameters " + key + " ：" + value + "<br>");
        }
    }

    // Response verify
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter outResponse = response.getWriter();
    outResponse.println("<h1>Response verify：</h1>");
    outResponse.println("The Content-Type is "+ response.getContentType()+"<br><br>");

    // Session verify
    session.setAttribute("name", "John");
    String sessionName = (String) session.getAttribute("name");
    out.println("<h1>Session verify：</h1>");
    out.println("Session -> id ：" + session.getId() + "<br>");
    out.println("Session -> name ：" + sessionName + "<br><br>");

    // Application verify
    application.setAttribute("count", 100);
    int count = (int) application.getAttribute("count");
    out.println("<h1>Application verify：</h1>");
    out.println("Application -> count ：" + count + "<br><br>");

    // Out verify
    out.println("<h1>Out verify：</h1>");
    out.println("Out can print text line to HTML DOM <br><br>");

    // PageContext verify
    String pageName = pageContext.getServletConfig().getServletName();
    out.println("<h1>PageContext verify：</h1>");
    out.println("The current JSP‘s Servlet name：" + pageName + "<br><br>");

    // Config verify
    String configValue = config.getInitParameter("configParam");
    out.println("<h1>Config verify：</h1>");
    out.println("ConfigParam：" + configValue + "<br><br>");

    // Page verify
    out.println("<h1>Page verify：</h1>");
    out.println("Current Page Class：" + page.getClass().getName() + "<br><br>");

    // exception verify
    try {
        int divideByZero = 10 / 0;
    } catch (Exception e) {
        out.println("<h1>Exception verify：</h1>");
        out.println("Catch Exception：" + e.toString() + "<br><br>");
    }
%>

</body>
</html>
