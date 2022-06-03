package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// 자동으로 스프링 컨테이너에 해당 클래스의 bean을 등록하는  annotation
// bean을 등록해야 HandlerMapping이 이 클래스의 객체를 검색할 수 있음
// com.spring.basic의 하위 이므로 자동으로 bean 생성 됨
@Controller
public class RequestController {
	public RequestController() {
		System.out.println("RequestController 생성");
		// 자동 bean등록이 되어 있기 때문에 서버가 활성화되면 자동으로 생성되어 컨트롤러에 저장됨 
	}

	@RequestMapping("/request/test")
	// /request/test라는 요청이 들어오면 밑의 메소드를 실행하라 라는 뜻
	// 리턴값인 "test"는 디스패처로 가고 
	// 디스패처는 viewresolver에 보내주고 경로를 완성해주고 
	// view에 해당 파일이 있는지 확인 후 요청에 응답함
	public String testCall() {
		System.out.println("/request/test 요청이 들어옴");
		return "test";
	}

	/*
   	만약 사용자가 /request/req 요청을 보내 왔을 때
    views폴더 아래에 request폴더 안에 존재하는
    req-ex01.jsp파일을 열도록 메서드를 구성해 보세요.
	 */
	@RequestMapping("/request/req")
	// 이거는 요청 url
	public String callme() {
		System.out.println("/request/req 요청이 들어왔습니다.");
		return "request/req-ex01";
		// 메소드가 실행되었을 때 실제 디스패처에게 전달되는 값 
		// 나중에 이게 viewresolver에 가서 완벽한 url을 만들고 
		// 그걸 다시 디스패처가 안에 값이 있는지 확인한 후 있으면 화면 출력 
	}

	//	@RequestMapping(value="/request/basic01", method=RequestMethod.GET)
	// requestMapping을 작성할 때 value에 요청 url을 작성하고 method에 GET, POST 구분할 수 있음 
	@GetMapping("/request/basic01")
	public String basicGet() {
		System.out.println("/request/basic01 요청이 들어왔습니다. GET 요청");
		return "request/req-ex01";
		// 같은 파일로 보내기 때문에 print문과 url을 확인해서 이동이 잘되었는지 확인
	}

	//	@RequestMapping(value="/request/basic01", method=RequestMethod.POST)
	@PostMapping("/request/basic01")
	public String basicPost() {
		System.out.println("/request/basic01 요청이 들어왔습니다. POST 요청");
		return "request/req-ex01";
		// 같은 파일로 보내기 때문에 print문과 url을 확인해서 이동이 잘되었는지 확인
	}
}
