<%-- メニューJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                メニュー
                
            </h2>

            <div class="row text-center px-4 fs-3 my-5">

                <!-- 学生管理 -->
                <div class="col">
              <a href="${pageContext.request.contextPath}/scoremanager/main/StudentList.action">
    学生管理
</a>
                </div>

                <!-- 成績管理 -->
                <div class="col">
                    <div>成績管理</div>
                    <div>
                        <a href="${pageContext.request.contextPath}/scoremanager/main/StudentCreate">
                            成績登録
                        </a>
                    </div>
                    <div>
                    <a href="${pageContext.request.contextPath}/scoremanager/main/TestList.action">
                            成績参照
                        </a>
                    </div>
                </div>

                <!-- 科目管理 -->
                <div class="col">
                    <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">
                        科目管理
                    </a>
                </div>

                <!-- クラス管理 -->
                <div class="col">
                <a href="${pageContext.request.contextPath}/scoremanager/main/ClassList.action">
                        クラス管理
                    </a>
                </div>

                
                </div>

            </div>

        </section>
    </c:param>

</c:import>