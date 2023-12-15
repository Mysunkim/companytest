<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <h1>Article List</h1>

   <% List<ArticleBean> articles = (List<ArticleBean>) request.getAttribute("articles"); %>
    <% if (articles != null && !articles.isEmpty()) { %>
        <table border="1">
            <thead>
                <tr>
                    <th>番号</th>
                    <th>タイトル</th>
                    <th>作成者</th>
                    <th>作成日</th>
                </tr>
            </thead>
            <tbody>
                <% for (ArticleBean article : articles) { %>
                    <tr>
                        <td><%= article.getArticleNo() %></td>
                        <td><a href="ArticleDetail.jsp?articleNo=<%= article.getArticleNo() %>"><%= article.getArticleTitle() %></a></td>
                        <td><%= article.getArticleAuthor() %></td>
                        <td><%= article.getArticlecreated_at() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No articles available</p>
    <% } %>
     <footer>
     	 2023 Your Website
     <input type="button" value="戻る" onclick="location.href='MainMenu.jsp'">
    </footer>

</body>
</html>