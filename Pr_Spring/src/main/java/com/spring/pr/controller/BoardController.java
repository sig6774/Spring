package com.spring.pr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.pr.board.service.IBoardService;
import com.spring.pr.command.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private IBoardService service; 
	
	@GetMapping("/BoardRegist")
	public String moveRegist() {
		return "boardRegist";
	}

	@PostMapping("/BoardRegist")
	public String regist(BoardVO board, RedirectAttributes ra) {
		// 커맨드 객체 이용 
		
		// System.out.println("board 가져오는지 확인 : " + board);
		// 아 잠시만 이제 로그 쓸 수 있으니깐 로그로 확인해봐야겠다 
		service.registBoard(board);
		// 요청받은 값을 db에 전달 
		
		ra.addFlashAttribute("msg", "정상 등록 처리되었습니다.");

		return "redirect:/board/boardList";
		// 등록 후 목록을 출력할 수 있도록 list로 값을 보냄 
		
	}
	
	
	// 게시물 목록 요청 
	@GetMapping("/boardList")
	public String listBoard(Model model) {
		
		List<BoardVO> listboard = service.listBoard();
		// db에서 값을 가져옴 
		System.out.println(listboard);
//		for (BoardVO b : listboard) {
//			System.out.println(b.getBNum());
//		}
		model.addAttribute("bList", listboard);
		return "/board/boardList";
	}
	
}
