<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		科目登録完了
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目情報登録
			</h2>

			<div class="alert alert-success" role="alert">
				登録が完了しました
			</div>

			<div class="mt-4">

				<a href="${pageContext.request.contextPath}/scoremanager/main/SubjectCreate.action">
					戻る
				</a>

				&nbsp;&nbsp;&nbsp;

				<a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">
					科目一覧
				</a>

			</div>

		</section>

	</c:param>

</c:import>