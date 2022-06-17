package com.spring.mvc.user.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

// interceptor를 사용하기 위해서는 implements 사용해서 interface 만들어줌 
public class AutoLoginInterceptor implements HandlerInterceptor{
	
	@Autowired
	private IUserMapper mapper;
	// db와 연동하기 위해 mapper 가져옴 (의존성 자동 주입)
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("자동 로그인 interceptor 발동 ");
		
		// 1. 자동 로그인 쿠키가 있는지의 여부 확인
			// loginCookie의 존재 유무 확인 
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for(Cookie coo : cookies) {
//				if (coo.getName().equals("loginCookie")) {
//					// 쿠키의 이름이 자동로그인을 설정 유무를 알 수 있는 loginCookie라는 이름과 같다면 
//				}
//			}
//		}
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		// WebUtils라는 이름의 클래스의 getCookie함수를 통해 request객체와 쿠키 이름을 작성하면 원하는 쿠키를 가져옴
		
		// 자동 로그인을 신청한 사람이라면 로그인 유지를 위해 세션 데이터를 만들어줌 
		HttpSession session = request.getSession();
		if(loginCookie != null) {
			// 자동 로그인 유지를 신청한 사람
			
		// 2. DB에서 쿠키값(세션ID)과 일치하는 세션 ID 를 가진 회원 정보 조회
			// mapper와 service에서 준비한 내용을 불러와서 정보 조회 
			UserVO autoLoginUser = mapper.getUserWithSessionId(loginCookie.getValue());
			// loginCookie에는 session의 id가 들어있음 
			
			System.out.println("쿠키의 값 : " + loginCookie.getValue());
			System.out.println("db에서 가지고 온 값 : " + autoLoginUser);
			
			if (autoLoginUser != null) {
				session.setAttribute("login", autoLoginUser);
				System.out.println("세션 제작 완료!");
			}
		}
		
		
		
		// true면 컨트롤러로 요청이 들어가고 false면 요청을 막음 
		// 자동 로그인 신청 여부와 상관없이 홈 화면은 무조건 봐야함으로 true를 작성 
		return true;
	}
	

}
