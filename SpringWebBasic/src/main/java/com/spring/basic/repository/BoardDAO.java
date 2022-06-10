package com.spring.basic.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.basic.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {

	// 게시글을 저장할 리스트 : DB 대용 
	private List<BoardVO> articles = new ArrayList<>();
	
	
	@Override
	public void insertArticle(BoardVO newBoard) {
		articles.add(newBoard);
	}

	@Override
	public List<BoardVO> listArticles() {
		return articles;
	}

	@Override
	public BoardVO getArticle(int bId) {
		return articles.get(bId-1);
		// 인덱스로 접근해서 가져옴
	}

	@Override
	public void deleteArticle(int bId) {
		articles.remove(bId -1);
		// 인덱스로 접근해서 삭제
	}

	@Override
	public void updateArticle(BoardVO board, int bId) {
		articles.set(bId-1, board);
		// set을 활용하여 인덱스를 통해 특정 위치에 값 넣을 수 있음
	}

}
