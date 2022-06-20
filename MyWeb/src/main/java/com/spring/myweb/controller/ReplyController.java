package com.spring.myweb.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.service.IReplyService;

@RestController
// Bean 등록
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private IReplyService service;
	
	// 댓글 등록 
	@PostMapping("/regist")
	public String regist(@RequestBody ReplyVO reply) {
		System.out.println("/reply/regist : POST");
		System.out.println("값 가져오는지 확인 :" + reply.toString());
		
		service.replyRegist(reply);
		return "success";
	}
	
}
