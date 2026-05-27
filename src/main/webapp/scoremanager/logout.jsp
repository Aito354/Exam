<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
}

body{
    background:#f5f5f5;
    font-family:sans-serif;
}

/* 全体 */
.wrapper{
    width:1000px;
    margin:0 auto;
    background:#fff;
    min-height:100vh;
}

/* ヘッダー */
.header{
    background:#dfe8f3;
    padding:25px 30px;
    font-size:42px;
    font-weight:bold;
    color:#333;
}

/* メイン */
.main{
    width:700px;
    margin:20px auto;
}

/* タイトル */
.title{
    background:#eeeeee;
    padding:15px 20px;
    font-size:30px;
    font-weight:bold;
    margin-bottom:15px;
}

/* メッセージ */
.message{
    background:#9fd5b3;
    color:#333;
    text-align:center;
    padding:10px;
    margin-bottom:40px;
    font-size:14px;
}

/* ログインリンク */
.login-link{
    margin-top:20px;
}

.login-link a{
    color:#4a6ee0;
    text-decoration:none;
    font-size:14px;
}

.login-link a:hover{
    text-decoration:underline;
}

/* フッター */
.footer{
    margin-top:80px;
    background:#eeeeee;
    text-align:center;
    padding:15px;
    font-size:12px;
    color:#666;
}

</style>

</head>

<body>

<div class="wrapper">

    <!-- ヘッダー -->
    <div class="header">
        得点管理システム
    </div>

    <!-- メイン -->
    <div class="main">

        <!-- タイトル -->
        <div class="title">
            ログアウト
        </div>

        <!-- メッセージ -->
        <div class="message">
            ログアウトしました
        </div>

        <!-- ログイン -->
        <div class="login-link">

            <a href="${pageContext.request.contextPath}/scoremanager/login.jsp">
                ログイン
            </a>

        </div>

    </div>

    <!-- フッター -->
    <div class="footer">
        © 2023 TIC<br>
        大原学園
    </div>

</div>

</body>
</html>