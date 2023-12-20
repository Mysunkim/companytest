<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Insert Data</h2>

<form action="InsertServlet" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br>

    <label for="content">Content:</label>
    <textarea id="content" name="content" required></textarea><br>

    <label for="author">Author:</label>
    <input type="text" id="author" name="author" required><br>

    <input type="submit" value="Insert Data">
</form>
</body>
</html>