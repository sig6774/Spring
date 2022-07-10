package com.spring.pr.utils.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.pr.command.UserVO;

public class UpDel implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		System.out.println("인터셉터 발동!");
		UserVO user = (UserVO) session.getAttribute("login");
		String writer = request.getParameter("writer");
		System.out.println("인터셉터 파라미터 가져오는지 확인 : " + writer);
		
		if (user != null) {
			if (user.getUserId().equals(writer)) {
				return true;
			}
		}
		
		response.setContentType("text/html charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String html = "<script> \r\n"
					 +"alert('권한이 없습니다.'); \r\n"
					 +"history.back(); \r\n"
					 +"</script>";
		out.print(html);
		out.flush();
		
		return false;
		
	}

}
