package com.spring.myweb.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SnsLikeVO {
	
	/*
	 CREATE TABLE snslike(
	    bno NUMBER(10,0) NOT NULL, 
	    userID VARCHAR2(50) NOT NULL, 
	    lno NUMBER primary key
		);

	CREATE SEQUENCE snslike_seq 
	    START WITH 1
	    INCREMENT BY 1
	    MAXVALUE 10000
	    NOCYCLE 
	    NOCACHE;
	    
	 ALTER TABLE snslike ADD FOREIGN KEY(bno) 
   	 REFERENCES snsboard(bno)
	 ON DELETE CASCADE;
	 -- foreign key로 참조하는 데이터가 사라지면 참조를 하고 있는 데이터도 함께 삭제 
	
	 ALTER TABLE snslike ADD FOREIGN KEY(userId)
	 REFERENCES USERS(userid)
	 ON DELETE CASCADE;
	 */
	
	private int bno; 
	private String userId;
	private int lno;

}
