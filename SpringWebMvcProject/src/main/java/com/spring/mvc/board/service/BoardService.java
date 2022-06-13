package com.spring.mvc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public List<BoardVO> getArticleList() {
		
		return mapper.getArticleList();
		// Controller에 값을 보냄
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

}
