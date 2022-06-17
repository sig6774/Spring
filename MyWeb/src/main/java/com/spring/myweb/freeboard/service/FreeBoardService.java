package com.spring.myweb.freeboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;
import com.spring.myweb.util.PageVO;

@Service
public class FreeBoardService implements IFreeBoardService {
	
	@Autowired
	private IFreeBoardMapper mapper;

	@Override
	public void regist(FreeBoardVO board) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FreeBoardVO> getList(PageVO page) {
		return mapper.getList(page);
	}

	@Override
	public int getTotal(PageVO page) {
		int num = mapper.getTotal(page);
		return num;
	}

	@Override
	public FreeBoardVO getContent(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(FreeBoardVO board) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int bno) {
		// TODO Auto-generated method stub

	}

}
