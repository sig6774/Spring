package com.spring.mvc.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.board.commons.PageVO;
import com.spring.mvc.board.commons.SearchVO;
import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IBoardMapper;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardMapper mapper;
	
	
	@Override
	public void insert(BoardVO board) {
		mapper.insert(board);
	}

//	@Override
//	public List<BoardVO> getArticleList() {
//		
//		return mapper.getArticleList();
//		// Controller에 값을 보냄
//	}

	@Override
	public List<BoardVO> getArticleList(SearchVO search) {
		
		// Mapper에게 전달한 Map 데이터 생성 
		// key -> xml에서 활용할 이름, value -> 실제 값 
		Map<String, Object> data = new HashMap<>();
		// 매개변수의 타입이 달라서 모두 담을 수 있도록 Object로 선언 
		data.put("paging", search.getPage());
		data.put("condition", search.getCondition());
		data.put("keyword", search.getKeyword());		
//		List<BoardVO> listBoard = mapper.getArticleList(paging, condition, keyword);
		
		List<BoardVO> listBoard = mapper.getArticleList(search);

		
		// 24시간이 지나지 않았으면 newMark.gif 표시 
		for(BoardVO board : listBoard) {
			long now = System.currentTimeMillis();
			// 현재의 서버 시간을 가져옴 
			
			long writeTime = board.getRegDate().getTime();
			// 게시물 작성시간
			
			if(now - writeTime < 60*60*24*1000) {
				// 현재시간 - 작성시간이 24시간 보다 작으면 
				board.setNewMark(true);
			}
		}
		return listBoard;
 	}
	
	@Override
	public BoardVO getArticle(int boardNo) {
		return mapper.getArticle(boardNo);
	}

	@Override
	public void updateArticle(BoardVO board) {
		mapper.updateArticle(board);
	}

	@Override
	public void deleteArticle(int boardNo) {
		mapper.deleteArticle(boardNo);
	}
	
	@Override
	public int countArticles(SearchVO search) {
		// keyword와 condition 정보를 포함해서 게시물 개수를 조회할 수 있으므로 매개값으로 SearchVO 객체를 받음
		return mapper.countArticles(search);
	}

}
