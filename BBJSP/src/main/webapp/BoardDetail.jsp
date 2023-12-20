<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="BBDTO.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="BBDTO.BoardDAO" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%
    // boardDTOを取得
    BoardDTO selectedBoard = (BoardDTO) request.getAttribute("boardDTO");
    System.out.println("DetailList :" + selectedBoard);

    // selectedBoardがnullでない場合のみgetBoardNo()を呼び出す
    if (selectedBoard != null) {
%>
        System.out.println("boardNo :" + selectedBoard.getBoardNo());
<%
    } else {
%>
        System.out.println("selectedBoard is null");
<%
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board Detail</title>
</head>
<body>
    <h2>Board Detail</h2>
    
<%
    if (selectedBoard != null) {
%>
    <table border="1">
        <tr>
            <th>Board No</th>
            <th>Board Title</th>
            <th>Board Content</th>
            <th>Board Author</th>
            <th>Board Created</th>
        </tr>
        <tr>
            <td><%= selectedBoard.getBoardNo() %></td>
            <td><%= selectedBoard.getBoardTitle() %></td>
            <td><%= selectedBoard.getBoardContent() %></td>
            <td><%= selectedBoard.getBoardAuthor() %></td>
            <td><%= selectedBoard.getBoardCreated() %></td>
        </tr>
    </table>
<%
    } else {
%>
    <p>No data available for the selected board.</p>
<%
    }
%>
</body>
</html>