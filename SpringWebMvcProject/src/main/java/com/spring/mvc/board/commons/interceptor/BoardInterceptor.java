package com.spring.mvc.board.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// interceptor class를 만들려면 HandlerInterceptor 인터페이스를 구현 
public class BoardInterceptor implements HandlerInterceptor{
	
	
	// preHandle은 컨트롤러로 들어가기 전 처리해야 할 로직을 작성
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("게시판 인터셉터 발동 확인 : preHandle");
		// 해당 메소드는 override라서 매개변수를 바꾸거나 return 타입을 바꾸면 안됨
		HttpSession session = request.getSession();
		// HttpServletRequest 객체가 매개변수로 있으므로 getsession()으로 session가져올 수 있음 
		
		if (session.getAttribute("login") == null) {
			
			System.out.println("회원 인증 실패 ");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String htmlcode = "<script> \r\n"
					+ "alert('로그인이 필요한 페이지입니다.'); \r\n"
					+ "location.href='/'"
					+ "</script>";
			// 로그인을 하지 않으면 메인 페이지로 이동 
			
			out.print(htmlcode);
			out.flush();
			return false;
			// return을 false로 주면 controller로 요청이 전달되지 않음
		}
		System.out.println("회원 인증 성공 ");
		return true;
		// return을 true로 리턴하면 아무일도 일어나지 않고 요청이 전달
	}
	
	// postHandle은 컨트롤러를 나갈 때 공통 처리해야 할 내용을 작성 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// ModelAndView가 있으므로 Controller에서 저장한 model의 값을 볼 수 있음 
		
		System.out.println("게시판 인터셉터 발동 확인 : postHandle");
		
		System.out.println("모델 객체 내부 : " + modelAndView.getModel());
		Object data = modelAndView.getModel().get("article");
		System.out.println("article 이라는 이름의 데이터 : " + data);
		System.out.println("뷰 페이지 이름 : " + modelAndView.getViewName());
		
		// 컨트롤러에서 로직을 처리하고 나가는 흐름을 붙잡아서 모델 데이터가 제대로 전송되는지 확인하고 
		// 추가할 내용이나 수정할 내용이 있다면 모델 객체를 받아와서 추가, 수정 가능 
		// 기타 특징을 활용해서 흐름을 제어할 수 있음 (sendRedirect, viewName 수정을 통해)
	}
}
