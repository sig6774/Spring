package com.spring.myweb.reply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.mapper.IReplyMapper;
import com.spring.myweb.util.PageVO;

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
	public List<ReplyVO> getList(PageVO page, int bno) {
		Map<String, Object> data = new HashMap<>();
		data.put("paging", page);
		data.put("bno", bno);
		// controller에서도 map으로 포장해도 되지만 service가 더 적합함으로 
		// map객체를 이용해서 값들을 넣어줌 
		
		// mybatis는 여러값을 보낼 수 없으므로 map객체를 활용해서 다중 정보를 담아서 보냄
		return mapper.getList(data);
	}

	@Override
	public int getTotal(int bno) {
		return mapper.getTotal(bno);
	}

	@Override
	public int pwCheck(ReplyVO reply) {
		
		return mapper.pwCheck(reply);
	}

	@Override
	public void update(ReplyVO reply) {
		mapper.update(reply);
	}

	@Override
	public void delete(int rno) {
		mapper.delete(rno);
	}

}
