package com.spring.basic;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// 핸들러 어탭터가 요청에 대해 적절한 메소드를 찾기위해 @RequestMapping을 진행
	// 메소드의 결과 "home"이라는 것을 돌려받음
	// DispatcherServlet은 그것을 받아서 ViewResolver에게 다시 보내줌
	// 특정 요청에 대해서 응답을 할 수 있는 메소드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
		// 응답하고자 하는 파일 명
	}
	
}
