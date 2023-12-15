<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>
<%@ page import="BPPModel.ArticleBean" %>
<link rel="stylesheet" href="bpp.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Main Menu</h1>
<input type="button" value="リストへ" onclick="redirectToServlet()">

<input type="button" value="作成する" onclick="location.href='ArticleInputForm.jsp'">

<script>
function redirectToServlet() {
    // サーブレットのURLにリダイレクト
    window.location.href = 'ArticleServlet';
}
</script>
</body>
</html>