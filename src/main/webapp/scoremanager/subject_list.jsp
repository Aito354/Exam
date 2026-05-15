<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="bean.Subject" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目管理</title>

<style>

table{
    border-collapse: collapse;
    width: 500px;
}

th,td{
    border:1px solid #cccccc;
    padding:8px;
}

</style>

</head>
<body>

<h2>科目管理</h2>

<a href="subject_create.jsp">新規登録</a>

<br><br>

<table>

<tr>
    <th>科目コード</th>
    <th>科目名</th>
    <th></th>
    <th></th>
</tr>

<%
List<Subject> list =
(List<Subject>)request.getAttribute("list");

if(list != null){

    for(Subject s : list){
%>

<tr>
    <td><%= s.getCd() %></td>
    <td><%= s.getName() %></td>

    <td>
        <a href="SubjectUpdate.action?cd=<%= s.getCd() %>">
    変更
</a>
    </td>

    <td>
        <a href="SubjectDelete.action?cd=<%= s.getCd() %>">
    削除
</a>
    </td>
</tr>

<%
    }
}
%>

</table>

</body>
</html>