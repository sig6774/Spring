package com.spring.mvc.board.service;

import java.util.List;

import com.spring.mvc.board.commons.PageVO;
import com.spring.mvc.board.commons.SearchVO;
import com.spring.mvc.board.model.BoardVO;

public interface IBoardService {

	// 게시글 등록 기능 
	void insert(BoardVO board);
	
	// 게시글 전체 조회 (페이징 전)
//	List<BoardVO> getArticleList();
	
//	// 게시글 전체 조회 (페이징 처리)
//	List<BoardVO> getArticleList(PageVO paging);
//	// 페이징을 하기 위해서는 page와 cpp가 필요하므로 PageVO객체가 필요
	
//	// 게시글 전체 조회 (페이징, 검색기능 추가)
//	List<BoardVO> getArticleList(PageVO paging, String condition, String keyword);
	
	// 게시글 전체 조회 (페이징, 검색기능 추가)
	List<BoardVO> getArticleList(SearchVO search);
	
	// 게시글 상세 조회 기능
	BoardVO getArticle(int boardNo);
	
	// 게시글 수정 기능 
	void updateArticle(BoardVO board);
	
	// 게시글 삭제 기능 
	void deleteArticle(int boardNo);
	
	// 게시글 수 조회 기능
	// 검색 결과에 따른 게시물 수를 리턴할 수 있어야함으로 검색에 관련된 내용을 품고 있는 
	// SearchVO를 매개값으로 받음
	int countArticles(SearchVO search);

}
