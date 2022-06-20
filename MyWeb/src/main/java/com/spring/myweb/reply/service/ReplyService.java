package com.spring.myweb.reply.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.mapper.IReplyMapper;

@Service
// 빈등록 
public class ReplyService implements IReplyService {
	
	@Autowired
	private IReplyMapper mapper;

	@Override
	public void replyRegist(ReplyVO reply) {
		mapper.replyRegist(reply);
	}

	@Override
	public List<ReplyVO> getList(Map<String, Object> data) {
		return null;
	}

	@Override
	public int getTotal(int bno) {
		return 0;
	}

	@Override
	public int pwCheck(ReplyVO reply) {
		return 0;
	}

	@Override
	public void update(ReplyVO reply) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int rno) {

	}

}
