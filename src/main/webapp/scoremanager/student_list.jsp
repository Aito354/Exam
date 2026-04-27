<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生一覧</title>
</head>
<body>

<h2>学生一覧</h2>

<!-- 新規登録 -->
<div style="text-align:right;">
    <a href="/exam/scoremanager.main.StudentCreate.action">
        新規登録
    </a>
</div>

<br>

<table border="1">
    <tr>
        <th>学籍番号</th>
        <th>氏名</th>
        <th>入学年度</th>
        <th>クラス</th>
    </tr>

    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.no}</td>
            <td>${s.name}</td>
            <td>${s.entYear}</td>
            <td>${s.classNum}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>