package com.spring.mvc.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
// 테스트 환경에서 Mapper 객체 활용을 위해 빈 등록 설정이 있는 xml 파일 로딩 
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
// root-config.xml에 있는 설정 가져옴 
public class UserMapperTest {
	
	@Autowired
	private IUserMapper mapper;
	// test를 진행하기 위해서는 db와 연결되어 있어야하는데 그것이 IBoardMapper임으로 
	// 해당 객체 container에서 찾아서 의존성 자동 주입 
	
	// 회원가입을 아무 아이디로 진행 
	@Test
	public void registTest() {
		UserVO user = new UserVO();
		user.setAccount("abc12345");
		user.setPassword("abc12345");
		user.setName("abcde초콜릿");
		mapper.regist(user);
	}
	
	// 위에서 회원 가입 아이디로 중복 확인해서 COUNT(*)을 활용해서 1이 리턴되는지 확인
	@Test
	public void checkTest() {
		String account = "abc123";
		int checknum = mapper.checkId(account);
		System.out.println(checknum);
	}
	
	// 가입한 회원의 정보를 얻어서 출력 
	@Test
	public void getUserTest() {
		
		String account = "abc123";
		UserVO user = mapper.selectOne(account);
		System.out.println(user.getAccount());
		System.out.println(user.getPassword());
		System.out.println(user.getName());
		System.out.println(user.getRegDate());
	}
	
	// 가입한 회원 탈퇴 진행 
	// 탈퇴가 성공했는지 여부를 메서드를 통해 확인 (null 체크)
	@Test
	public void delUserTest() {
		String account = "abc123";
		mapper.delete(account);
		
		if(mapper.selectOne(account) == null) {
			System.out.println("회원 삭제 완료");
		}else {
			System.out.println("회원 삭제 실패");
		}
		
	}

}
