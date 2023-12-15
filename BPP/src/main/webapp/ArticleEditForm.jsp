<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="BPPModel.ArticleBean" %>
<link rel="stylesheet" href="bpp.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Article Edit Page</h1>

    <form method="PUT" action="/your-servlet-context/article">
        Article Number: <input type="text" name="articleNo"><br>
        Updated Title: <input type="text" name="updatedTitle"><br>
        Updated Content: <textarea name="updatedContent"></textarea><br>
        <input type="submit" value="Update Article">
    </form>

</body>
</html>