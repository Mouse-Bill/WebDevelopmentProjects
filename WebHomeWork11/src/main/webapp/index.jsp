<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Test JSP</title>
</head>
<body style="font-family: sans-serif">
<h1><%= "Test JSP" %>
</h1>
<br/>
<a href="test-jsp-object?test=hello">Test JSP Object</a>
<br/>
<p>Static & Dynamic Include Test</p>
<%-- Static Include --%>
<%@ include file="./static_include.jsp" %>
<br/>
<p>
    Static Include = ${st}<br>
</p>
<%-- Dynamic Include --%>
<jsp:include page="./dynamic-include.jsp"/>
<p>
    Dynamic Include = ${dy}<br>
</p>
<br/>
</body>
</html>