package com.spring.myweb.freeboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;
import com.spring.myweb.util.PageVO;

@Service
// container에 bean으로 등록하기 위해서 @Service annotaion 사용
public class FreeBoardService implements IFreeBoardService {
	
	@Autowired
	private IFreeBoardMapper mapper;
	// service는 mapper가 필요함으로 의존성 주입

	@Override
	public void regist(FreeBoardVO board) {
		mapper.regist(board);
	}

	@Override
	public List<FreeBoardVO> getList(PageVO page) {		
		List<FreeBoardVO> allList = mapper.getList(page);
		
		// newmark 넣기 (하루가 지나지 않았으면)
		for(FreeBoardVO article : allList) {
			long nowTime = System.currentTimeMillis();
			// 현재시간 
			
			long regTime = article.getRegDate().getTime();
			
			if (nowTime - regTime < 1000 * 60 * 60 * 24) {
				// 하루가 지나지 않았으면 
				article.setNewMark(true);
			}
		}
		return allList;
	}

	
	@Override
	public int getTotal(PageVO page) {
		return mapper.getTotal(page);
	}

	@Override
	public FreeBoardVO getContent(int bno) {
		return mapper.getContent(bno);
	}

	@Override
	public void update(FreeBoardVO board) {

	}

	@Override
	public void delete(int bno) {

	}

}
