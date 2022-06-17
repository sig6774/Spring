package com.spring.myweb.freeboard.mapper;

import java.util.List;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.util.PageVO;

public interface IFreeBoardMapper {

	// 글 등록 
	void regist(FreeBoardVO board);
	
	// 글 목록
	List<FreeBoardVO> getList(PageVO page);
	// 사용자가 선택한 페이지 정보와 검색 정보를 알 수 있는 PageVO 객체 매개변수 받음 
	
	// 총 게시물 수 
	int getTotal(PageVO page);
	// 검색에 따른 페이지 수 등이 다르므로 해당 정보를 가지고 있는 객체 매개변수로 받음 
	
	// 상세보기 
	FreeBoardVO getContent(int bno);
	
	// 수정 
	void update(FreeBoardVO board);
	
	// 삭제 
	void delete(int bno);
}
