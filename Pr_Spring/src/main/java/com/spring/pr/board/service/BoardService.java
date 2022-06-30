package com.spring.pr.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.pr.board.mapper.IBoardMapper;
import com.spring.pr.command.BoardVO;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardMapper mapper;

	@Override
	public void registBoard(BoardVO board) {
		mapper.registBoard(board);
	}

	@Override
	public List<BoardVO> listBoard() {
		return mapper.listBoard();
	}

	@Override
	public void modiBoard(String title, String content) {
		mapper.modiBoard(title, content);
	}

	@Override
	public void delBoard(int bNum) {
		mapper.delBoard(bNum);
	}

}
