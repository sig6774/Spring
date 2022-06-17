package com.spring.mvc.user.repository;

import java.util.Map;

import com.spring.mvc.user.model.UserVO;

public interface IUserMapper {
	
	// 아이디 중복 체크 기능 
	int checkId(String account);
	
	// 회원 가입 기능 
	void regist(UserVO user);
	
	// 회원 정보 조회 
	UserVO selectOne(String account);
	
	// 회원 탈퇴 기능 
	void delete(String account);
	
//	// 로그인 기능 
//	int loginCheck(String account, String password);
	
	// 자동 로그인 쿠키값 DB 저장 처리 
	void keepLogin(Map<String, Object> data);
	// 해당 데이터는 존재하기 때문에 insert가 아니라 update임
	
	// 세션 아이디를 통한 회원 정보 조회 기능 
	UserVO getUserWithSessionId(String sessionId);
	/*
	 자동 로그인을 하고 싶은 사람에게 쿠키(세션 id)를 만들어줌
	 자동 로그인을 희망한 사람이 시간이 지나고 다시 방문을 했으면 
	 희망한 사람이 서버에 요청을 보내고 요청과 함께 쿠키도 같이 전달이 됨(쿠키는 사용자가 가지고 있음) 
	 쿠키 안에 있는 세션 id로 회원 정보를 조회해서 마치 희망한 사람이 로그인 중인 것처럼 세션 데이터를 
	 만들어줌  
	 */
}
