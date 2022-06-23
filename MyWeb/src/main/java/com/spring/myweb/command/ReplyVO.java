package com.spring.myweb.command;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 -- 댓글 테이블 
CREATE TABLE freereply(
    rno NUMBER(10,0) primary key, -- 댓글 번호 
    bno NUMBER(10,0), -- 글 번호 (FK)
    reply VARCHAR2(1000), -- 내용 
    reply_id VARCHAR2(50), -- 글쓴이 아이디 
    reply_pw VARCHAR2(50), -- 비번 
    reply_date DATE DEFAULT sysdate, -- 등록일 
    update_date DATE DEFAULT null
);

-- 시퀀스 생성 
CREATE SEQUENCE freereply_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE
    NOCACHE;
 */
@Getter
@Setter
@ToString
public class ReplyVO {

	private int rno;
	private int bno;
	private String reply;
	private String replyId;
	private String replyPw;
	private Timestamp replyDate;
	private Timestamp updateDate;
}
