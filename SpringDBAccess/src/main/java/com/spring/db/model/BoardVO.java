package com.spring.db.model;


/*
	CREATE TABLE jdbc_board(
	    board_no NUMBER PRIMARY KEY,
	    writer VARCHAR2(30) NOT NULL,
	    title VARCHAR2(100) NOT NULL,
	    content VARCHAR2(1000)
	);
	
	CREATE SEQUENCE bid_seq
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 1000
	NOCYCLE
	NOCACHE;
 */
public class BoardVO {
	
	private int boardNo;
	private String writer;
	private String title;
	private String content;
	
	public BoardVO() {}
	
	
	public BoardVO(int boardNo, String writer, String title, String content) {
		super();
		this.boardNo = boardNo;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}
	
	
	
	

}
