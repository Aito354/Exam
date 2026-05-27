<%@ page contentType="text/html;charset=UTF-8" %>

<h2>科目情報削除</h2>

<p>
「${subject.name}」を削除してもよろしいですか？
</p>

<form action="SubjectDeleteExecute.action" method="post">

    <input type="hidden"
           name="cd"
           value="${subject.cd}">

    <input type="submit"
           value="削除">

</form>

<br>

<a href="SubjectList.action">戻る</a>