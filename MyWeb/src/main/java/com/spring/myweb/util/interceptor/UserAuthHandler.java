package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserAuthHandler implements HandlerInterceptor{
	
	// 회원 권한이 필요한 페이지 요청이 들어왔을 때 요청을 가로채서 확인할 인터셉터 
	// 글쓰기 화면과, 마이페이지 화면에 들어가는 요청을 가로채 검사
	// 권한이 없다면 로그인 페이지로 이동 
	
	// 유저 인증은 controller로 요청이 들어가기 전 체크하는 것이 좋으므로 preHandle 사용 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 세션에서 로그인 데이터를 얻은 후 확인 진행 
		HttpSession session = request.getSession();
		
		// 세션이 있는지 체크 (없다면 다른 곳으로 보냄)
		if(session.getAttribute("login") == null) {
			// 로그인을 안했으면 session이 없으므로 null이 됨 
			response.sendRedirect(request.getContextPath() + "/user/userLogin");
			// 로그인을 안했으므로 userLogin으로 보냄
			
			return false;
		}
		else {
			return true;
			// 로그인을 한 사람은 interceptor를 통과 
		}
	}
}
