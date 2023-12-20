package BBDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	
	private Connection conn;
	
    // コンストラクタ
    public BoardDAO(Connection conn) {
        this.conn = conn;
    }
	
 // データの挿入メソッド
    public int insertData(BoardDTO dto) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql;

        try {
            sql = "INSERT INTO board (boardTitle, boardContent, boardAuthor, boardCreated) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dto.getBoardTitle());
            pstmt.setString(2, dto.getBoardContent());
            pstmt.setString(3, dto.getBoardAuthor());
            pstmt.setString(4, dto.getBoardCreated());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // より詳細なエラー情報を出力する
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    
    // データの読み込みメソッド
    public List<BoardDTO> readData() {
        List<BoardDTO> boardList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;

        try {
            sql = "SELECT boardNo, boardTitle, boardContent, boardAuthor, boardCreated FROM board";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDTO dto = new BoardDTO();
                dto.setBoardNo(rs.getInt("boardNo"));
                dto.setBoardTitle(rs.getString("boardTitle"));
                dto.setBoardContent(rs.getString("boardContent"));
                dto.setBoardAuthor(rs.getString("boardAuthor"));
                dto.setBoardCreated(rs.getString("boardCreated"));

                boardList.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return boardList;
    }
    
    // boardNoでデータを取得するメソッド
    public BoardDTO readDataByBoardNo(int boardNo) {
        BoardDTO dto = null;
        String sql = "SELECT boardNo, boardTitle, boardContent, boardAuthor, boardCreated FROM board WHERE boardNo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, boardNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dto = new BoardDTO();
                    dto.setBoardNo(rs.getInt("boardNo"));
                    dto.setBoardTitle(rs.getString("boardTitle"));
                    dto.setBoardContent(rs.getString("boardContent"));
                    dto.setBoardAuthor(rs.getString("boardAuthor"));
                    dto.setBoardCreated(rs.getString("boardCreated"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dto;
    }
    
    // データを削除するメソッド
    public boolean deleteData(int boardNo) {
        boolean success = false;
        String sql = "DELETE FROM board WHERE boardNo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, boardNo);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

}

