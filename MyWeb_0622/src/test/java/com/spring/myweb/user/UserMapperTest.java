package com.spring.myweb.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.user.mapper.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserMapperTest {

	@Autowired
	private IUserMapper mapper;
	
	@Test
	public void insertTest() {

			UserVO user = new UserVO();
			user.setUserId("moon");
			user.setUserPw("abc");
			user.setUserName("문");
			user.setUserPhone1("02-24389");
			user.setUserPhone2("010-1234-1234");
			user.setUserEmail("fpdfkej");
			user.setUserEmail2("naver.com");
			user.setAddrBasic("부산");
			user.setAddrDetail("부산진구");
			user.setAddrZipNum("23423");
			mapper.join(user);
			// 삽입 테스트 완료 
	}
}
