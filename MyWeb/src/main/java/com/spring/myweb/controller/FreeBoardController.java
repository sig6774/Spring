package com.spring.myweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// 글 작성 
	@GetMapping("/freeRegist")
	public String freeRegi() {
		System.out.println("/freeBoard/freeRegist : GET");
		System.out.println("작성 페이지로 이동");
		
		return "/freeBoard/freeRegist";
	}
	

	@PostMapping("/freeRegist")
	public String freeRegi(FreeBoardVO board, RedirectAttributes ra) {
		System.out.println("/freeBoard/freeRegist : POST");
		System.out.println("게시물 값 가져오는지 확인" + board.toString());
		
		service.regist(board);
		ra.addFlashAttribute("msg", "정상 등록 처리되었습니다.");
		return "redirect:/freeBoard/freeList";
	}
	
	
	// 목록 
	@GetMapping("/freeList")
	public String freeList(PageVO page, Model model) {
		System.out.println("/freeboard/freeList : GET");
		System.out.println("page : " + page.toString());
		// 페이지 들고 오는지 확인 
		
		List<FreeBoardVO> allList = service.getList(page);
		model.addAttribute("boardList", allList);
		// 모델에 데이터를 담아서 보내줌
		
		PageCreator pcv = new PageCreator();
		pcv.setPaging(page);
		pcv.setArticleTotalCount(service.getTotal(page));
		System.out.println("pcv 객체 확인 : " + pcv.toString());
		
		model.addAttribute("pcv", pcv);
		// 모델에 데이터를 담아서 보내줌
		return "/freeBoard/freeList";
		
	}
	
	// 상세보기 
	@GetMapping("/freeDetail")
	public String freeContent(@RequestParam("bno") int bno, Model model) {
		// 파라미터 명이 bno라는 것을 받아와서 상세보기 구현 
		System.out.println("/freeBoard/freeDetail : GET");
		System.out.println("게시물 상세보기 , 게시물 번호 가져오는지 확인 : " + bno);
		
		FreeBoardVO board = service.getContent(bno);
		System.out.println("게시물 DB에서 가져오는지 확인 : " + board.getBno());
		model.addAttribute("board", board);
		return "/freeBoard/freeDetail";
	}
	
	// 게시물 수정 (태그가 버튼이라서 그런가? jquery가 안먹히네)
	@GetMapping("/modify")
	public String freeMoveModi(@RequestParam("bno") int bno, Model model) {
		System.out.println("/freeBoard/modify : GET");
		System.out.println("게시물 수정 , 게시물 번호 가져오는지 확인 : " + bno);
		FreeBoardVO board = service.getContent(bno);
		
		model.addAttribute("board", board);
		return "/freeBoard/modify";
	}
}
