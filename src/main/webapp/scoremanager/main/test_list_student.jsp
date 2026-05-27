<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        成績一覧（学生）
    </c:param>

    <c:param name="content">

        <style>

        .area{
            border:1px solid #ddd;
            padding:30px;
            width:900px;
            margin:20px auto;
            background:#fff;
        }

        select,input{
            height:32px;
            padding:4px 8px;
            font-size:14px;
        }

        button{
            height:32px;
            padding:0 15px;
            background:#666;
            color:#fff;
            border:none;
            border-radius:3px;
            cursor:pointer;
        }

        table{
            width:100%;
            border-collapse:collapse;
            font-size:14px;
        }

        th,td{
            border-bottom:1px solid #ddd;
            padding:12px;
            text-align:center;
        }

        th{
            background:#f7f7f7;
        }

        h2{
            margin-bottom:20px;
            font-size:40px;
        }

        .student-name{
            margin:15px 0;
            font-size:18px;
            font-weight:bold;
        }

        .message{
            color:deepskyblue;
            font-size:13px;
            margin-top:20px;
        }

        .search-table td{
            border:none;
            padding:10px;
        }

        hr{
            margin:25px 0;
            border:0;
            border-top:1px solid #ddd;
        }

        </style>

        <h2>成績一覧（学生）</h2>

        <!-- エラー -->
        <c:if test="${not empty error}">
            <p style="color:red; font-size:14px;">
                ${error}
            </p>
        </c:if>

        <!-- メッセージ -->
        <c:if test="${not empty message}">
            <p style="color:deepskyblue; font-size:14px;">
                ${message}
            </p>
        </c:if>

        <!-- 上の検索エリア -->
        <div class="area">

            <!-- 科目情報 -->
            <form action="TestListSubjectExecute.action" method="get">

                <table class="search-table">

                    <tr>

                        <td>
                            科目情報
                        </td>

                        <td>

                            入学年度<br>

                            <select name="f1">

                                <option value="">--------</option>

                                <c:forEach var="y" begin="2020" end="${year}">

                                    <c:choose>

                                        <c:when test="${f1 eq y}">
                                            <option value="${y}" selected>
                                                ${y}
                                            </option>
                                        </c:when>

                                        <c:otherwise>
                                            <option value="${y}">
                                                ${y}
                                            </option>
                                        </c:otherwise>

                                    </c:choose>

                                </c:forEach>

                            </select>

                        </td>

                        <td>

                            クラス<br>

                            <select name="f2">

                                <option value="">--------</option>

                                <c:forEach var="c" items="${classList}">

                                    <c:choose>

                                        <c:when test="${f2 eq c}">
                                            <option value="${c}" selected>
                                                ${c}
                                            </option>
                                        </c:when>

                                        <c:otherwise>
                                            <option value="${c}">
                                                ${c}
                                            </option>
                                        </c:otherwise>

                                    </c:choose>

                                </c:forEach>

                            </select>

                        </td>

                        <td>

                            科目<br>

                            <select name="f3">

                                <option value="">--------</option>

                                <c:forEach var="s" items="${subjectList}">

                                    <c:choose>

                                        <c:when test="${f3 eq s.cd}">
                                            <option value="${s.cd}" selected>
                                                ${s.name}
                                            </option>
                                        </c:when>

                                        <c:otherwise>
                                            <option value="${s.cd}">
                                                ${s.name}
                                            </option>
                                        </c:otherwise>

                                    </c:choose>

                                </c:forEach>

                            </select>

                        </td>

                        <td>
                            <br>
                            <button type="submit">
                                検索
                            </button>
                        </td>

                    </tr>

                </table>

            </form>

            <hr>

            <!-- 学生情報 -->
            <form action="TestListStudentExecute.action" method="get">

                学生情報　

                学生番号

                <input
                    type="text"
                    name="no"
                    value="${param.no}"
                    placeholder="学生番号を入力してください">

                <button type="submit">
                    検索
                </button>

            </form>

            <p class="message">
                科目情報または学生情報を入力して検索してください
            </p>

        </div>

        <!-- 下の一覧エリア -->
        <div class="area">

            <div class="student-name">

                <c:if test="${not empty list}">
                    氏名：${list[0].studentName}（${student.no}）
                </c:if>

            </div>

            <table>

                <tr>
                    <th>科目名</th>
                    <th>科目コード</th>
                    <th>回数</th>
                    <th>点数</th>
                </tr>

                <c:if test="${not empty list}">

                    <c:forEach var="obj" items="${list}">

                        <tr>

                            <td>${obj.subjectName}</td>

                            <td>${obj.subjectCd}</td>

                            <td>${obj.num}</td>

                            <td>${obj.point}</td>

                        </tr>

                    </c:forEach>

                </c:if>

            </table>

        </div>

    </c:param>

</c:import>