<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="BBDTO.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Board List</h2>
<%
    List<BoardDTO> boardList = (List<BoardDTO>) request.getAttribute("boardList");
	System.out.println("boardList : " + boardList);

    if (boardList != null && !boardList.isEmpty()) {
    	
%>

<table border="1">
        <tr>
            <th>Board No</th>
            <th>Board Title</th>
            <th>Board Content</th>
            <th>Board Author</th>
            <th>Board Created</th>
        </tr>

        <% for (BoardDTO dto : boardList) { %>
            <tr>
                <td><%= dto.getBoardNo() %></td>
                <td><a href="BoardDetail.jsp?boardNo=<%= dto.getBoardNo() %>"><%= dto.getBoardTitle() %></a></td>
                <td><%= dto.getBoardContent() %></td>
                <td><%= dto.getBoardAuthor() %></td>
                <td><%= dto.getBoardCreated() %></td>
               
            </tr>
        <% } %>
    </table>
<%
    } else {
%>
    <p>No data available.</p>
<%
    }
%>
</body>
</html>