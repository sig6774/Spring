package com.spring.mvc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.commons.PageCreator;
import com.spring.mvc.board.commons.PageVO;
import com.spring.mvc.board.commons.SearchVO;
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
	public String writeBoard(BoardVO board, RedirectAttributes ra) {
		System.out.println("/board/write : POST");
		service.insert(board);
		ra.addFlashAttribute("msg", "regSuccess");
		return "redirect:/board/list";
		// board/list로 다시 재요청을 보내 리스트를 확인할 수 있도록 함
		// 글 등록이후 다시 list로 재요청 
		
	}

	/*
	// 검색요청을 하면 keyword와 condition값도 들어옴
	// 조건에 맞춰야하기 때문에 sql이 4가지가 될 수 있음
	// 조건에 맞게 메서드를 만들어야하기 때문에 상당히 귀찮음
	// 기존 query에 WHERE만 바꾸면 되기 때문에 상황에 맞게 query를 변경할 수 있는 동적 쿼리를 mybatis가 지원  
	
	// 페이징 처리 이후 게시물 목록 불러오기 요청 
	@GetMapping("/list")
	public String list(PageVO paging, Model model) {
		// paging을 하기 위한 값이 들어옴
		
		System.out.println("/board/list : GET");
		System.out.println("페이지 번호 : " + paging.getPage());
		List<BoardVO> listBoard = service.getArticleList(paging);
		System.out.println("페이징 처리 후 게시물 수 : " + listBoard.size());
		
		PageCreator pc = new PageCreator();
		pc.setPaging(paging);
		pc.setArticleTotalCount(service.countArticles());
		// 총 게시물의 개수를 가져와서 PageCreator 객체에 보내줌
		// setArticleTotalCount은 전체 게시물 개수를 주고 calc~ 메소드도 실행
		
		System.out.println(pc);
		model.addAttribute("articles", listBoard);
		model.addAttribute("pc", pc);
		

		return "board/list";
	}
	*/
	
	// 검색 처리 이후 게시글 목록 불러오기 요청 
//	@GetMapping("/list")
//	public String list(PageVO paging, Model model, String condition, String keyword) {
//		System.out.println("/board/list : GET");
//		
//		// 매개변수 이름과 파라미터 명이 같으면 @RequsetParam 안써도 됨
//		System.out.println("검색 조건 파라미터 불러오는지 테스트" + condition);
//		System.out.println("검색어 파라미터 불러오는지 테스트" + keyword);
//		System.out.println("페이지 번호 : "+paging.getPage());
//		
//		List<BoardVO> list = service.getArticleList(paging, condition, keyword);
//		// 게시글을 조회할 때 condition과 keyword도 보내야함
//		model.addAttribute("articles", list);
//		// 모델에 값을 넣어줌
//		
//		PageCreator pc = new PageCreator();
//		pc.setPaging(paging);
//		pc.setArticleTotalCount(service.countArticles());
//		model.addAttribute("pc", pc);
//		// 모델에 값을 넣어줌
//		return "board/list";
//	}
	
	@GetMapping("/list")
	public String list(SearchVO search, Model model) {
		System.out.println("/board/list : GET");
		
		// 매개변수 이름과 파라미터 명이 같으면 @RequsetParam 안써도 됨
		System.out.println("검색 조건 파라미터 불러오는지 테스트" + search.getCondition());
		System.out.println("검색어 파라미터 불러오는지 테스트" + search.getKeyword());
		System.out.println("페이지 번호 : "+search.getPage());
		
		List<BoardVO> list = service.getArticleList(search);
		// 게시글을 조회할 때 condition과 keyword도 보내야함
		model.addAttribute("articles", list);
		// 모델에 값을 넣어줌
		
		PageCreator pc = new PageCreator();
		pc.setPaging(search);
		pc.setArticleTotalCount(service.countArticles(search));
		model.addAttribute("pc", pc);
		// 모델에 값을 넣어줌
		
		model.addAttribute("search", search);
		// page와 keyword content를 가진 객체인 search를 model에 담아서 보내줌
		return "board/list";
	}
	
//	// 게시글 목록 불러오기
//	@GetMapping("/list")
//	public String listBoard(Model model){
//		System.out.println("/board/list : GET");
//		model.addAttribute("articles", service.getArticleList());
//		// model에 articles라는 이름으로 전체 게시물을 담아줌
//		// model은 지정된 url로 자동 이동
//		return "board/list";
//		// 특정 url로 보냄
//	}
	
	
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
public String contentBoard(@PathVariable int boardNo, Model model, @ModelAttribute("p") SearchVO search) {
		// 파라미터명과 매개변수 명이 같으면 @RequestParam 안적어도 됨
		// page도 보내는데 파라미터 이름이 객체 이름과 같으므로 model 사용
		System.out.println("/board/content : GET");
		model.addAttribute("article", service.getArticle(boardNo));
//		model.addAttribute("search", search);
		return "board/content";
	}
	
	// 게시글 수정 화면 요청 
	@GetMapping("/modify")
	public String modifyBoard(int boardNo, Model model) {
		// requestParam안붙어도 됨 왜냐면 parameter이름과 객체의 변수이름이 같으므로
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/modify";
	}
	
	// 게시글 수정 
	@PostMapping("/modify")
	public String modifyBoard(BoardVO board) {
		System.out.println("/board/modify : POST");
		service.updateArticle(board);
		return "redirect:/board/content?boardNo=" + board.getBoardNo();
		// content로 안감
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
