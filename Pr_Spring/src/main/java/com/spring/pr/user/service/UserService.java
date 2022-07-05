package com.spring.pr.user.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.pr.command.UserVO;
import com.spring.pr.user.mapper.IUserMapper;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserMapper mapper;

	@Override
	public int checkId(String userId) {
		return mapper.checkId(userId);
	}

	@Override
	public void registUser(UserVO user) {
		mapper.registUser(user);

	}
	
	@Override
	public UserVO loginUser(@Param("id") String id, @Param("pw") String pw) {
		return mapper.loginUser(id, pw);
	}

	@Override
	public UserVO getUser(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void chUser(UserVO user) {
		mapper.chUser(user);

	}

	@Override
	public void delUser(String userPw) {
		mapper.delUser(userPw);
	}

}
