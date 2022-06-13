package com.spring.mvc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private IBoardService service;
	
	// 게시글 등록 
	@PostMapping("/write")
	public String writeBoard(BoardVO board) {
		System.out.println("/board/write : POST");
		service.insert(board);
		return "redirect:/board/list";
		// board/list로 다시 재요청을 보내 리스트를 확인할 수 있도록 함
		// 글 등록이후 다시 list로 재요청 
	}

	// 게시글 목록 불러오기 
	@GetMapping("/list")
	public String listBoard(Model model){
		System.out.println("/board/list : GET");
		model.addAttribute("articles", service.getArticleList());
		// model에 articles라는 이름으로 전체 게시물을 담아줌
		// model은 지정된 url로 자동 이동
		return "board/list";
		// 특정 url로 보냄
	}
	
	
	// 게시글 상세보기 
	@GetMapping("/content")
	public String contentBoard(Model model) {
		System.out.println("/board/content : GET");
		model.addAttribute("article", service.getArticle(30));
		return "board/content";
	}
	
	// 게시글 수정 
	@PostMapping("/modify")
	public String modifyBoard(BoardVO board) {
		System.out.println("/board/modify : POST");
		board.setBoardNo(5);
		service.updateArticle(board);
		return "board/modify";
	}

}
