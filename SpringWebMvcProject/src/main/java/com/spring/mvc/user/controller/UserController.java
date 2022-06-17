package com.spring.mvc.user.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

import oracle.jdbc.proxy.annotation.Post;

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
		} 
		else {
			System.out.println("아이디 사용가능");
			return "Available";
		}
		// 해당 return 값을 현재 화면인 /user/checkId에 다시 보냄 
		// return 값을 ajax가 받음
	}
	
	// 회원가입 요청 처리 
	@PostMapping("/")
	public String regist(@RequestBody UserVO user) {
		// json으로 날라온 것을 바꿔주기 위해 @RequestBody사용 
		// json안에 property이름이 UserVO객체의 변수명과 같으므로 커맨드 객체 사용 
		System.out.println("/user/ :POST");
		service.regist(user);
		// 데이터가 DB에 잘 들어갔으면 return 
		return "joinSuccess";
	}
	
	// 로그인 요청 처리 
	@PostMapping("/loginCheck")
	public String loginCheck(@RequestBody UserVO user, /* 1번 방법 HttpServletRequest request*/
			HttpSession session, HttpServletResponse response) {
		System.out.println("/user/loginCheck : POST");
		
		// 내가 적은 것 (상당히 비효율적임)
//		int num = service.loginCheck(user.getAccount(), user.getPassword());
//		if (num == 1) {
//			System.out.println("로그인 성공");
//			return "loginSuccess";
//		} else if (num == 0) {
//			System.out.println("비밀번호 틀림");
//			return "pwFail";
//		} else {
//			System.out.println("아이디 틀림");
//			return "idFail";
//		}
		
		// 서버에서 세션 객체를 얻는 방법
		// 1. HttpServletRequest 객체 사용 (메서드의 매개변수로 넣어줌)
//		HttpSession session = request.getSession();
		
		// 2. 매개값으로 HttpSession 객체 받아서 사용 특정 메서드에 sesion정보 입력
			// 오버라이딩을 할 경우 매개변수를 마음대로 바꾸지 못해서 2번은 문제가 발생할 수 있음

		UserVO dbData = service.selectOne(user.getAccount());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 암호화된 비밀번호를 가져오기 위해
		
		if (dbData != null) {
			if (encoder.matches(user.getPassword(), dbData.getPassword())) {
				// 사용자가 입력한 비밀번호를 암호화 했을 때 기존에 저장된 암호화된 비밀번호가 같은지
				
				// 로그인을 성공했다면 로그인 성공 대상으로 세션 정보 생성 
				session.setAttribute("login", dbData);
				
				long limitTime = 60*60*24;
				// 자동로그인 유효 기간 
				
				// 로그인을 성공했고 자동 로그인 체크 시 처리해야 할 내용
				if (user.isAutoLogin()) {
					// 자동 로그인을 체크했다면 
					// 자동 로그인 희망하는 경우 쿠키를 이용하여 자동 로그인 정볼르 저장 
					System.out.println("자동 로그인 쿠키 생성 중 ...");
					// 세션 아이디를 가지고 와서 쿠키에 저장(고유한 값이 필요함)
					
					// 쿠키 생성 
					Cookie loginCookie = new Cookie("loginCookie", session.getId());
					// 이름이 loginCookie이고 값이 sessionID인 쿠키 생성 
					
					loginCookie.setPath("/");
					// 쿠키가 동작할 수 있는 유효한 url 설정
					
					loginCookie.setMaxAge((int) limitTime);
					// 쿠키가 유효할 수 있는 기간 
					
					response.addCookie(loginCookie);
					// 쿠키 추가 
					
					// 자동 로그인 유지 시간을 날짜 객체로 변환 
					// DB에 삽입하기 위해서 (밀리초)
					long expiredDate = System.currentTimeMillis() + (limitTime * 1000);
					Date limitDate = new Date(expiredDate);
					// Date 객체의 생성자에 매개값으로 밀리초의 정수를 전달해주면 날짜 형태로 변경해줌
					System.out.println("자동 로그인 만료 시간 : " + limitDate);
					
					System.out.println("session id : " + session.getId());
					service.keepLogin(session.getId(), limitDate, user.getAccount());
				}
				return "loginSuccess";
			} else {
				return "pwFail";
			}
		} else {
			return "idFail";
		}
	}
	
	// 로그아웃 처리
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes ra
			/*HttpServletResponse response*/) throws IOException {
		System.out.println("/user/logout : GET");

		// 자동 로그인 설정을 한 사용자는 쿠키를 삭제해야 완전히 로그아웃 됨 
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		// 쿠키는 request 객체 안에 존재함으로 매개변수로 request객체를 가져옴
		// 쿠키의 이름이 loginCookie라는 쿠키를 쉽게 가져옴 
		String sessionId = "none";
		
		// db에 저장된 session정보 바꿔야함 
		// 세션아이디, 만료시간
		long changeExpiredDate = System.currentTimeMillis();
		// 현재시간 
		Date chLimitDate = new Date(changeExpiredDate);
		// 밀리초로 되어 있는 것을 날짜로 바꿔줌 
		UserVO user = (UserVO) session.getAttribute("login");
		// user의 account가 필요함으로 session에서 정보를 받아옴 
		
		if (loginCookie != null) {
			// 만약 사용자가 자동 로그인을 설정했을 때 쿠키가 존재한다면 
			loginCookie.setMaxAge(0);
			// 쿠키의 수명을 0으로 바꿔 소멸 
			loginCookie.setPath("/");
			// 쿠키 소멸 끝 
			response.addCookie(loginCookie);
			
			service.keepLogin(sessionId, chLimitDate, user.getAccount());
			

			
//			service.changeLogin(sessionId, chLimitDate, user.getAccount());
//			// db에 저장된 session정보 변경 
			
		}
		
		session.invalidate();
		// 세션 삭제
		
		
		ra.addFlashAttribute("msg", "logout");
		// 요청을 보낸 header.jsp에 msg 보냄
		
//		return "redirect:/";
		// 비동기 전송이라서 화면에 그냥 데이터가 뜸
//		response.sendRedirect("/");
//		// sendRedirect로도 보낼 수 있음 
		
		// ModelAndView를 사용하게 되면 어디로 보낼 지 지정할 수 있음 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		// 로그아웃 되었으니 홈화면으로 다시 요청해서 전달 
		return mv;
	}
}
