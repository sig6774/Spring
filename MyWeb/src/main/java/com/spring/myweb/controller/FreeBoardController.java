package com.spring.myweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		// 요청받은 데이터의 name속성 값이 board의 변수 이름과 같으므로 커맨드 객체 이용해서 @RequestParam을 하나씩 하지 않아도 됨  
		System.out.println("/freeBoard/freeRegist : POST");
		
		System.out.println("게시물 값 가져오는지 확인" + board.toString());
		
		service.regist(board);
		ra.addFlashAttribute("msg", "정상 등록 처리되었습니다.");
		// 다시 요청을 보낼 때 msg라는 이름으로 값을 보냄 
		return "redirect:/freeBoard/freeList";
	}
	
	
	// 목록 
	@GetMapping("/freeList")
	public String freeList(PageVO page, Model model) {
		// 페이지의 정보가 들어있는 객체가 들어옴
		// page객체가 들어옴으로 mapper에서 page안의 변수의 값에 따라 조회할 데이터가 달라지므로 중요 
		
		System.out.println("/freeboard/freeList : GET");
		System.out.println("page : " + page.toString());
		// 페이지 들고 오는지 확인 
		
		List<FreeBoardVO> allList = service.getList(page);
		// db에서 조회된 결과를 List<FreeBoardVO>형태로 가져옴 
		
		model.addAttribute("boardList", allList);
		// 모델에 조회된 결과를 담아서 보냄  
		
		
		PageCreator pcv = new PageCreator();
		pcv.setPaging(page);
		pcv.setArticleTotalCount(service.getTotal(page));
		// 전체 게시물의 개수를 set해줘야 begin, end page 등의 정보를 만들 수 있음(그렇게 만들었기 때문에)
		
		System.out.println("pcv 객체 확인 : " + pcv.toString());
		
		model.addAttribute("pcv", pcv);
		// 모델에 데이터를 담아서 보내줌
		return "/freeBoard/freeList";
		
	}
	
	// 상세보기 
	@GetMapping("/freeDetail/{bno}")
	public String freeContent(@PathVariable int bno, Model model, PageVO page) {
		// 파리미터 이름과 변수 이름이 같으면 ()안적고 넣을 수 있음
		// 파라미터 명이 bno라는 것을 받아와서 상세보기 구현 
		System.out.println("/freeBoard/freeDetail : GET");
		System.out.println("게시물 상세보기 , 게시물 번호 가져오는지 확인 : " + bno);
		
		System.out.println("페이지 정보 가지고 오는지 확인 : " + page.toString());
		model.addAttribute("p", page);
		
		FreeBoardVO board = service.getContent(bno);
		// 가져온 bno값을 통해 게시판 정보를 가져옴 
		System.out.println("게시물 DB에서 가져오는지 확인 : " + board.getBno());
		model.addAttribute("board", board);
		return "/freeBoard/freeDetail";
	}
	
	// 게시물 수정
	@GetMapping("/freeModify")
	public String freeMoveModi(@RequestParam("bno") int bno, Model model) {
		System.out.println("/freeBoard/freeModify : GET");
		System.out.println("게시물 수정 , 게시물 번호 가져오는지 확인 : " + bno);
		FreeBoardVO board = service.getContent(bno);
		
		model.addAttribute("board", board);
		return "/freeBoard/freeModify";
	}
	
	// 게시물 수정 
	@PostMapping("/freeModify")
	public String freeModi(FreeBoardVO upBoard, RedirectAttributes ra) {
		System.out.println("/freeBoard/freeModify : POST");
		System.out.println("수정하고 싶은 값 받오는지 확인 : " + upBoard.toString());
		service.update(upBoard);
		
		ra.addFlashAttribute("msg", "수정완료");
		return "redirect:/freeBoard/freeDetail/"+upBoard.getBno()+"/";
	}
	
	// 게시물 삭제
	@PostMapping("/freeDelete")
	public String freeDelete(@RequestParam("bno") int bno, RedirectAttributes ra) {
		System.out.println("/freeBoard/freeDelete : POST");
		System.out.println("게시물 번호 가져오는지 확인 : " + bno);
		service.delete(bno);
		
		ra.addFlashAttribute("msg", "게시글이 정상적으로 삭제되었습니다.");
		return "redirect:/freeBoard/freeList";
		// 다시 목록으로 돌아가게 해줌
	}
	
}
