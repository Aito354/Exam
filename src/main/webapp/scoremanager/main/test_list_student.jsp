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
            cursor:pointer;
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

        .student-name{
            margin:15px 0;
            font-size:14px;
            font-weight:bold;
        }

        .message{
            color:deepskyblue;
            font-size:13px;
        }

        </style>

        <h2>成績一覧（学生）</h2>

        <!-- 検索エリア -->
        <div class="area">

            <!-- 科目情報 -->
            <form action="TestList.action" method="get">

                <table style="border:none;">

                    <tr style="border:none;">

                        <td style="border:none;">
                            科目情報
                        </td>

                        <!-- 入学年度 -->
                        <td style="border:none;">

                            入学年度<br>

                            <select name="entYear">

                                <option value="">--------</option>

                                <option value="2023"
                                    <c:if test="${param.entYear == '2023'}">
                                        selected
                                    </c:if>>
                                    2023
                                </option>

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

                        </td>

                        <!-- クラス -->
                        <td style="border:none;">

                            クラス<br>

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

                        </td>

                        <!-- 科目 -->
                        <td style="border:none;">

                            科目<br>

                            <select name="subjectCd">

                                <option value="">--------</option>

                                <option value="JAVA">JAVA</option>
                                <option value="DB">DB</option>
                                <option value="KOKUGO">国語</option>
                                <option value="SUGAKU">数学</option>
                                <option value="RIKA">理科</option>
                                <option value="JOHO1">情報処理基礎知識Ⅰ</option>
                                <option value="EIGO">英語コミュニケーション概論</option>
                                <option value="JFRAME">Javaフレームワーク</option>
                                <option value="GIT">Git</option>
                                <option value="H2">H2</option>
                                <option value="ID">ID管理術</option>
                                <option value="JSYSTEM">Javaシステム開発</option>
                                <option value="AWS">AWS</option>
                                <option value="BEAN">Bean</option>
                                <option value="C">C言語</option>
                                <option value="ERROR">エラー対処術</option>

                            </select>

                        </td>

                        <!-- 回数 -->
                        <td style="border:none;">

                            回数<br>

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
                    name="no"
                    value="${param.no}"
                    placeholder="学生番号を入力してください">

                <button type="submit">検索</button>

            </form>

            <br>

            <p class="message">
                入学年度とクラスと科目を選択してください
            </p>

        </div>

        <!-- 一覧 -->
        <c:if test="${not empty list}">

            <div class="area">

                <div class="student-name">
                    氏名：${student.name}（${student.no}）
                </div>

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

            </div>

        </c:if>

    </c:param>

</c:import>