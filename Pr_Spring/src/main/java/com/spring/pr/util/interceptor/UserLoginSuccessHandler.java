package com.spring.pr.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.pr.command.UserVO;

public class UserLoginSuccessHandler implements HandlerInterceptor{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("로그인 인터셉터 동작.");
		ModelMap mv = modelAndView.getModelMap();
		UserVO vo = (UserVO)mv.get("user");
		System.out.println("interceptor vo:" + vo);
		
		//컨트롤러에서 로그인을 성공한 사람!
		if(vo != null) {
			System.out.println("로그인 성공 로직 동작.");
			//세션 데이터를 생성하여 로그인 유지를 하게 해 줌.
			HttpSession session = request.getSession();
			session.setAttribute("login", vo);
			response.sendRedirect(request.getContextPath());
		}
		else {
			modelAndView.addObject("msg", "loginFail");
			modelAndView.setViewName("/user/userLogin");
		}
		
	}
}
