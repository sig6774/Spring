package com.spring.myweb.controller;

import javax.servlet.http.HttpSession;

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
	// 이메일 인증을 하기 위한 객체를 의존성 등록 
	
	// 도로명 주소 승인 키 : devU01TX0FVVEgyMDIyMDYyMjE2MTc1MDExMjcxNjE=
	
	// 아이디 중복 확인
	@PostMapping("/checkId")
	@ResponseBody
	// RestController가 아닌 경우에는 @ResponseBody를 붙여야 비동기 통신이 가능
	public String check(@RequestBody String id) {
		// client에서 보낸 요청의 데이터 (비동기 통신)
		System.out.println("/user/checkId : POST");
		System.out.println("아이디 중복 확인 요청 아이디 값 : " + id);
		
		int num = service.idCheck(id);
		// 중복 확인 테스트 
		
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
		
		UserVO loginUser = service.login(id, pw);
		// 일단 session이나 그런건 나중에 
		/*
		 * if (loginUser != null ) { System.out.println("가져온 유저 정보 확인 : " +
		 * loginUser.toString()); }
		 */
		

		
		/* user 정보를 model에 담아서 리턴은 /user/userLogin으로 셋팅 
		 util 패키지 안에 인터셉터(UserLoginSuccessHandler)를 생성해서 
		 UserLoginSuccessHandler는 로그인 처리 이후 실행되는 핸들러를 오버라이딩 해서 
		  모델의 객체의 값이 null인지 아닌지 확인하고 null이라면 msg라는 이름으로 loginFail이라는 문자를 담아서 
		 userLogin.jsp 파일로 응답하도록 viewName을 세팅하고 null이 아니라면 세션 만들어서 홈 화면으로 이동
		 */
		model.addAttribute("loginUser", loginUser);
		
		
		return "/user/userLogin";
		// interceptor에서 디스패처에 가기전 가로채서 검증을 끝내고 거기서 요청을 보내기 때문에 controller에 있는 
		// return은 안먹
	}
	
	// MyPage 이동 요청 
	@GetMapping("/userMypage")
	public String moveMyPage(HttpSession session, Model model) {
		
		// 세션 데이터에서 id를 뽑아야 유저 정보를 가져올 수 있으므로 session을 매개변수로 하고 
		// 값을 보내야하기 때문에 model도 매개변수로 지정 
		
		String id = ((UserVO) session.getAttribute("login")).getUserId();
		// session을 가지고 와서 해당 session에서 id를 추출  
		
		UserVO user = service.getInfo(id);
		System.out.println("join의 결과 : "  + user);
		model.addAttribute("userInfo", user);
		return "/user/userMypage";
	}
	
	@PostMapping("/userUpdate")
	public String update(UserVO user, RedirectAttributes ra) {
		// 커맨드 객체를 활용하여 값을 받아옴 
		System.out.println("마이페이지에서 데이터 가져오는지 확인 " + user.toString());
		service.updateUser(user);
		
		ra.addFlashAttribute("msg", "수정이 완료되었습니다.");
		
		return "redirect:/user/userMypage";
	}
	

	
	

}
