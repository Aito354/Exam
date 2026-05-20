
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生別成績一覧</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
}

body{
    font-family:sans-serif;
    background-color:#f5f5f5;
}

/* ヘッダー */
.header{
    background-color:#dfe8f3;
    height:70px;
    display:flex;
    align-items:center;
    padding-left:30px;
    font-size:32px;
    font-weight:bold;
}

/* 全体 */
.container{
    display:flex;
    min-height:100vh;
}

/* サイドバー */
.sidebar{
    width:200px;
    background-color:#ffffff;
    padding:20px;
    border-right:1px solid #cccccc;
}

.sidebar a{
    display:block;
    margin-bottom:15px;
    color:#4a6ee0;
    text-decoration:none;
}

.sidebar a:hover{
    text-decoration:underline;
}

/* メイン */
.main{
    flex:1;
    padding:20px;
}

/* タイトル */
.page-title{
    background-color:#eeeeee;
    padding:15px;
    font-size:24px;
    font-weight:bold;
    margin-bottom:20px;
}

/* 検索エリア */
.search-area{
    background-color:#ffffff;
    padding:20px;
    border:1px solid #dddddd;
    margin-bottom:20px;
}

.row{
    display:flex;
    flex-wrap:wrap;
    gap:10px;
    align-items:center;
}

/* フォーム */
input[type="text"],
select{
    padding:5px;
}

input[type="submit"]{
    padding:6px 15px;
    background-color:#4a6ee0;
    color:white;
    border:none;
    cursor:pointer;
}

input[type="submit"]:hover{
    background-color:#3451aa;
}

/* テーブル */
table{
    width:100%;
    border-collapse:collapse;
    background-color:#ffffff;
}

th{
    background-color:#eeeeee;
    border:1px solid #cccccc;
    padding:10px;
}

td{
    border:1px solid #cccccc;
    padding:10px;
    text-align:center;
}

.student-info{
    margin-bottom:10px;
    font-weight:bold;
}

/* エラー */
.error{
    color:red;
    margin-top:10px;
    font-weight:bold;
}

</style>

</head>

<body>

<!-- ヘッダー -->
<div class="header">
    得点管理システム
</div>

<div class="container">

    <!-- サイドバー -->
    <div class="sidebar">

        <a href="<c:url value='Menu.action' />">メニュー</a>

        <a href="<c:url value='StudentList.action' />">学生管理</a>

        <b>成績管理</b>

        <a href="<c:url value='TestRegist.action' />">成績登録</a>

        <a href="<c:url value='TestList.action' />">成績参照</a>

        <a href="<c:url value='SubjectList.action' />">科目管理</a>

    </div>

    <!-- メイン -->
    <div class="main">

        <div class="page-title">
            学生別成績一覧
        </div>

        <!-- 検索エリア -->
        <div class="search-area">

            <form action="TestList.action" method="post">

                <div class="row">

                    学生番号
                    <input type="text" name="no" value="${param.no}">

                    入学年度
                    <select name="entYear">
                        <option value="">--------</option>

                        <option value="2024"
                            <c:if test="${param.entYear == '2024'}">
                                selected
                            </c:if>>
                            2024
                        </option>

                        <option value="2025"
                            <c:if test="${param.entYear == '2025'}">
                                selected
                            </c:if>>
                            2025
                        </option>
                    </select>

                    クラス
                    <select name="classNum">
                        <option value="">--------</option>

                        <option value="1"
                            <c:if test="${param.classNum == '1'}">
                                selected
                            </c:if>>
                            1
                        </option>

                        <option value="2"
                            <c:if test="${param.classNum == '2'}">
                                selected
                            </c:if>>
                            2
                        </option>
                    </select>

                    科目
                    <select name="subjectCd">
                        <option value="">--------</option>

                        <option value="JAVA"
                            <c:if test="${param.subjectCd == 'JAVA'}">
                                selected
                            </c:if>>
                            JAVA
                        </option>

                        <option value="DB"
                            <c:if test="${param.subjectCd == 'DB'}">
                                selected
                            </c:if>>
                            DB
                        </option>
                    </select>

                    回数
                    <select name="num">
                        <option value="">--------</option>

                        <option value="1"
                            <c:if test="${param.num == '1'}">
                                selected
                            </c:if>>
                            1
                        </option>

                        <option value="2"
                            <c:if test="${param.num == '2'}">
                                selected
                            </c:if>>
                            2
                        </option>
                    </select>

                    <input type="submit" value="検索">

                </div>

            </form>

            <!-- エラー -->
            <c:if test="${not empty error}">
                <div class="error">
                    ${error}
                </div>
            </c:if>

        </div>

        <!-- 学生情報 -->
        <c:if test="${not empty list}">

            <div class="student-info">
                学生番号：${param.no}
            </div>

            <!-- 一覧 -->
            <table>

                <tr>
                    <th>科目名</th>
                    <th>科目コード</th>
                    <th>回数</th>
                    <th>点数</th>
                </tr>

                <c:forEach var="obj" items="${list}">

                    <tr>
                        <td>${obj.subjectName}</td>
                        <td>${obj.subjectCd}</td>
                        <td>${obj.num}</td>
                        <td>${obj.point}</td>
                    </tr>

                </c:forEach>

            </table>

        </c:if>

    </div>

</div>

</body>
</html>