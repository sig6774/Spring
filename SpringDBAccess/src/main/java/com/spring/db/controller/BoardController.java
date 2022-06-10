package com.spring.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.db.model.BoardVO;
import com.spring.db.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private IBoardService service;

	//글 작성 화면을 열어주는 메서드
	@GetMapping("/write")
	public void write() {
		System.out.println("/board/write: GET");
	}

	//작성된 글 등록 처리 요청
	@PostMapping("/write")
	public String write(BoardVO vo) {
		System.out.println("/board/write: POST");
		service.insertArticle(vo);
		return "redirect:/board/list";
	}


	//글 목록 화면 요청

	@GetMapping("/list")
	public void list(Model model) {
		System.out.println("/board/list: GET");
		model.addAttribute("articles", service.getArticles());
		
	}

	//글 내용 상세보기 요청 처리 메서드
	@GetMapping("/content")
	public void content(@RequestParam("boardNo") int boardNo, Model model) {
		System.out.println("/board/content?boardNo=" + boardNo);
		model.addAttribute("article", service.getArticle(boardNo));
	}

	//글 수정하기 화면으로 이동 요청
	//메서드 이름은 modify(), url: /board/modify -> GET
	//수정하고자 하는 글 정보를 모두 받아와서 modify.jsp로 보내 주세요.(글 번호 같이)
	@GetMapping("/modify")
	public void modify(@RequestParam("boardNo") int boardNo, Model model) {
		System.out.println("수정페이지 이동 요청! 번호: " + boardNo);
		model.addAttribute("article", service.getArticle(boardNo));
	}


	@PostMapping("/modify")
	public String modify(@RequestParam("boardNo") int boardNo, BoardVO vo) {
		System.out.println("글 수정 요청! 번호: " + vo.getBoardNo());
		service.updateArticle(vo);
		return "redirect:/board/content?boardNo=" + vo.getBoardNo();
	}
	
	
	//삭제
	@GetMapping("/delete")
	public String delete(@RequestParam("boardNo") int boardNo) {
		service.deleteArticle(boardNo);
		return "redirect:/board/list";
	}
	
	// 게시글 검색 요청 
	@GetMapping("/searchList")
	public String searchList(@RequestParam("keyword") String keyword, Model model) {
		model.addAttribute("articles", service.seachList(keyword));
		return "board/list";
	}



}


















