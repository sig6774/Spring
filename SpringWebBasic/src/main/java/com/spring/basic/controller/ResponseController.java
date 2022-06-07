package com.spring.basic.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.basic.model.UserVO;

@Controller
@RequestMapping("/response")
public class ResponseController {
	
	@GetMapping("/res-ex01")
	public void resEx01() {	
	}
	
	// 1.Model 객체를 사용하여 화면에 데이터 전송 
//	@GetMapping("/test")
//	public void test(@RequestParam("age") int age, Model model) {
//		// /response/test를 부를 때 model과 age값을 가져옴 
//		// 모델에 값을 담아줌 
//		model.addAttribute("age", age);
//		model.addAttribute("nick", "개");
//		// 모델은 메소드가 끝나기 전에 값을 담아놓으면 자동으로 view페이지에 보냄 
//		// 현재 void이므로 경로를 알 수 있음
//		// view에서 el을 활용하여 값을 가져올 수 있음
//	}
	
	// 2. @ModelAttribute를 사용한 화면에 데이터 전송 처리
	// @RequestParam + model.Attribute 처럼 작동 
	@GetMapping("/test")
	public void test(@ModelAttribute("age") int age) {
		// 파라미터 변수명 age를 받아서 바로 model에 넣어줌 
		// 즉 addAttribute를 작성할 필요가 없음
		// 메서드안에서 값을 사용하고 싶으면 뒤에 타입과 변수명 작성해도 됨
		System.out.println(age);
	}
	@GetMapping("/test2")
	public void test2(@ModelAttribute("info") UserVO user) {
		// Model 객체에 addAttribute를 작성하지 않고 그냥 값을 넣어줌
		
		System.out.println("매서드 내의 콘솔 출력 : " + user.toString());
	}
	
	// 3. ModelAndView 객체를 활용한 처리
	// Model 객체를 사용하면서 사용자가 보는 view도 제어하는 객체 
	@GetMapping("/test3")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("userName", "안철수");
		mv.addObject("userAge" , 30);
		// 값을 넣어줌
		
		mv.setViewName("/response/test3");
		// 보낼 경로 지정 
		return mv;
	}
	
	////////////////////////////////////////////////////////
	
	@GetMapping("/res-quiz01")
	public String res() {
		System.out.println("res-quiz01호출");
		return "/response/res-quiz01";
	}
	
	@PostMapping("/res-login")
	// post형식으로 해당 uri 받음
	public String res_quiz01(@ModelAttribute("info") UserVO user) {
		
		if (user.getUserId().equals("moon123") && user.getUserPw().equals("1234")) {
			return "/response/res-quiz02";
		} else {
			return "/response/res-quiz03";
		}
	}
	// model에 info라는 이름으로 res-login에 받은 값을 Model 객체에 저장 
	// 메소드 내에서 로직을 진행하기 위해 변수 선언 
	
	// 다른 방법 
//	@PostMapping("/res-login")
//	public String resLogin(@ModelAttribute("userId") String id, 
//						   @RequestParam("userPw") String pw) {
//		// 값을 두개 받음(id는 quiz02와 quiz03에 모두 사용되기 때문에 Model 객체에 넣어줌 
//		if (id.equals("moon123") && pw.equals("1234")) {
//			return "response/res-quiz02";
//		}else {
//			return "response/res-quiz03";
//		}
//	}
	
	/////////////////////////////////////////////////////////
	
	// Redirect 처리 
	
	// form 화면을 보여주는 메서드 
	@GetMapping("/login")
	public String login() {
		System.out.println("/login : GET요청");
		return "response/res-redirect-form";
	}
	
	@PostMapping("/login")
	// response/res-redirect-form 여기서 받은 값 
	public String login(@RequestParam("userId") String id,
						@RequestParam("userPw") String pw,
						@RequestParam("userPwChk") String pwChk, 
						RedirectAttributes ra) {
						// redirectAttribute 객체를 사용하면 return에 redirect를 사용하면 
						// 데이터를 일회성으로 전달할 때 사용하며 url뒤에 데이터가 붙지 않음  
		// form태그에서 사용자가 입력한 값을 @RequestParam("파라미터명")으로 받아옴 
		System.out.println("/login: POST 요청 발생 ");
		System.out.println("값 확인 : " + id + " " + pw + " " + pwChk);
		
		if(id.equals("")) {
//			model.addAttribute("msg", "아이디는 필수값이예요");
			// res-redirect-form으로 다시 보내줌
			
			ra.addFlashAttribute("msg", "아이디는 필수 값이예요");
			// redirect 상황에서 일회성으로 데이터를 전송할 때 사용하는 메서드 
			// url뒤에 데이터가 붙지 않고 한번 이용 후 알아서 소멸
			return "redirect:/response/login";
			// redirect: 라는 키워드로 특정 url에 다시 요청을 보냄
			// redirect 상황에서 model 객체를 사용하게 되면 model 내부의 데이터가 재 요청이 
			// 들어올 때 파라미터 값으로 붙어서 들어옴 데이터가 url 주소 뒤에 ?와 함께 노출되어 전달
			
		}else if(!pw.equals(pwChk)){
			ra.addFlashAttribute("msg", "비밀번호 확인란을 체크하세요");
			return "redirect:/response/login";
		}else {
			return null;
		}
	}
}
