package com.spring.mvc.board.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.mvc.board.commons.PageVO;
import com.spring.mvc.board.commons.SearchVO;
import com.spring.mvc.board.model.BoardVO;

public interface IBoardMapper {

	
	// 게시글 등록 기능 
	void insert(BoardVO board);
	
	// 게시글 전체 조회 (페이징 전)
//	List<BoardVO> getArticleList();
	
//	// 게시글 전체 조회 (페이징 처리)
//	List<BoardVO> getArticleList(PageVO paging);
//	// 페이징을 하기 위해서는 page와 cpp가 필요하므로 PageVO객체가 필요
	
	/*
	 - MyBatis로 DB연동을 진행할 때 파라미터 값이 2개 이상이라면 
	 그냥 보내게 되면 에러가 발생함으로 조치가 필요 
	 
	  1. @Param을 이용해서 이름을 붙여줌(xml 파일에서 해당 값을 지목할 수 있는 이름을 붙임)
		// 게시글 전체 조회 (페이징, 검색기능 추가)
		List<BoardVO> getArticleList(@Param("paging") PageVO paging, 
									 @Param("condition") String condition, 
									 @Param("keyword") String keyword);
	  2. Map으로 포장해서 보내는 방법 
	List<BoardVO> getArticleList(Map<String, Object> data);
	// service에서 맵핑한 Map객체를 매개변수로 받음
	 	
	  3. 클래스를 디자인해서 객체 하나만 매개값으로 보내는 방법 
	  
	   
	 3개 중 하나를 상황에 맞게 적절하게 선택
	
	*/
	
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
