<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="bpp.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Add New Article</h1>
    <form action="ArticleServlet" method="post">
        <label for="articleTitle">Title:</label>
        <input type="text" id="articleTitle" name="articleTitle" required><br>

        <label for="articleContent">Content:</label>
        <textarea id="articleContent" name="articleContent" rows="4" required></textarea><br>

        <label for="articleAuthor">Author:</label>
        <input type="text" id="articleAuthor" name="articleAuthor" required><br>

        <!-- articlecreated_at은 자동으로 생성되므로 입력 폼에서 제외 -->

        <button type="submit">作成</button>
    </form>
</body>
</html>