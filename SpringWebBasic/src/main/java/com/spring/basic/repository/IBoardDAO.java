package com.spring.basic.repository;

import java.util.List;

import com.spring.basic.model.BoardVO;

public interface IBoardDAO {

	// 게시글 등록 
	void insertArticle(BoardVO newBoard);
	
	// 전체 게시글 목록
	List<BoardVO> listArticles();
	
	// 게시글 상세 보기 
	BoardVO getArticle(int bId);
	
	// 게시글 삭제 
	void deleteArticle(int bId);
	
	// 게시글 수정
	void updateArticle(BoardVO board, int bId);
	// 인덱스로 수정해야함으로 두개의 매개값 받음
}
