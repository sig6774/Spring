package com.spring.myweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.service.IFreeBoardService;
import com.spring.myweb.util.PageCreator;
import com.spring.myweb.util.PageVO;

@Controller
// controller bean 등록 
@RequestMapping("/freeBoard")
public class FreeBoardController {

	@Autowired
	private IFreeBoardService service;
	
	// 목록 
	@GetMapping("/freeList")
	public String freeList(PageVO page, Model model) {
		System.out.println("/freeboard/freeList : GET");
		System.out.println("page : " + page.getPageNum());
		// 페이지 들고 오는지 확인 
		
		List<FreeBoardVO> allList = service.getList(page);
		model.addAttribute("boardList", allList);
		
		PageCreator pcv = new PageCreator();
		pcv.setPaging(page);
		pcv.setArticleTotalCount(service.getTotal(page));
		System.out.println("pcv 객체 확인 : " + pcv.toString());
		
		model.addAttribute("pcv", pcv);
		
		return "/freeBoard/freeList";
	}
}
