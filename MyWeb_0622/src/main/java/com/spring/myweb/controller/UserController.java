package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	@PostMapping("/userJoin")
	public String Join(UserVO user) {
		System.out.println("/user/userJoin : POST");
		
		System.out.println("join요청 유저 값 가져오는지 확인 : " + user.toString());
		service.join(user);
		
		return "/user/userLogin";
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
		return mailService.joinEmail(email);
	}
	
	

}
