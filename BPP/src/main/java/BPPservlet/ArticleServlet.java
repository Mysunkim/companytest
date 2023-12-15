package BPPservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BPPModel.ArticleBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 記事番号を取得
        String articleNoParam = request.getParameter("articleNo");

        // 記事が存在しないか不正な記事番号の場合はエラー
        if (articleNoParam == null || articleNoParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid article number");
            return;
        }

        int articleNo = Integer.parseInt(articleNoParam);

        // 接続情報
        String url = "jdbc:postgresql://localhost:5433/BBP";
        String username = "postgres";
        String password = "1234";

        try (
            // データベース接続
            Connection connection = DriverManager.getConnection(url, username, password);
            // 更新用のSQLクエリ
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE articles SET articleTitle = ?, articleContent = ? WHERE articleNo = ?"
            );
        ) {
            // リクエストから記事情報を取得
            String updatedTitle = request.getParameter("updatedTitle");
            String updatedContent = request.getParameter("updatedContent");

            // パラメータが不足している場合はエラー
            if (updatedTitle == null || updatedContent == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Missing parameters for article update");
                return;
            }

            // SQLクエリのパラメータを設定
            preparedStatement.setString(1, updatedTitle);
            preparedStatement.setString(2, updatedContent);
            preparedStatement.setInt(3, articleNo);

            // SQLクエリ実行
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                response.getWriter().write("Article updated successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Article not found for update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Database error");
        }
    }
	
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 폼에서 전송된 데이터 가져오기
        String articleTitle = request.getParameter("articleTitle");
        String articleContent = request.getParameter("articleContent");
        String articleAuthor = request.getParameter("articleAuthor");

        // articlecreated_at은 자동으로 생성되므로 여기서는 사용하지 않음

        // PostgreSQL 데이터베이스 연결 정보
        String url = "jdbc:postgresql://localhost:5433/BBP";
        String username = "postgres";
        String password = "1234";

        try {
            // PostgreSQL JDBC 드라이버를 명시적으로 로드
            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                // 데이터베이스에 새로운 기사 추가하는 INSERT 쿼리
                String insertSql = "INSERT INTO articles (articleTitle, articleContent, articleAuthor) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                    // 쿼리 매개변수 설정
                    preparedStatement.setString(1, articleTitle);
                    preparedStatement.setString(2, articleContent);
                    preparedStatement.setString(3, articleAuthor);

                    // INSERT 쿼리 실행
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // 적절한 예외 처리 수행
            }

            // 성공적으로 데이터베이스에 추가되면 다시 입력 폼으로 리다이렉트
            response.sendRedirect("MainMenu.jsp");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // 적절한 예외 처리 수행
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ArticleBean> articles = new ArrayList<>();

        // PostgreSQL データベースへの接続情報
        String url = "jdbc:postgresql://localhost:5433/BBP";
        String username = "postgres";
        String password = "1234";
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            // PostgreSQL JDBC ドライバを明示的にロード
            Class.forName("org.postgresql.Driver");

            // データベースとの接続
            connection = DriverManager.getConnection(url, username, password);

            // データベースから記事情報を取得する SELECT クエリ
            String selectsql = "SELECT * FROM articles";
            String detailsql = "SELECT * FROM articles WHERE articleNo = ?";
            // PreparedStatement の作成
            preparedStatement = connection.prepareStatement(selectsql);

            // SELECT クエリの実行と結果の取得
            resultSet = preparedStatement.executeQuery();

            // 結果がある場合、ArticleBean オブジェクトを作成しリストに追加
            while (resultSet.next()) {
                ArticleBean article = new ArticleBean();
                // articlecreated_atは String タイプなので getString を使用
                article.setArticleNo(resultSet.getInt("articleNo"));
                article.setArticleTitle(resultSet.getString("articleTitle"));
                article.setArticleContent(resultSet.getString("articleContent"));
                article.setArticleAuthor(resultSet.getString("articleAuthor"));
                article.setArticlecreated_at(resultSet.getString("articlecreated_at"));
                articles.add(article);

                // データをコンソールに出力
                System.out.println("Article Title: " + article.getArticleTitle());
                System.out.println("Article Content: " + article.getArticleContent());
                System.out.println("Article Author: " + article.getArticleAuthor());
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // 適切な例外処理を行う
        } finally {
            // リソースを解放する
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 取得した記事リストをリクエストにセット
        request.setAttribute("articles", articles);

        // JSP ページにフォワード
        request.getRequestDispatcher("ArticleList.jsp").forward(request, response);
    }
    
    
}

