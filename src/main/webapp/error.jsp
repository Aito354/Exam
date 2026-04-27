<%
Exception e = (Exception) request.getAttribute("error");
if (e != null) {
%>
    <%= e.toString() %>
<%
} else {
%>
    エラー情報がありません
<%
}
%>