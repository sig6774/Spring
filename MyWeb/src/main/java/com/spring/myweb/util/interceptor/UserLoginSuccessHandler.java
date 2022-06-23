package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.command.UserVO;

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
//		Object user = modelAndView.getModel().get("loginUser");
		ModelMap mv = modelAndView.getModelMap();
		// model 객체에 있는 것을 ModelMap 타입으로 반환해줌
		
		UserVO user = (UserVO) mv.get("loginUser");
		// ModelMap객체에서 값을 가져올 수 있음
		
		System.out.println("인터셉터에서 불러온 유저 데이터 : " + user);
		
		if (user == null) {
			modelAndView.addObject("msg", "loginFail");
			modelAndView.setViewName("user/userLogin");
			// 작성한 경로로 돌아갈 수 있도록 작성 
		} else {
			System.out.println("로그인 성공");
			// 로그인을 성공했으면 session을 생성해서 로그인 유지할 수 있는 수단으로 사용 
			session.setAttribute("login", user);
			response.sendRedirect(request.getContextPath());
			// /myweb으로 리턴하는 건데 contextroot는 바뀔 수 있으므로 위처럼 작성 
		}

	}
}
