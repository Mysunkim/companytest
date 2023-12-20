package BBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import BBDTO.BoardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")

public class DeleteServlet extends HttpServlet  {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;

        try {
            // データベースに接続
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/BBP";
            String username = "postgres";
            String password = "1234";
            conn = DriverManager.getConnection(url, username, password);

            // パラメータから削除するデータのIDを取得
            int boardNo = Integer.parseInt(request.getParameter("boardNo"));

            // BoardDAOを使用してデータを削除
            BoardDAO boardDAO = new BoardDAO(conn);
            boolean success = boardDAO.deleteData(boardNo);

            if (success) {
                // 削除が成功した場合の処理（例: 成功メッセージをセット）
                request.setAttribute("deleteMessage", "データが削除されました");
            } else {
                // 削除が失敗した場合の処理（例: エラーメッセージをセット）
                request.setAttribute("errorMessage", "データの削除に失敗しました");
            }

            // リダイレクトまたは他の処理が必要な場合はここで行う
            // 例えば、リストのページにリダイレクトする場合:
            // response.sendRedirect(request.getContextPath() + "/ListServlet");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // エラーが発生した場合の処理（例: エラーメッセージをセット）
            request.setAttribute("errorMessage", "エラーが発生しました");
        } finally {
            // 接続をクローズ
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // メッセージがセットされている場合はリダイレクトまたは他の処理が必要ならばここで行う
            // 例えば、リストのページにリダイレクトする場合:
            // request.getRequestDispatcher("/ListServlet").forward(request, response);
        }
    }

}
