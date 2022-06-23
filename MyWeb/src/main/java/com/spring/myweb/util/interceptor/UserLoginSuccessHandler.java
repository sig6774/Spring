package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// controller의 login 요청 전과 후의 데이터를 가로채서 정제 
// interceptor class를 만들려면 HandlerInterceptor 인터페이스 구현 
public class UserLoginSuccessHandler implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("로그인 요청이 들어가기 전 인터셉터 발동");
		// System.out.println("파라미터 값 가져오는지 확인 " +  request.getParameter("id"));
		// 될려나
		return true;		
	}
	
	// controller에서 데이터 처리 후 디스패처에 보내기 전 가져와서 확인 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();

		System.out.println("로그인 요청 후 디스패처에게 가기 전 인터셉터 발동");
		Object user = modelAndView.getModel().get("loginUser");
		System.out.println("인터셉터에서 불러온 유저 데이터 : " + user.toString());
		if (user == null) {
			modelAndView.addObject("msg", "loginFail");
			modelAndView.setViewName("/user/userLogin");
		} else {
			session.setAttribute("userSession", user);
			response.sendRedirect("/MyWeb/home");
		}

	}
}
