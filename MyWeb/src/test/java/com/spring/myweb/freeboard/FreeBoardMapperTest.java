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
public class FreeBoardMapperTest {
	
	@Autowired
	private IFreeBoardMapper mapper;
	
	@Test
	public void registTest() {
		for (int i = 1; i <= 1; i ++) {
			FreeBoardVO board = new FreeBoardVO();
			board.setTitle("마이페이지 테스트 " + i);
			board.setWriter("moon" + i);
			board.setContent("테스트 글쓰기 " + i);
			mapper.regist(board);
		}
	}
	
}
