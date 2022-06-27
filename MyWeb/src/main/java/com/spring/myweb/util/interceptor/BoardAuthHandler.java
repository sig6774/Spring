package com.spring.myweb.util.interceptor;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.myweb.command.UserVO;

public class BoardAuthHandler implements HandlerInterceptor {
	//화면에서 변경, 수정, 삭제가 일어날 때, writer값을 넘겨주도록 처리
	   //게시글 수정, 삭제에 대한 권한 처리 핸들러
	   //세션값과 writer(작성자) 정보가 같다면 컨트롤러를 실행,
	   //그렇지 않다면 '권한이 없습니다.' 경고창 출력 후 뒤로가기.
	   //권한이 없습니다 경고창은 jsp에서 했었던 PrintWriter 객체를 이용하시면 됩니다.
	   //PrintWriter 객체는 response 객체에게 받아 옵니다.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("login");
		System.out.println("session에 있는 유저 객체 가져오는지 확인 : " + user);;
		// login이라는 이름의 유저의 session 가져옴 
		String writer = request.getParameter("writer");
		System.out.println("인터셉터 파라미터 가져오는지 확인 : " + writer);
		// 특정 요청으로 Controller로 가는 데이터를 가져와서 확인 
		
		if (user != null) {
			if (user.getUserId().equals(writer)) {
				// 파라미터에서 가져온 값과 session에 저장된 값이 같으면 controller로 이동하도록 
				return true;
			}
		}
		
		response.setContentType("text/html charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String html = "<script>\r\n"
				 +"alert('권한이 없습니다.'); \r\n"
				 +"history.back(); \r\n"
				 +"</script>";
		out.print(html);
		out.flush();
		
		return false;
	}
}
