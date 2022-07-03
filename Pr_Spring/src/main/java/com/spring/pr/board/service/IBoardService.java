package com.spring.pr.board.service;

import java.util.List;

import com.spring.pr.command.BoardVO;
import com.spring.pr.utils.PageVO;

public interface IBoardService {
	
	// 글 등록 
	void registBoard(BoardVO board);
	
	// 글 목록 
	List<BoardVO> listBoard(PageVO pageInfo);
	
	// 글 가져오기 
	BoardVO contentBoard(int BNum);
	
	// 글 전체 개수 가져오기 
	int getAllCount();
	
	// 글 수정 
	// 글 제목이랑 글 내용만 수정 가능 
	void modiBoard(BoardVO board);
	
	// 글 삭제 
	void delBoard(int bNum);

}
