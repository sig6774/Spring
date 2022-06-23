package com.spring.myweb.controller;

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

import com.spring.myweb.command.UserVO;
import com.spring.myweb.user.service.IUserService;
import com.spring.myweb.util.MailSendService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private MailSendService mailService;
	
	// 도로명 주소 승인 키 : devU01TX0FVVEgyMDIyMDYyMjE2MTc1MDExMjcxNjE=
	
	// 아이디 중복 확인
	@PostMapping("/checkId")
	@ResponseBody
	// RestController가 아닌 경우에는 @ResponseBody를 붙여야 비동기 통신이 가능
	public String check(@RequestBody String id) {
		System.out.println("/user/checkId : POST");
		System.out.println("아이디 중복 확인 요청 아이디 값 : " + id);
		
		int num = service.idCheck(id);
		System.out.println(num);
		if (num >= 1) {
			return "checkFail";
		}
		else {
			return "checkSuccess";
		}
	}
	
	
	// 회원가입 페이지 이동 요청 
	@GetMapping("/userJoin")
	public String moveJoin() {
		System.out.println("/user/userJoin : GET");
		
		return "/user/userJoin";
	}
	
	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	// 비동기로 진행 
	public String mailCheck(String email) {
		// email : userJoin에서 비동기로 보낸 파라미터 값
		System.out.println("이메일 인증 요청 들어옴");
		System.out.println("인증 이메일 : " + email);
		
		// 받은 이메일을 MailSendService에서 전송 등의 로직을 진행
		// 화면에다가 비동기로 보내줌
		return mailService.joinEmail(email);
	}
	
	// 회원 가입 처리 
	@PostMapping("/userJoin")
	public String Join(UserVO user, RedirectAttributes ra) {
		// 커맨드 객체 사용 
		System.out.println("/user/userJoin : POST");
		
		System.out.println("join요청 유저 값 가져오는지 확인 : " + user.toString());
		service.join(user);
		
		ra.addFlashAttribute("msg", "joinSuccess");
		// 보낼 경로에 msg라는 이름으로 값을 보냄 
		return "redirect:/user/userLogin";
		// 해당 경로로 재요청 
	}
	
	// 로그인 페이지 이동 
	@GetMapping("/userLogin")
	public String moveLogin() {
		System.out.println("/user/userLogin : GET");
		
		return "/user/userLogin";
	}
	
	// 로그인 검증 
	@PostMapping("/userLogin")
	public String Login(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		System.out.println("/user/userLogin : POST");
		
		UserVO user = service.login(id, pw);
		// 일단 session이나 그런건 나중에 
		System.out.println("가져온 유저 정보 확인 : " + user.toString());

		
		/* user 정보를 model에 담아서 리턴은 /user/userLogin으로 셋팅 
		 util 패키지 안에 인터셉터(UserLoginSuccessHandler)를 생성해서 
		 UserLoginSuccessHandler는 로그인 처리 이후 실행되는 핸들러를 오버라이딩 해서 
		  모델의 객체의 값이 null인지 아닌지 확인하고 null이라면 msg라는 이름으로 loginFail이라는 문자를 담아서 
		 userLogin.jsp 파일로 응답하도록 viewName을 세팅하고 null이 아니라면 세션 만들어서 홈 화면으로 이동
		 */
		model.addAttribute("loginUser", user);
		
		
		return "/user/userLogin";
	}
	

	
	

}
