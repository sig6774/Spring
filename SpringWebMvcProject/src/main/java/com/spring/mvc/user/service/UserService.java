package com.spring.mvc.user.service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserMapper mapper;
	
	@Override
	public int checkId(String account) {
		return mapper.checkId(account);
	}

	@Override
	public void regist(UserVO user) {
		
		// 회원의 비밀번호를 암호화 인코딩하는 로직 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("암호화 하기 전 PW : " + user.getPassword());
		
		// 사용자에게 입력받은 비밀번호를 암호화해서 user객체에 다시 저장
		String securePw = encoder.encode(user.getPassword());
		System.out.println("암호화 후 PW : " + securePw);
		
		user.setPassword(securePw);
		// 암호화한 비밀번호를 다시 세팅 
		mapper.regist(user);
	}

	@Override
	public UserVO selectOne(String account) {
		return mapper.selectOne(account);
	}

	@Override
	public void delete(String account) {

	}
	
//	@Override
//	public int loginCheck(String account, String password){
//		UserVO user = mapper.selectOne(account);
////		System.out.println(user.getAccount());
////		System.out.println(user.getPassword());
////		System.out.println(account);
////		System.out.println(password);
//		String id = user.getAccount();
//		String pw = user.getPassword();
//		int num;
//		
//		if (id.equals(account)) {
//			if (pw.equals(password)) {
//				num = 1;
//			} else {
//				num = 0;
//			}
//		} else {
//			num = -1;
//		}
//		System.out.println(num);
//		return num;
//	}
	
	@Override
	public void keepLogin(String session, Date limitTime, String account) {
		Map<String, Object> data = new HashMap<>();
		data.put("sessionId", session);
		data.put("limitDate", limitTime);
		data.put("account", account);
		
		mapper.keepLogin(data);
	}

}
