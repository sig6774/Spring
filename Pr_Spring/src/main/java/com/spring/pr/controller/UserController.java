package com.spring.pr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.pr.command.UserVO;
import com.spring.pr.user.service.IUserService;
import com.spring.pr.utils.MailSender;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service; 
	
	@Autowired
	private MailSender mail;
	
	@GetMapping("/userJoin")
	public String moveJoin() {
		System.out.println("join 이동 요청");
		return "/user/userJoin";
	}
	
	@PostMapping("/userCheckId")
	@ResponseBody
	public String checkId(@RequestBody String id) {
		System.out.println("아이디 중복 체크 요청 ");
		if (service.checkId(id) > 0) {
			return "Fail";
		}
		else {
			return "Success";
		}
		
	}
	
	@GetMapping("/CheckMail")
	@ResponseBody
	public String certifyMail(String email) {
		// 사용자가 이메일 보내줌 
		System.out.println("인증번호를 보낼 이메일 : " + email);
		return mail.EmailForm(email);
	}
	
	@PostMapping("/userJoin")
	public String joinUser(UserVO user) {
		System.out.println("회원가입 유저 정보 확인 : " + user);
		
		String finalUserTel = user.getUserTel1() + user.getUserTel2() + user.getUserTel3();
		
		String finalUserEmail = user.getUserEmail1() + user.getUserEmail2();
		
		String finalUserAddr = user.getUserAddr1() + " " + user.getUserAddr2() + " " + user.getUserAddr3();
		
		user.setTotalUserTel(finalUserTel);
		user.setTotalUserEmail(finalUserEmail);
		user.setTotalUserAddr(finalUserAddr);
		
		System.out.println("추가 정보 삽입 확인 : " + user);
		
		service.registUser(user);
		
		return "/user/userLogin";
	}
	
	@GetMapping("/userLogin")
	public String moveLogin() {
		System.out.println("Login페이지 이동 요청 ");
		return "/user/userLogin";
	}
	
	@PostMapping("/userLogin")
	public String Login(@RequestParam("userId") String id, @RequestParam("userPw") String pw, Model model,
						HttpServletRequest request) {
		// 세션처리 하기 
		System.out.println("로그인 유저 정보 가져오는지 확인 " + id + " " + pw);
		
		UserVO loginUser = service.loginUser(id, pw);
		System.out.println(loginUser);
		
		if (loginUser == null) {
			return "/user/userJoin";
		}
		else {
			HttpSession session = request.getSession();

			session.setAttribute("login", loginUser);
			// 세션 만들기 
			// 다른 곳에서도 데이터를 사용하기 위해 
			
			model.addAttribute("user", loginUser);
			// 보내고자 하는 경로에 해당 데이터 보냄 

			return "/user/userMypage";
		}
	}
	
	@GetMapping("/userMypageInfo")
	public String moveInfo(HttpSession session, Model model) {
		System.out.println("수정페이지로 이동 요청");
		
		String id = ((UserVO) session.getAttribute("login")).getUserId();
		String pw = ((UserVO) session.getAttribute("login")).getUserPw();
		
		UserVO loginUser = service.loginUser(id, pw);
		// 세션 정보에서 값을 받아서 로그인한 유저의 정보 불러 
		
		model.addAttribute("user", loginUser);
		// 보내고자 하는 경로에 해당 데이터 보냄 

		
		return "/user/userMypageInfo";
	}
}
