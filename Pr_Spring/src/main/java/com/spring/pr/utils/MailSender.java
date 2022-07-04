package com.spring.pr.utils;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
	
	@Autowired
	private JavaMailSender Sender; 
	// JavaMailSender라는 클래스를 container에 등록하고 의존성 주입 
	
	private int certifyNum;
	// 인증번호 변수 생성 
	
	// 난수 발생 
	public void RandomNum() {
		
		Random ran = new Random();
		// random객체 불러옴 
		
		int checkNum = ran.nextInt(888888) + 111111;
		// 난수 생성 
		certifyNum = checkNum;

	}
	
	
	// 인증번호 보낼 때 사용할 이메일 양식 
	public String EmailForm(String email) {
		RandomNum(); 
		// 난수 생성 
		
		String setEmail = "mkr0410@naver.com";
		// 인증번호를 보낼 이메일 주소 
		
		String toMail = email;
		// 인증번호를 받을 이메일 주소 
			
		String title = "Pr_Spring에서 보낸 회원가입 인증메일입니다.";
		String content = "Pr_Spring 홈페이지를 방문해주셔서 감사합니다."+
						 "<br><br>"+
						 "고객님은 회원가입을 진행 중이시며 인증번호는 " + certifyNum + "입니다." +
						 "<br>" + 
						 "해당 인증 번호를 인증번호 확인란에 기입하여 주십시요." + 
						 "<br>" + 
						 "감사합니다.";
		mailSend(setEmail, toMail, title, content);
		return Integer.toString(certifyNum);
		// 정수를 문자열로 변경 
	}
	
	// 이메일 전송 
	public void mailSend(String setEmail, String toMail, String title, String content) {
		
		try {
			MimeMessage message = Sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			
			helper.setFrom(setEmail);
			// 인증번호를 보낼 이메일 
			
			helper.setTo(toMail);
			// 인증번호를 받을 메일 
			
			helper.setSubject(title);
			// 인증메일  제목 
			
			helper.setText(content, true);
			// true를 해야 html형식으로 전송 
			
			Sender.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
