package BBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import BBDTO.BoardDAO;
import BBDTO.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 데이터베이스에 연결
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/BBP";
            String username = "postgres";
            String password = "1234";

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                // 리퀘스트 파라미터에서 게시판 번호 가져오기
                String boardNoParam = request.getParameter("boardNo");
                System.out.println("boardNoParam: " + boardNoParam);

                if (boardNoParam != null && !boardNoParam.isEmpty()) {
                    try {
                        int boardNo = Integer.parseInt(boardNoParam);
                        System.out.println("boardNo: " + boardNo);

                        // BoardDAO를 사용하여 데이터를 가져옴
                        BoardDAO boardDAO = new BoardDAO(conn);
                        BoardDTO boardDTO = boardDAO.readDataByBoardNo(boardNo);
                        System.out.println("boardDTO: " + boardDTO);

                        // 리퀘스트 속성에 데이터를 설정
                        request.setAttribute("boardDTO", boardDTO);

                        // JSP로 포워드
                        request.getRequestDispatcher("/BoardDetail.jsp").forward(request, response);

                    } catch (NumberFormatException e) {
                        // 정수로 변환할 수 없는 경우의 에러 핸들링
                        e.printStackTrace();
                    }
                } else {
                    // "boardNo"가 null이거나 비어 있는 경우의 에러 핸들링
                    System.out.println("boardNo is null or empty");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
