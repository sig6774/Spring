package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.model.BirthVO;

@Controller
@RequestMapping("/birth")
public class BirthController {

	@GetMapping("/")
	public String call() {
		System.out.println("birth-form 호출");
		return "/birth/birth-form";
		// birth-form.jsp로 이동 
	}

	// 생일 정보를 result 페이지에 전달하는 요청 메서드
	@PostMapping("/birth-call")
	// /birth-call이라는 uri 요청을 받으면 
	public String check_value(@ModelAttribute("BDM") BirthVO birth) {
		// 파라미터명과 BirthVO 객체의 변수명이 같으므로 Spring에서 자동으로 값 넣어줌
		// 다른 곳에서도 사용해야 하기 때문에 BDM이라는 이름으로 저장해서 Model 객체에 넣어줌
		// 해당 메서드 안에서도 사용해야하기 때문에 변수 선언
		System.out.println(birth.getYear()+birth.getMonth()+birth.getDay());
		return "/birth/birth-result";
		// 결과 다시 돌려보냄
	}

}
