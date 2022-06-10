package com.spring.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.basic.model.BoardVO;
import com.spring.basic.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private IBoardService service;
	// Controller는 BoardService없이는 동작을 할 수 없으므로 container에서 저장된 객체 불러옴 
	
	
	// 글작성 화면을 열어주는 메서드 
	@GetMapping("/write")
	public void write() {
		System.out.println("/board/write:GET");
	}
	
	// 작성 글 등록 처리 요청 
	@PostMapping("/write")
	public String write(BoardVO board) {
		// 파라미터 명과 객체의 변수명이 같으므로 커맨드 객체를 이용해서 값을 받아옴
		System.out.println("/board/write:POST");
		
		service.insertArticle(board);
		return "redirect:/board/list/";
		// /board/write 요청이 끝나면 바로 /board/list로 다시 요청을 보냄
	}
	
	
	
	
	// 글 목록 요청 처리 메서드
	@GetMapping("/list")
	public String list(Model model) {
		// 데이터를 다른 곳으로 보내야하기 때문에 Model 객체 사용
		System.out.println("/board/list:GET");
		List<BoardVO> articles = service.listArticles();
		model.addAttribute("listBoard", articles);
		// 값들을 전부 받아서 model 객체에 넣어줌
		return "/board/list";
	}
	
	// 글 상세보기 요청 처리 메서드
	@GetMapping("/content")
	public String content(@RequestParam("boardNo") int num, Model model) {
		System.out.println("/board/content:GET");
		BoardVO board = service.getArticle(num);
//		System.out.println(board);
		model.addAttribute("selectboard", board);
		model.addAttribute("boardnum", num);
		
		return "/board/content";
		// boardNo라는 파라미터 값을 가지고 와서 조회를 한 후Model 객체에 Board객체와 글 번호를 저장 후 content에 가서 값을 화면에 보여줌
	}
	
	// 글 수정하기 화면으로 이동 요청 처리 메서드 
	@GetMapping("/modify")
	public String modi(@RequestParam("boardNum") int num, Model model ) {
		System.out.println("/board/modifiy : GET");
		BoardVO board = service.getArticle(num);
//		System.out.println(board);
		model.addAttribute("selectboard", board);
		model.addAttribute("boardnum", num);	
		return "/board/modify";
		// boardNum이라는 파라미터 값을 가지고 와서 조회를 한 후 Model 객체에 Board객체와 글 번호를 저장 후 modify로 이동  후 값을 화면에 보여줌
	}
	
	// 글 수정하는 요청 처리 메서드
	@PostMapping("/modify")
	public String modi(BoardVO board, @RequestParam("boardNo") int num) {
		System.out.println("/board/modify : POST");
		service.updateArticle(board, num);
		
		return "redirect:/board/list";
		// 파라미터명과 Board객체의 변수명이 같으므로 커맨드 객체를 사용하고 boardNo라는 파라미터 값을 가지고 와서 dao(index -1 해야함)에 접근하여 값을 수정 진행
		// 수정이 끝난 후 다시 list로 요청
	}
	
	// 글 삭제하는 요청 처리 메서드
	@GetMapping("/delete")
	public String delete(@RequestParam("boardNum") int num ) {
		System.out.println("/board/delete : GET");
		service.deleteArticle(num);
		
		return "redirect:/board/list";
		// boardNum이라는 파라미터 값을 가지고 와서 해당 번호를 통해 dao(index -1해야함)에 접근헤서 삭제 진행
		// 삭제가 끝난 후 다시 list로 요청
	}
	
}
