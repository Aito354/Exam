<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目登録</title>
</head>
<body>

<h2>科目登録</h2>

<form action="SubjectCreateExecute.action" method="post">

    科目コード<br>
    <input type="text" name="cd">

    <br><br>

    科目名<br>
    <input type="text" name="name">

    <br><br>

    <input type="submit" value="登録">

</form>

</body>
</html>