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
	
	private String account;
	private String password;
	private String name;
	private Timestamp regDate;
	
}
