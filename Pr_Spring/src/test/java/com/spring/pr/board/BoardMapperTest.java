package com.spring.pr.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.pr.board.mapper.IBoardMapper;
import com.spring.pr.command.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
// db에 대한 설정이 있는 파일의 경로를 지정해서 해당 내용을 불러올 수 있도록 지정 
public class BoardMapperTest {
	
	@Autowired
	private IBoardMapper mapper;
	
	// 삽입 테스트 
	@Test
	public void re() {
		for (int i = 1; i<=50; i++) {
			BoardVO board = new BoardVO();
			board.setBWriter("writer 더미 " + i);
			board.setBTitle("title 더미 데이터 : " + i);
			board.setBContent("content 더미 데이터 : " + i);
			
			mapper.registBoard(board);
		}
	}

}
