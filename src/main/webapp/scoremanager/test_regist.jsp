<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>

.area{
    border:1px solid #ccc;
    padding:20px;
    max-width:1000px;
    margin:20px auto;
    background:#fff;
    border-radius:8px;
}

select, input{
    height:38px;
    padding:5px;
}

button{
    height:38px;
    padding:0 15px;
}

table{
    border-collapse:collapse;
    width:100%;
    font-size:16px;
}

th, td{
    border:1px solid #ddd;
    padding:12px;
    text-align:center;
}

h2{
    text-align:left;
}

.error{
    color:#ff9800;
    font-size:14px;
    margin-top:5px;
    text-align:left;
}

</style>

<c:import url="/common/base.jsp">

    <c:param name="title">
        成績管理
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <h2>成績管理</h2>

        <div class="area">

            <form action="TestRegist.action" method="get">

                入学年度
                <select name="f1">

                    <option value="">--------</option>

                    <c:forEach var="y" begin="2020" end="${year}">
                        <c:choose>
                            <c:when test="${f1 == y}">
                                <option value="${y}" selected>${y}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${y}">${y}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                </select>

                クラス
                <select name="f2">

                    <option value="">--------</option>

                    <c:forEach var="c" items="${classList}">
                        <c:choose>
                            <c:when test="${f2 == c}">
                                <option value="${c}" selected>${c}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${c}">${c}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                </select>

                科目
                <select name="f3">

                    <option value="">--------</option>

                    <c:forEach var="s" items="${subjectList}">
                        <c:choose>
                            <c:when test="${f3 eq s.cd}">
                                <option value="${s.cd}" selected>${s.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${s.cd}">${s.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                </select>

                回数
                <select name="f4">

                    <option value="">--------</option>

                    <c:forEach var="i" begin="1" end="10">
                        <c:choose>
                            <c:when test="${f4 == i}">
                                <option value="${i}" selected>${i}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${i}">${i}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                </select>

                <button type="submit">検索</button>

            </form>

        </div>

        <br>

        <c:if test="${not empty list}">

            <form action="TestRegistExecute.action" method="post">

                <table>

                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>点数</th>
                    </tr>

                    <c:forEach var="t" items="${list}">

                        <tr>

                            <td>${t.student.entYear}</td>

                            <td>${t.classNum}</td>

                            <td>
                                ${t.student.no}

                                <input type="hidden"
                                       name="studentNo"
                                       value="${t.student.no}">
                            </td>

                            <td>${t.student.name}</td>

                            <td>

                                <input type="number"
                                       name="point"
                                       value="${t.point}">

                                <c:if test="${sessionScope.error != null}">
                                    <div class="error">
                                        ${sessionScope.error}
                                    </div>
                                </c:if>

                            </td>

                        </tr>

                    </c:forEach>

                </table>

                <input type="hidden"
                       name="subjectCd"
                       value="${f3}">

                <input type="hidden"
                       name="num"
                       value="${f4}">

                <input type="hidden"
                       name="classNum"
                       value="${f2}">

                <input type="hidden"
                       name="entYear"
                       value="${f1}">

                <br>

                <button type="submit">
                    登録して終了
                </button>

            </form>

        </c:if>

        <c:remove var="error" scope="session"/>

    </c:param>

</c:import>