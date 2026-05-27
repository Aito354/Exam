<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>科目登録完了</title>

<style>

body{
	margin:0;
	font-family: sans-serif;
	background-color:#f3f3f3;
}

/* ヘッダー */
.header{
	background:#eef3fb;
	height:70px;
	padding-left:60px;
	display:flex;
	align-items:center;
	font-size:40px;
	font-weight:bold;
	border-bottom:1px solid #cccccc;
}

/* 全体 */
.container{
	display:flex;
	min-height:600px;
}

/* 左メニュー */
.sidebar{
	width:180px;
	padding-top:30px;
	padding-left:20px;
	background:white;
	border-right:1px solid #dddddd;
}

.sidebar a{
	display:block;
	margin-bottom:18px;
	font-size:14px;
	color:#3b82f6;
	text-decoration:underline;
}

/* メイン */
.main{
	flex:1;
	padding:30px;
	background:white;
}

/* タイトル */
.title{
	font-size:28px;
	font-weight:bold;
	margin-bottom:25px;
}

/* 完了メッセージ */
.complete{
	background:#9fd5b3;
	padding:10px;
	font-size:14px;
	margin-bottom:40px;
}

/* 下リンク */
.links a{
	margin-right:30px;
	color:#3b82f6;
	text-decoration:underline;
	font-size:14px;
}

/* フッター */
.footer{
	height:60px;
	background:#eeeeee;
	display:flex;
	align-items:center;
	justify-content:center;
	font-size:12px;
	color:#666666;
}

</style>

</head>

<body>

<!-- ヘッダー -->
<div class="header">
	得点管理システム
</div>

<div class="container">

	<!-- 左メニュー -->
	<div class="sidebar">

		<a href="Menu.action">メニュー</a>

		<a href="StudentList.action">
			学生管理
		</a>

		<a href="#">
			成績管理
		</a>

		<a href="#">
			成績登録
		</a>

		<a href="#">
			成績参照
		</a>

		<a href="SubjectList.action">
			科目管理
		</a>

	</div>

	<!-- メイン -->
	<div class="main">

		<div class="title">
			科目情報登録
		</div>

		<div class="complete">
			登録が完了しました
		</div>

		<div class="links">

			<a href="SubjectCreate.action">
				戻る
			</a>

			<a href="SubjectList.action">
				科目一覧
			</a>

		</div>

	</div>

</div>

<!-- フッター -->
<div class="footer">
	© 2023 TIC
</div>

</body>
</html>