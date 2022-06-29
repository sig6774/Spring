package com.spring.myweb.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SnsBoardVO {

//	-- SNS Board
//	CREATE TABLE snsboard(
//	    bno NUMBER(10,0) primary key,
//	    writer VARCHAR2(50) NOT NULL,
//	    uploadpath VARCHAR2(100) NOT NULL,
//	    fileloca VARCHAR2(100) NOT NULL,
//	    filename VARCHAR2(50) NOT NULL,
//	    filerealname VARCHAR2(50) NOT NULL,
//	    content VARCHAR2(2000),
//	    regdate DATE DEFAULT SYSDATE
//	);
//
//	CREATE SEQUENCE snsboard_seq
//	    START WITH 1
//	    INCREMENT BY 1 
//	    MAXVALUE 1000
//	    NOCYCLE 
//	    NOCACHE;
	
//	public SnsBoardVO(int bno, String writer, String uploadpath, String fileloca, String filename, String filerealname,
//			String content, Timestamp regdate) {
//		super();
//		this.bno = bno;
//		this.writer = writer;
//		this.uploadpath = uploadpath;
//		this.fileloca = fileloca;
//		this.filename = filename;
//		this.filerealname = filerealname;
//		this.content = content;
//		this.regdate = regdate;
//	}
	
	private int bno;
	private String writer;
	private String uploadpath;
	private String fileloca;
	private String filename;
	private String filerealname;
	private String content;
	private Timestamp regdate;
	
	// 좋아요 개수가 몇개인지 알려주는 변수 추가 
	private int likeCnt;
}
