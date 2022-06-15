package com.spring.mvc.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
// 테스트 환경에서 Mapper 객체 활용을 위해 빈 등록 설정이 있는 xml 파일 로딩
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
// XML 파일 설정을 가져옴 
// root-config.xml에 있는 Mapper설정을 가져옴
public class BoardMapperTest {
	
	@Autowired
	private IBoardMapper mapper;
		
	// 게시글 등록 단위 테스트
	@Test
	public void insertTest() {
		for (int i = 0; i <= 100; i++) {
			BoardVO board = new BoardVO();
			board.setTitle("테스트 제목" + i);
			board.setWriter("문테스트" + i);
			board.setContent("테스트 중입니당" + i);
			mapper.insert(board);
			// 테스트 값이 실제 DB에 들어가는지 확인
			// root-config.xml을 가져왔으므로 될 것 같은데
			// 테스트 성공
		}
	}
	
	//게시글 목록 전체 조회 테스트
	//게시물 개수 몇개인지 출력하시고, 게시글 모든 내용을 toString()으로 출력
//	@Test
//	public void listTest() {
//		List<BoardVO> boards = mapper.getArticleList();
	
//		// mapper와 연동해서 전체 게시물 가져옴 
//		
//		System.out.println("전체 게시물 개수 : "+ boards.size());
//		for(BoardVO board : boards) {
//			System.out.println(board.toString());
//			// 전체 게시물의 내용 모두 출력
//		}
//	}

	//게시글 단일 조회 테스트	
	@Test
	public void getBoard() {
		int num = 38;
		BoardVO board = mapper.getArticle(num);
		// 게시물 번호로 특정 게시물 가져옴 
		System.out.println(num + "번째 게시물 내용 : " +board.getContent());
	}

	//게시글 수정 테스트
	//수정 내용(글 제목, 글 내용)을 입력하고 수정을 진행 (1번글의 제목, 내용)
	@Test
	public void upBoard() {
		BoardVO upBoard = new BoardVO();
		upBoard.setBoardNo(1);
		upBoard.setTitle("수정 테스트 글 제목");
		upBoard.setContent("수정 테스트 글 내용");
		mapper.updateArticle(upBoard);
		// 특정 게시물 번호로 게시물 수정 
		
		BoardVO board = mapper.getArticle(1);
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		// 수정이 정상적으로 완료되었는지 확인
	}

	//게시글 삭제 테스트
	//3번글을 삭제하세요. 삭제 후 상세보기를 통해 3번글을 가져왔을 때 null이 리턴되는지
	//조건문으로 확인해서 결과를 출력
	@Test
	public void delBoard() {
		int num = 3;
		mapper.deleteArticle(num);
		// 특정 게시물 번호로 게시물 삭제 
		
		// delete이후 실제 해당 게시물 번호로 값이 존재하는지 유무 파악
		if (mapper.getArticle(num) == null) {
			System.out.println("삭제 성공 게시글 없음");
		} else {
			System.out.println("삭제 실패");
		}
	}
}
