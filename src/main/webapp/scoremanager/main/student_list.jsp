<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>学生管理</title>
</head>
<body>

<h2>学生管理</h2>

<!-- 絞り込みフォーム -->
<form action="StudentList.action" method="get">

    入学年度
    <select name="entYear">
        <option value="">--------</option>
        <c:forEach var="year" items="${entYearList}">
            <option value="${year}"
                <c:if test="${param.entYear == year.toString()}">selected</c:if>>
                ${year}
            </option>
        </c:forEach>
    </select>

    クラス
    <select name="classNum">
        <option value="">--------</option>
        <c:forEach var="c" items="${classNumList}">
            <option value="${c}"
                <c:if test="${param.classNum == c}">selected</c:if>>
                ${c}
            </option>
        </c:forEach>
    </select>

    在学中
    <input type="checkbox" name="isAttend" value="true"
        <c:if test="${param.isAttend != null}">checked</c:if> />

    <input type="submit" value="絞込み" />

    <a href="StudentCreate.action">新規登録</a>
</form>

<hr>

<!-- 件数表示 -->
<c:choose>
    <c:when test="${empty studentList}">
        <p>学生情報が存在しませんでした。</p>
    </c:when>
    <c:otherwise>
        <p>検索結果：${studentList.size()}件</p>

        <!-- 一覧表 -->
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>入学年度</th>
                <th>学生番号</th>
                <th>氏名</th>
                <th>クラス</th>
                <th>在学中</th>
                <th></th>
            </tr>

            <c:forEach var="student" items="${studentList}">
                <tr>
                    <td>${student.entYear}</td>
                    <td>${student.no}</td>
                    <td>${student.name}</td>
                    <td>${student.classNum}</td>
                    <td>
                        <c:choose>
                            <c:when test="${student.attend}">○</c:when>
                            <c:otherwise>×</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="StudentUpdate.action?no=${student.no}">
                            変更
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>