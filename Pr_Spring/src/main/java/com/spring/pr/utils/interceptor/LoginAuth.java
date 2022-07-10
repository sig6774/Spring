package com.spring.pr.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginAuth implements HandlerInterceptor{
	
	// 인터셉터 특정 요청이 들어왔을 때 controller로 들어가기 전 혹은 후의 내용을 가로채 
	// 검증을 진행하기 위해 사용하는 class가 interceptor 
	
	// 특정 요청이 controller로 들어가기 전 요청을 가로채서 확인하는 것이 prehandler 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandler 인터셉터 발동 ");
			
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			response.sendRedirect(request.getContextPath() + "/user/userLogin");
			return false;
		}
		
		else {
			return true; 
		}
		// 만약 로그인을 한 사용자라면 login이라는 이름의 세션이 있을 것이므로 해당 세션이 존재한다면 
		// controller로 요청이 들어가게 진행 
		// 없다면 controller로 요청이 들어가지 않고 다시 login요청을 보냄 
		
	}
	

}
