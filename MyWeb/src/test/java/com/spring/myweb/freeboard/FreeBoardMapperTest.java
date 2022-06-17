package com.spring.myweb.freeboard;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
// db설정이 들어있는 xml 가져옴 
public class FreeBoardMapperTest {

	@Autowired
	private IFreeBoardMapper mapper;
	// mapper를 사용하여 db와 연결하기 위해 의존성 자동 주입

	@Test
	public void registTest() {
		for (int i = 1; i <= 30; i++) {
			FreeBoardVO board = new FreeBoardVO();
			board.setTitle("마이페이지 테스트 " + i);
			board.setWriter("moon" + i);
			board.setContent("테스트 글쓰기 내용 " + i);
			mapper.regist(board);
		}
		
	}
	
}
