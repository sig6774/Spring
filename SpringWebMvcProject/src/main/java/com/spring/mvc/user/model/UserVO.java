package com.spring.mvc.user.model;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {

//	CREATE TABLE mvc_user(
	
//		    account VARCHAR2(50) primary key, 
//		    password VARCHAR2(100) not null, 
//		    name VARCHAR2(50) not null, 
//		    reg_date DATE DEFAULT sysdate
//		);
	
//	-- 자동 로그인 관련 컬럼 추가 
//	ALTER TABLE mvc_user 
//	ADD session_id VARCHAR2(80)
//	DEFAULT 'none' NOT NULL;
//
//	ALTER TABLE mvc_user 
//	ADD limit_time DATE;
//	-- 자동 로그인을 신청한 사람만 진행하기 때문에 sysdate 적지 않음

	
	private String account;
	private String password;
	private String name;
	private Timestamp regDate;
	
	// 자동 로그인 체크 여부 
	private boolean autoLogin;
	
	private String sessionId;
	private Timestamp limitTime;
	// mapper.xml에 변수명과 컬럼명이 다르므로 바꿔줘야 함
	
}
