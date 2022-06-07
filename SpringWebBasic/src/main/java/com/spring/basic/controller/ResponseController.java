package com.spring.basic.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/response")
public class ResponseController {
	
	@GetMapping("/res-ex01")
	public void resEx01() {	
	}
	
	// 1.Model 객체를 사용하여 화면에 데이터 전송 
	@GetMapping("/test")
	public void test(@RequestParam("age") int age, Model model) {
		// /response/test를 부를 때 model과 age값을 가져옴 
		// 모델에 값을 담아줌 
		model.addAttribute("age", age);
		model.addAttribute("nick", "개");
		// 모델은 메소드가 끝나기 전에 값을 담아놓으면 자동으로 view페이지에 보냄 
		// 현재 void이므로 경로를 알 수 있음
		// view에서 el을 활용하여 값을 가져올 수 있음
	}
	
	// 2. @ModelAttribute를 사용한 화면에 데이터 전송 처리
	// @RequestParam + model.Attribute 처럼 작동 
	@GetMapping("/test")
	public void test(@ModelAttribute("age") int age) {
		// 파라미터 변수명 age를 받아서 바로 model에 넣어줌 
		// 즉 addAttribute를 작성할 필요가 없음
		// 메서드안에서 값을 사용하고 싶으면 뒤에 타입과 변수명 작성해도 됨
		System.out.println(age);
		
	}
	
}
