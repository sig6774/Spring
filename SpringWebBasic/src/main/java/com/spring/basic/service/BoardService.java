package com.spring.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.basic.model.BoardVO;
import com.spring.basic.repository.IBoardDAO;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO dao;
	// BoardService는 BoardDAO가 없으면 수행할 수 없기 때문에 객체를 불러옴 
	// Autowired를 통해 container에 있는 bean을 가져옴 

	@Override
	public void insertArticle(BoardVO newBoard) {
		dao.insertArticle(newBoard);

	}

	@Override
	public List<BoardVO> listArticles() {
		
		return dao.listArticles();
	}

	@Override
	public BoardVO getArticle(int bId) {
		return dao.getArticle(bId);
	}

	@Override
	public void deleteArticle(int bId) {
		dao.deleteArticle(bId);
	}

	@Override
	public void updateArticle(BoardVO board, int bId) {
		dao.updateArticle(board, bId);

	}

}
