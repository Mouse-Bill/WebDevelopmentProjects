<%--
  Created by IntelliJ IDEA.
  User: bill
  Date: 2023/4/29
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forward Test JSP</title>
</head>
<body>
    <%
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
    %>
    <h1>Forward Test JSP</h1>
    <p>This page was showed through forward.</p>
    <p>param1: <%= param1 %></p>
    <p>param2: <%= param2 %></p>
</body>
</html>
