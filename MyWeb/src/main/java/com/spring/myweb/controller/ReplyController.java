package com.spring.myweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.service.IReplyService;
import com.spring.myweb.util.PageVO;

@RestController
// Bean 등록
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private IReplyService service;
	
	// 댓글 등록 
	@PostMapping("/replyRegist")
	public String regist(@RequestBody ReplyVO reply) {
		// System.out.println("/reply/regist : POST");
		// System.out.println("값 가져오는지 확인 :" + reply.toString());
		
		service.replyRegist(reply);
		return "success";
	}
	
	// 페이징이 추가된 댓글 목록 
	@GetMapping("/getList/{bno}/{pageNum}")
	// 요청에 bno와 pageNum도 있다는 것을 표시 
	public Map<String, Object> getList(@PathVariable int bno, @PathVariable int pageNum){
		// @PathVariable을 사용해서 요청한 파라미터를 쉽게 가져옴
		// 페이지 정보와 댓글 목록에 대한 정보를 보내야 함으로 Map 객체를 사용

		// getList메서드는 글번호와 페이지 번호를 매개값으로 받고 Mapper 인터페이스에 각각 다른 값을 전달하기 위해 
		// Map or @Param Annotation을 사용해서 보내고 ReplyMapper.xml에 sql문을 페이징 쿼리로 작성
		
		// rest방식은 화면에 필요한 값을 여러개 보낼 때, 
		//return에 Map이나 VO 형식으로 필요한 데이터를 한번에 담아서 처리(댓글 목록 리스트와 전체 댓글 게스를 함께 전달)
		
		PageVO page = new PageVO();
		page.setPageNum(pageNum);
		// 화면에서 전달된 페이지 번호 
		page.setCpp(5); // 댓글은 한 화면에 5개씩 배치 
		
		List<ReplyVO> replyList = service.getList(page, bno);
		// page정보와 게시물 번호를 db에 보내줌
		// db로 부터 조회된 값을 다시 받음 
		// 댓글 목록 데이터 
		// System.out.println("댓글 목록 가져오는지 확인 : " + replyList.toString());
		
		int total = service.getTotal(bno);
		// System.out.println("댓글 개수 가져오는지 확인 : " + total);
		// db로 부터 조회된 전체 댓글 개수 
		
		Map<String, Object> map = new HashMap<>();
		map.put("replyList", replyList);
		map.put("total", total);
		
		return map;
		// 다시 detail페이지로 보내줌 
	}
	
	@PostMapping("/update")
	public String update(@RequestBody ReplyVO reply) {
		//커맨드 객체 사용 (파라미터이름과 변수명이 같음)
		System.out.println("/reply/update : POST");
//		System.out.println("수정 댓글 내용 확인 : " + reply.toString());
		
		int num = service.pwCheck(reply);
		// 비밀번호 확인
		// 비밀번호가 맞으면 update 진행
		if (num >= 1) {
			System.out.println("댓글 수정 진행");
			service.update(reply);
			
			return "modSuccess";
		} else {
			// 비밀번호 틀림 
		
			return "pwFail";
		}
		
	}
	
	@PostMapping("/delete")
	public String delete(@RequestBody ReplyVO reply) {
		System.out.println("/reply/delete : post");
		System.out.println("삭제 댓글 번호 : " + reply.getRno());
		
		int num = service.pwCheck(reply);
		
		if (num >= 1) {
			System.out.println("댓글 삭제 진행");
			service.delete(reply.getRno());
			
			return "delSuccess";
		} else {
			return "pwFail";
		}
	}
	
}
