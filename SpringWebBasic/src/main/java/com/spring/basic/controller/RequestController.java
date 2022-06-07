package com.spring.basic.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.basic.model.UserVO;


// 자동으로 스프링 컨테이너에 해당 클래스의 bean을 등록하는  annotation
// bean을 등록해야 HandlerMapping이 이 클래스의 객체를 검색할 수 있음
// com.spring.basic의 하위 이므로 자동으로 bean 생성 됨
@Controller
@RequestMapping("/request")
// 컨트롤러 자체에 공통 URI 맵핑하여 /request를 작성하지 않아도 됨 
public class RequestController {
	public RequestController() {
		System.out.println("RequestController 생성");
		// 자동 bean등록이 되어 있기 때문에 서버가 활성화되면 자동으로 생성되어 컨트롤러에 저장됨 
	}

//	@RequestMapping("/request/test")
	@RequestMapping("/test")
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
//	@RequestMapping("/request/req")
	@RequestMapping("/req")
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
//	@GetMapping("/request/basic01")
	@GetMapping("/basic01")
	public String basicGet() {
		System.out.println("/request/basic01 요청이 들어왔습니다. GET 요청");
		return "request/req-ex01";
		// 같은 파일로 보내기 때문에 print문과 url을 확인해서 이동이 잘되었는지 확인
	}

	//	@RequestMapping(value="/request/basic01", method=RequestMethod.POST)
//	@PostMapping("/request/basic01")
	@PostMapping("/basic01")
	public String basicPost() {
		System.out.println("/request/basic01 요청이 들어왔습니다. POST 요청");
		return "request/req-ex01";
		// 같은 파일로 보내기 때문에 print문과 url을 확인해서 이동이 잘되었는지 확인
	}
	
	//////////////////////////////////////////////////////////
//	@GetMapping("/request/join")
	@GetMapping("/join")
	// /request/join요청이 들어오면 view 폴더 밑의 request/join.jsp로 가라 라는 메소드
	// RequestCOntroller는 공통적으로 /request를 사용하기 때문에 Controller에 /request을 맵핑 진행	
	public void register() {
		System.out.println("/request/join : GET");
//		return "request/join";
		// getmapping에서 적은 uri과 return 값과 같으면 return을 안적어도 됨 
		// void로 선언하면 요청이 들어온 uri값을 viewresolver에게 전달 
	}
	// 요청 uri 주소가 같더라도 전송 방식에 따라서 맵핑을 다르게 진행하기 때문에 
	// 같은 주소를 사용하는 것이 가능
	// ex : GET -> 화면처리, POST -> 입력값 처리 
	
	/*
	 입력 값 처리 방법 
	 1.전통적인 jsp/servlet 방식의 파라미터 읽기 처리 방법
	  - HttpServletRequest 객체 활용 (jsp에서 활용하던 방식)
	  - 거의 안씀
	
	
	@PostMapping("/join")
	public void register(HttpServletRequest request) {
		System.out.println("/request/join : POST");
		// join.jsp의 form태그에서 값을 해당 메서드로 리턴을 하도록 했음
		
		System.out.println("id : " + request.getParameter("userId"));
		System.out.println("pw : " + request.getParameter("userPw"));	
		System.out.println("hobby : " + Arrays.toString(request.getParameterValues("hobby")));
		// 체크박스는 여러개이므로 배열로 받아와서 진행 
		// 값이 잘 들어오는 것을 확인
	}
	*/
	
	/*
	 입력 값 처리 방법 
	 2. @RequestParam annotation을 이용한 요청 파라미터 처리
	  - @RequestParam("파라미터 변수명") 값을 받아서 처리할 변수
	  	
	 
	@PostMapping("/join")
	public void register(@RequestParam("userId") String id,
						 @RequestParam("userPw") String pw,
						 @RequestParam(value="hobby", required = false, defaultValue = "no hobby person") List<String> hobbies) {
		System.out.println("id : " + id + " pw : " + pw + " hobby : " + hobbies);
		// @RequestParam을 통해서 값을 받아올 수 있음
		// @RequestParam("파라미터 변수명") 해당 메서드에서 사용하고 싶은 타입과 변수명 이렇게 작성하면 됨
		// 배열 타입의 값이 없을 때 에러가 날 수 있으므로  @RequestParam의 매개값으로 required와 defaultValue를 지정하여 값이 들어오지 않았을 때 
		// 대체할 수 있는 값으로 지정할 수 있음
	}
	*/
	
	/*
	 입력 값 처리 방법
	 3. 커맨드 객체를 활용한 파라미터 처리
	  - 파라미터 데이터와 연동되는 VO 클래스가 필요
	 */
	
	@PostMapping("/join")
	public void register(UserVO user) {
		System.out.println("id : " + user.getUserId() + " pw : " + user.getUserPw() + " hobby : " + user.getHobby());
		// 한글은 깨짐 왜냐면 post방식으로 보내기 때문이며 setcharacter~~하는 것보다는 spring에서 제공하는 filter패키지를 web.xml에 넣어줌
	}
	
	/////////////////////////////////////////////////////////////
	
	
	
	@GetMapping("/quiz")
	public String quiz() {	
		System.out.println("quiz페이지 이동 요청 ");
		return "request/req_quiz";
	}
	
	@PostMapping("/quiz")
	public String quiz(UserVO user) {
		if (user.getUserId().equals("abc1234") & user.getUserPw().equals("aaa1111")) {
			// UserVO객체의 변수명과 파라미터의 name이 값으므로 UserVO객체 사용 
			// 요청 파라미터의 값과 지정한 문자열 두개가 동시에 같으면 특정 로직 수행 
			System.out.println("로그인 성공");
			return "request/login-success";
			// 디스패처에게 값을 보내고 디스패처가 viewResolver에 return값을 보내서 uri완성
		}else {
			System.out.println("로그인 실패");
			return "request/login-fail";
		}
	}
	// PostMapping에는 해당 메서드에서 처리할 요청 uri를 작성 
	// 이후 메서드 작성 이후 어느 파일로 보낼 것인지 작성
	
}
