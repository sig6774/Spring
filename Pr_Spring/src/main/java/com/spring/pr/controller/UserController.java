package com.spring.pr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.pr.command.UserVO;
import com.spring.pr.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@GetMapping("/userJoin")
	public String join() {
		return "/user/user_join";
	}

	//회원 가입 처리
	@PostMapping("/UserJoin")
	public String join(UserVO vo, RedirectAttributes ra) {
		System.out.println("param: " + vo);
		vo.setTotalUserTel();
		vo.setTotalUserEmail();
		vo.setTotalUserAddr();
		service.join(vo);
		ra.addFlashAttribute("msg", "joinSuccess");
		return "redirect:/user/userLogin";
	}
	
	@GetMapping("/userLogin")
	public String login() {
		return "/user/user_login";
	}
	
	@PostMapping("/UserLogin")
	public String login(String userId, String userPw, Model model) {
		model.addAttribute("user", service.login(userId, userPw));
		return "/user/user_login";
	}
	
	
}
