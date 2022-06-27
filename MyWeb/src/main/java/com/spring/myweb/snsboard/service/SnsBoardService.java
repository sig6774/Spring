package com.spring.myweb.snsboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.controller.SnsBoardController;
import com.spring.myweb.snsboard.mapper.ISnsBoardMapper;
import com.spring.myweb.util.PageVO;

@Service
public class SnsBoardService implements ISnsBoardService {
	
	@Autowired
	private ISnsBoardMapper mapper;

	@Override
	public void insert(SnsBoardVO board) {
		mapper.insert(board);
	}

	@Override
	public List<SnsBoardVO> getList(PageVO paging) {
		return null;
	}

	@Override
	public SnsBoardVO getDetail(int bno) {
		return null;
	}

	@Override
	public void delete(int bno) {

	}

}
