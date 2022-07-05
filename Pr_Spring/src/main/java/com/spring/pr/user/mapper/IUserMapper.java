package com.spring.pr.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.pr.command.UserVO;

public interface IUserMapper {
	
	// 아이디 중복 체크 
	int checkId(String userId);
	
	// 아이디 등록 
	void registUser(UserVO user);
	
	// 유저 정보 가져오기 
	UserVO getUser(UserVO user);
	
	// 로그인 유저 
	UserVO loginUser(@Param("id") String id, @Param("pw") String pw);
	// mybatis는 한개이상의 값을 보낼 때 인식을 하지 못하는 경우가 있으므로 @Param annotation을 사용하거나 
	// 여러 다른 방법을 활용해서 mybatis가 인식할 수 있도록 만들어야 인식이 가능 
	
	
	// 유저 정보 변경 
	void chUser(UserVO user);
	
	// 유저 삭제 
	void delUser(String userPw);

}
