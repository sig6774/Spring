package com.spring.mvc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private IBoardService service;
	
	// 게시글 등록 이동 요청 
	@GetMapping("/write")
	public String moveWrite() {
		System.out.println("/board/write : GET");
		return "board/write";
	}
	
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
//	@GetMapping("/content")
//	public String contentBoard(@RequestParam("boardNo") int boardNo, Model model) {
//		System.out.println("/board/content : GET");
//		model.addAttribute("article", service.getArticle(boardNo));
//		return "board/content";
//	}

	// 게시글 상세보기 (?를 써서 파라미터를 보여주는 것이 아니라 특정 구분자로 url에 보여주는 방법)
	// 받은 parameter명에 @PathVariable annotation 지정
		// @PathVariable은 URL 경로에 변수를 포함시켜 주는 방식 
		// 만약 파라미터 값에 .이 포함되어 있다면 .뒤의 값은 잘림
		// {}안에 변수명 넣고 @PathVariable 괄호 안에 영역을 지목해서 값을 받아옴
	// 요청 url뒤에 /{받은 파라미터 명}
	@GetMapping("/content/{boardNo}")
	public String contentBoard(@PathVariable int boardNo, Model model) {
		// 파라미터명과 매개변수 명이 같으면 @RequestParam 안적어도 됨
		System.out.println("/board/content : GET");
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/content";
	}
	
	// 게시글 수정 
	@PostMapping("/modify")
	public String modifyBoard(BoardVO board) {
		System.out.println("/board/modify : POST");
		service.updateArticle(board);
		return "redirect:/board/content?boardNo=" + board.getBoardNo();
	}
	
	// 게시글 삭제 
	@PostMapping("/delete")
	public String deleteBoard(@RequestParam("boardNo") int boardNo, RedirectAttributes ra) {
		System.out.println("/board/delete : POST");
		
		service.deleteArticle(boardNo);
		ra.addFlashAttribute("msg", "deleteSuccess");
		return "redirect:/board/list";
	}

}
