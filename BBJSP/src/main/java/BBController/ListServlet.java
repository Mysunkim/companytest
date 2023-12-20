package BBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import BBDTO.BoardDAO;
import BBDTO.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;

        try {
            // データベースに接続
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/BBP";
            String username = "postgres";
            String password = "1234";
            conn = DriverManager.getConnection(url, username, password);

            // BoardDAOを使用してデータを取得
            BoardDAO boardDAO = new BoardDAO(conn);
            List<BoardDTO> boardList = boardDAO.readData();

            // リクエスト属性にデータを設定
            request.setAttribute("boardList", boardList);

            // JSPにフォワード
            request.getRequestDispatcher("/list.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 接続をクローズ
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
