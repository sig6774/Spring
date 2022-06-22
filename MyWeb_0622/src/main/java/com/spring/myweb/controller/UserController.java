package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.myweb.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service; 
	
	@PostMapping("/checkId")
	@ResponseBody
	public String check(@RequestBody String id) {
		System.out.println("/user/checkId : POST");
		System.out.println("아이디 중복 확인 요청 아이디 값 : " + id);
		
		int num = service.idCheck(id);
		System.out.println(num);
		if (num >= 1) {
			return "checkSuccess";
		}
		else {
			return "checkFail";
		}
	}
	
	// 회원가입 페이지 이동 요청 
	@GetMapping("/userJoin")
	public String moveJoin() {
		System.out.println("/user/userJoin : GET");
		
		return "/user/userJoin";
	}
	
	

}
