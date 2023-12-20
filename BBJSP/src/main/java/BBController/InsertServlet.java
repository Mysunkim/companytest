package BBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스에 연결
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/BBP";
            String username = "postgres";
            String password = "1234";
            conn = DriverManager.getConnection(url, username, password);

            // 폼에서 데이터 가져오기
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String author = request.getParameter("author");

            // SQL문 작성 (boardNO는 SERIAL 타입이므로 자동으로 증가함)
            String sql = "INSERT INTO board (boardTitle, boardContent, boardAuthor) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, author);

            // 데이터 삽입 및 생성된 키 가져오기
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // 키를 가져와 출력
                response.getWriter().println("Insert to Success");

                // 생성된 키 가져오기
                var generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedKey = generatedKeys.getInt(1);
                    response.getWriter().println("Operation key: " + generatedKey);
                }
            } else {
                response.getWriter().println("Insert to Fail");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 연결 및 리소스 닫기
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
