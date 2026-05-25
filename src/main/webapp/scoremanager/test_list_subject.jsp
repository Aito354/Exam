<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        成績一覧（科目）
    </c:param>

    <c:param name="content">

        <style>

        .area{
            border:1px solid #ddd;
            padding:20px;
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
        }

        table{
            width:100%;
            border-collapse:collapse;
            font-size:14px;
        }

        th,td{
            border-bottom:1px solid #ddd;
            padding:10px;
            text-align:center;
        }

        th{
            background:#f7f7f7;
        }

        h2{
            margin-bottom:20px;
        }

        .subject-name{
            margin:15px 0;
            font-size:14px;
        }

        .message{
            color:deepskyblue;
            font-size:13px;
        }

        </style>

        <h2>成績一覧（科目）</h2>

        <div class="area">

            <!-- 科目情報 -->
            <form action="TestListSubjectExecute.action" method="get">

                <table style="border:none;">

                    <tr style="border:none;">

                        <td style="border:none;">科目情報</td>

                        <td style="border:none;">

                            入学年度<br>

                            <select name="f1">

                                <option value="">--------</option>

                                <c:forEach var="y" begin="2020" end="${year}">

                                    <c:choose>

                                        <c:when test="${f1 eq y}">
                                            <option value="${y}" selected>${y}</option>
                                        </c:when>

                                        <c:otherwise>
                                            <option value="${y}">${y}</option>
                                        </c:otherwise>

                                    </c:choose>

                                </c:forEach>

                            </select>

                        </td>

                        <td style="border:none;">

                            クラス<br>

                            <select name="f2">

                                <option value="">--------</option>

                                <c:forEach var="c" items="${classList}">

                                    <c:choose>

                                        <c:when test="${f2 eq c}">
                                            <option value="${c}" selected>${c}</option>
                                        </c:when>

                                        <c:otherwise>
                                            <option value="${c}">${c}</option>
                                        </c:otherwise>

                                    </c:choose>

                                </c:forEach>

                            </select>

                        </td>

                        <td style="border:none;">

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

                        <td style="border:none;">
                            <br>
                            <button type="submit">検索</button>
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
                    name="f4"
                    placeholder="学生番号を入力してください">

                <button type="submit">検索</button>

            </form>

            <br>

            <p class="message">
                科目情報または学生情報を入力して検索してください
            </p>

        </div>

        <div class="area">

            <div class="subject-name">
                科目：${subject.name}
            </div>

            <table>

                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>1回</th>
                    <th>2回</th>
                </tr>

                <c:forEach var="t" items="${list}">

                    <tr>

                        <td>${t.entYear}</td>

                        <td>${t.classNum}</td>

                        <td>${t.studentNo}</td>

                        <td>${t.studentName}</td>

                        <td>${t.getPoint(1)}</td>

                        <td>${t.getPoint(2)}</td>

                    </tr>

                </c:forEach>

            </table>

        </div>

    </c:param>

</c:import>
