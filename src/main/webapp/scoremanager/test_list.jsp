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

</style>

<c:import url="/common/base.jsp">

    <c:param name="title">
        成績参照
        
        <c:if test="${not empty error}">
    <p style="color:red; font-size:14px;">
        ${error}
    </p>
</c:if>

<c:if test="${not empty message}">
    <p style="color:deepskyblue; font-size:14px;">
        ${message}
    </p>
</c:if>
    </c:param>

    <c:param name="content">

        <h2>成績参照</h2>

        <div class="area">

            <!-- 科目情報 -->
            <p>科目情報</p>

            <form action="TestListSubjectExecute.action" method="get">

                入学年度

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

                クラス

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

                <button type="submit">検索</button>

            </form>

            <br>

            <!-- 学生情報 -->
            <p>学生情報</p>

            <form action="TestListStudentExecute.action" method="get">

                学生番号
                <input type="text" name="f4" value="${f4}">

                <button type="submit">検索</button>

            </form>

            <br>

            <p style="color:deepskyblue;">
                科目情報または学生情報を入力して検索してください
            </p>

        </div>

        <br>

        <!-- 科目検索結果 -->
        <c:if test="${type == 'subject'}">

            <jsp:include page="test_list_subject.jsp" />

        </c:if>

        <!-- 学生検索結果 -->
        <c:if test="${type == 'student'}">

            <jsp:include page="test_list_student.jsp" />

        </c:if>

    </c:param>

</c:import>