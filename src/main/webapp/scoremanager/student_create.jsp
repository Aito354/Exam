<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生登録</title>
</head>
<body>

<h2>学生登録</h2>

<!-- エラーメッセージ -->
<c:if test="${errors != null}">
    <ul style="color:red;">
        <c:forEach var="e" items="${errors}">
            <li>${e}</li>
        </c:forEach>
    </ul>
</c:if>

<br>

<form action="scoremanager.main.StudentCreateExecute.action" method="post">

    <!-- 学生番号 -->
    <div>
        学生番号：
        <input type="text" name="no" value="${no}">
    </div>

    <br>

    <!-- 氏名 -->
    <div>
        氏名：
        <input type="text" name="name" value="${name}">
    </div>

    <br>

    <!-- 入学年度 -->
    <div>
        入学年度：
        <input type="text" name="entYear" value="${entYear}">
    </div>

    <br>

    <!-- クラス -->
    <div>
        クラス：
        <select name="classNum">
            <c:forEach var="c" items="${classList}">
                <option value="${c}"
                    <c:if test="${c == classNum}">selected</c:if>>
                    ${c}
                </option>
            </c:forEach>
        </select>
    </div>

    <br>

    <!-- 登録ボタン -->
    <div>
        <input type="submit" value="登録して終了">
    </div>

</form>

<br>

<!-- 戻る -->
<a href="/exam/scoremanager.main.StudentList.action">
    一覧に戻る
</a>

</body>
</html>