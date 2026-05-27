<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="bean.Subject" %>

<%
Subject subject =
(Subject)request.getAttribute("subject");

String error =
(String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目変更</title>
</head>
<body>

<h2>科目変更</h2>

<% if(error != null){ %>
    <p style="color:red;">
        <%= error %>
    </p>
<% } %>

<form action="SubjectUpdateExecute.action" method="post">

    <input type="hidden"
           name="cd"
           value="<%= subject.getCd() %>">

    科目コード<br>
    <%= subject.getCd() %>

    <br><br>

    科目名<br>

    <input type="text"
           name="name"
           value="<%= subject.getName() %>">

    <br><br>

    <input type="submit" value="変更">

</form>

</body>
</html>