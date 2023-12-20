package BBDTO;

public class BoardDTO {
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardAuthor() {
		return boardAuthor;
	}
	public void setBoardAuthor(String boardAuthor) {
		this.boardAuthor = boardAuthor;
	}
	public String getBoardCreated() {
		return boardCreated;
	}
	public void setBoardCreated(String boardCreated) {
		this.boardCreated = boardCreated;
	}
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardAuthor;
	private String boardCreated;

}
