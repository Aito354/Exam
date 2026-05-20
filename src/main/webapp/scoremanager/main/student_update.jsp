<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報変更</title>
</head>
<body>

<h2>学生情報変更</h2>

<form action="StudentUpdateExecute.action" method="post">

    <!-- 学生番号は変更しないため hidden で送信 -->
    <input type="hidden" name="no" value="${student.no}">

    <table border="1" cellpadding="5">
        <tr>
            <th>学生番号</th>
            <td>${student.no}</td>
        </tr>

        <tr>
            <th>氏名</th>
            <td>
                <input type="text" name="name" value="${student.name}">
            </td>
        </tr>

        <tr>
            <th>入学年度</th>
            <td>
                <input type="text" name="entYear" value="${student.entYear}">
            </td>
        </tr>

        <tr>
            <th>クラス</th>
            <td>
                <input type="text" name="classNum" value="${student.classNum}">
            </td>
        </tr>

        <tr>
            <th>在学中</th>
            <td>
                <input type="checkbox" name="isAttend" value="true"
                    ${student.attend ? "checked" : ""}>
            </td>
        </tr>
    </table>

    <br>

    <input type="submit" value="変更">

    <a href="StudentList.action">戻る</a>

</form>

</body>
</html>