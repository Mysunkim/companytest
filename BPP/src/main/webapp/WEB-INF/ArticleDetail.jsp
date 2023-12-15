<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Article Details</h1>
<%-- articleNo를 요청 파라미터에서 가져옴 --%>
    <% String articleNoParam = request.getParameter("articleNo"); %>

    <%-- articleNo가 지정되었는지 확인 --%>
    <% if (articleNoParam != null && !articleNoParam.isEmpty()) { %>

        <%-- articleNo에 해당하는 게시물을 데이터베이스에서 가져옴 --%>
        <% 
            int articleNo = Integer.parseInt(articleNoParam);
            ArticleBean article = null;

            // 데이터베이스 연결 정보
            String url = "jdbc:postgresql://localhost:5433/BBP";
            String username = "postgres";
            String password = "1234";

            try {
                // PostgreSQL JDBC 드라이버를 명시적으로 로드
                Class.forName("org.postgresql.Driver");

                try (Connection connection = DriverManager.getConnection(url, username, password)) {
                    // 데이터베이스에서 지정된 articleNo의 게시물을 가져오는 SELECT 쿼리
                    String selectsql = "SELECT * FROM articles WHERE articleNo = ?";
                    
                    // PreparedStatement 생성
                    try (PreparedStatement preparedStatement = connection.prepareStatement(selectsql)) {
                        preparedStatement.setInt(1, articleNo);

                        // SELECT 쿼리 실행 및 결과 가져오기
                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            // 결과가 있는 경우 ArticleBean 객체를 만듦
                            if (resultSet.next()) {
                                article = new ArticleBean();
                                article.setArticleNo(resultSet.getInt("articleNo"));
                                article.setArticleTitle(resultSet.getString("articleTitle"));
                                article.setArticleContent(resultSet.getString("articleContent"));
                                article.setArticleAuthor(resultSet.getString("articleAuthor"));
                                article.setArticlecreated_at(resultSet.getString("articlecreated_at"));
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // 적절한 예외 처리 수행
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                // 적절한 예외 처리 수행
            }
        %>

        <%-- 게시물이 가져와졌을 경우 상세 정보를 표시 --%>
        <% if (article != null) { %>
            <p>번호: <%= article.getArticleNo() %></p>
            <p>제목: <%= article.getArticleTitle() %></p>
            <p>작성자: <%= article.getArticleAuthor() %></p>
            <p>작성일: <%= article.getArticlecreated_at() %></p>
            <!-- 추가적인 게시물 정보를 표시하거나 스타일을 추가할 수 있음 -->
        <% } else { %>
            <p>게시물이 없습니다</p>
        <% } %>

        <footer>
            2023 Your Website
            <input type="button" value="뒤로 가기" onclick="location.href='ArticleList.jsp'">
        </footer>
</body>
</html>