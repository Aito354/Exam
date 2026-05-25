<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目登録</title>
</head>
<body>

<h2>科目登録</h2>

<%
List<String> errors =
    (List<String>)request.getAttribute("errors");

String cd =
    (String)request.getAttribute("cd");

String name =
    (String)request.getAttribute("name");

if(cd == null) cd = "";
if(name == null) name = "";
%>

<!-- エラー表示 -->
<% if(errors != null && !errors.isEmpty()){ %>

    <div style="color:red;">

        <% for(String error : errors){ %>

            <p><%= error %></p>

        <% } %>

    </div>

<% } %>

<form action="SubjectCreateExecute.action"
      method="post">

    科目コード<br>

    <input type="text"
           name="cd"
           value="<%= cd %>">

    <br><br>

    科目名<br>

    <input type="text"
           name="name"
           value="<%= name %>">

    <br><br>

    <input type="submit"
           value="登録">

</form>

</body>
</html>