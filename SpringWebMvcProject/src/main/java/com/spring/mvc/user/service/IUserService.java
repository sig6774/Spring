package com.spring.mvc.user.service;

import java.util.Date;
import java.util.Map;

import com.spring.mvc.user.model.UserVO;

public interface IUserService {

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
	void keepLogin(String sessoin, Date limitTime, String account);
	// 해당 데이터는 존재하기 때문에 insert가 아니라 update임
	// controller에서는 Map으로 포장해서 전달하지 않으므로 Map 사용 X
	
//	// 자동 로그인 쿠키값 DB 변경 처리 
//	void changeLogin(String session, Date limitTime, String account);

}
