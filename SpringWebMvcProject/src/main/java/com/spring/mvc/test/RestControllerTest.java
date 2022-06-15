package com.spring.mvc.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestControllerTest {
	
	
	/*
	 @ResponseBody
	 - 메서드가 리턴하는 데이터를 viewResolver에게 전달하지 않고 클라이언트에게 해당
	 데이터를 바로 응답하게 함 
	 주로 비동기 통신에서 많이 사용 
	 
	 @RestController를 사용하면 모든 메서드에 @ResponseBody를 붙인 결과와 같음
	 
	 참고 
	 	- 동기 : 순서대로 일을 처리 (순서대로 요청과 응답을 처리)
	 	- 비동기 : 번호표를 뽑는다고 생각 (순서대로 일을 처리하지 않고 특정 요청이 오게 된다면 바로 처리)
	 */
	@GetMapping("/hello")
//	@ResponseBody
	// 비동기 방식으로 데이터를 처리하고 싶으면 사용하는 annotation
	public String hello() {
		return "Hello World!";
		// rest방식은 viewresolver로 보내서 url을 만들어주는 것과 다르게 
		// 페이지 이동 없이 데이터를 요청을 보낸 화면에 던짐
	}
	
	
	@GetMapping("/hobby")
//	@ResponseBody
	public List<String> hobby() {
		List<String> hobby = Arrays.asList("축구", "운동", "잠");
		return hobby;
	}
	
	@GetMapping("/study")
	public Map<String, Object> study() {
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "java");
		subject.put("jsp", "java server pages");
		subject.put("스프링", "Spring Framework5");
		return subject;
	}
	
	@GetMapping("/person")
	public Person person() {
		Person p = new Person();
		p.setName("문경록");
		p.setAge(28);
		p.setHobby(Arrays.asList("공부", "잠", "밥"));
		return p;
		// 자바 객체를 전달했을 때 JSON으로 어떻게 변화되는지 확인 
	}
	
	// client에서 json형태로 값을 보냄 
	@PostMapping("/getObject")
	public Person getObject(@RequestBody Person person) {
		// 보내는 파라미터 key가  Person객체 변수의 이름과 같으므로 커맨드 객체 사용
		// @RequestBody라는 annotation을 사용하면 JSON형식의 데이터를 java의 객체형식으로 변환해줌
		// 해당 annotation을 사용하지 않으면 JSON을 변환하지 못하고 null이 들어옴
		
		System.out.println("/getObject 요청이 들어옴");
		System.out.println("이름 : " + person.getName());
		System.out.println("나이 : " + person.getAge());
		System.out.println("취미 : " + person.getHobby());
		// 값을 제대로 받아오는지 확인
		
		person.setAge(431);
		// 파라미터로 저장된 person객체의 값을 바꿔줌
		
		return person;
	}
	
	@GetMapping("/getPath/{id}/{cpp}/{page}")
	// 영역에 이름을 지정 
	public Map<String, Object> getPath(@PathVariable String id, 
									   @PathVariable int cpp,
									   @PathVariable int page) {
		Map<String, Object> map = new HashMap<>();
		map.put("아이디", id);
		map.put("게시물 개수 " , cpp);
		map.put("페이지 번호", page);
		
		// 경로에서 값을 가져와서 해당 메서드에서 포장해서 값을 보냄
		return map;
	}
	
}
