package com.spring.mvc.board.model;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// lombok을 통해 쉽게 vo작성 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardVO {
	
//	-- 게시판 테이블 생성 
//	CREATE TABLE mvc_board(
//	    board_no NUMBER PRIMARY KEY,
//	    title VARCHAR2(100) NOT NULL,
//	    content VARCHAR2(2000) NOT NULL,
//	    writer VARCHAR2(50) NOT NULL,
//	    reg_date DATE DEFAULT sysdate,
//	    view_cnt NUMBER DEFAULT 0
//	);
//
//	-- board_no에 대한 sequence 설정 
//	CREATE SEQUENCE board_seq
//		START WITH 1
//		INCREMENT BY 1
//		MAXVALUE 1000
//		NOCYCLE 
//		NOCACHE;

	
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Timestamp regDate;
	
	private int viewCnt;
	private boolean newMark;
	
	
}
