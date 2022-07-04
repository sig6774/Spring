package com.spring.pr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		service.registUser(user);
		
		return "/user/userLogin";
	}
	
}
