package com.spring.mvc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.user.service.IUserService;

/*
 만약 컨트롤러에 비동기 통신 요청을 받는 메서드가 있다고 해서 무조건 @RestController일 필요가 없음 
 그냥 @Controller를 지정한 controller에 메서드도 @ResponseBody를 붙여주면 비동기 방식으로 
 클라이언트로 값을 바로 리턴할 수 있음
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	
	@PostMapping("/checkId")
	// 아이디 중복 여부 체크 
	public String checkId(@RequestBody String account) {
		// ajax함수는 json형태로 보내기 때문에 JSON을 문자열로 바꾸기 위해 
		// @RequestBody를 사용해서 JSON을 문자열로 변경 
		System.out.println("/user/checkId : POST");
		System.out.println("param : " + account);
		int checkNum = service.checkId(account);
		// 요청을 통해 받은 값을 통해서 아이디 검증 로직 수행 
		
		if (checkNum == 1) {
			System.out.println("아이디 중복됨");
			return "duplicated";
		} else {
			System.out.println("아이디 사용가능");
			return "Available";
		}
		// 해당 return 값을 현재 화면인 /user/checkId에 다시 보냄 
		// return 값을 ajax가 받음
	}
}
