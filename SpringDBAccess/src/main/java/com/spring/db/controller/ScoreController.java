package com.spring.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.db.model.ScoreVO;
import com.spring.db.service.IScoreService;

@Controller
@RequestMapping("/score")
public class ScoreController {
	
	// Controller는 Service가 무조건 필요하므로 (종속관계)
	// 컨트롤러와 서비스 계층 사이의 의존성 자동 주입을 위해 변수 선언 
	@Autowired
	// autowired를 통해 bean등록한 것을 자동으로 객체 주입
	private IScoreService service;
	
	
	// 점수 등록 화면을 열어주는 처리를 하는 메서드 
	@GetMapping("/register")
	// /score/register 요청이 들어오면 해당 메서드 실행 
	public String register() {
		System.out.println("/score/register : GET");
		return "score/write-form";
		// register 요청이 들어오면 GET방식으로 score/write-form으로 보내줌
	}
	
	// write-form에서 post방식으로 온 데이터를 처리하는 메서드 
	@PostMapping("/register")
	public String register(ScoreVO scores) {
		// write-form에서의 값의 파라미터 명은 scoreVO의 변수명과 같으므로 커맨드 객체 활용 
		System.out.println("/score/register : POST");
		
		System.out.println(scores.toString());
		
		// Service에 scores를 전달해야함
		// 위에서 생성한 service를 통해 연결
		
		service.insertScore(scores);
		return "score/write-result";
	}
	
	// 점수 전체 처리하는 메서드
	@GetMapping("/list")
	public String list(Model model) {
		// 데이터를 보내야 하기 때문에 Model 객체가 필요 
		System.out.println("/score/list : GET");
//		List<ScoreVO> sList = service.listScore();
		// service에 전체 리스트 값을 가져와라 
		model.addAttribute("sList", service.listScore());
		// 모델 객체에 값을 담아줌 
		// 보낼 경로를 작성하게 되면 Model 객체는 자동으로 이동
		
		return "score/list";
	}
	
	// 점수를 삭제하는 요청을 처리하는 메서드
	@GetMapping("/delete")
	public String delete(@RequestParam("stuNum") int stuNum, 
						RedirectAttributes ra) {
		// delete요청이 들어올 때 stuNum라는 이름의 파라미터 값이 들어오기 때문에 requestParam을 사용
		// RedirectAttributes 객체를 활용해서 redirect(다시 요청) 진행
		
		System.out.println("/score/delete : GET");
		service.deleteScore(stuNum);
		ra.addFlashAttribute("message", "학번이 " + stuNum +" 님의 정보가 삭제가 완료되었습니다.");
		// redirect할 때 표시할 메세지도 객체에 포함시킴 
		return "redirect:/score/list";
		// 삭제를 진행한 후 다시 redirect를 통해 list에 다시 요청을 보내도록 함 
	}
	
	// 특정 사람의 점수를 조회하는 메서드
	@GetMapping("/search")
	public String search() {
		System.out.println("/score/search : GET");
		return "score/search";
	}
	
	// 점수 개별 조회 처리 요청 메서드
	@GetMapping("/selectOne")
	public String select(@RequestParam("stuNum") int stuNum,
						Model model,
						RedirectAttributes ra) {
		// 파라미터를 받고 조회가 된다면 조회 결과를 보낼 Model객체, 조회가 되지 않는다면 redirect 객체
		
		System.out.println("/score/selectOne : GET");
		ScoreVO score = service.selectScore(stuNum);
		// 조회가 된다면 score는 null이 아님
//		System.out.println(score);
		
		if (score == null) {
			// 객체가 없다면 
			ra.addFlashAttribute("message", "입력하신 " + stuNum + "의 정보가 없습니다.");
			return "redirect:/score/search";
			// 다시 요청 
		}
		else {
			// 객체가 존재한다면
			model.addAttribute("score", score);
			return "score/search-result";
		}
	}
}
