package com.spring.myweb.util;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
	
	@Autowired
	private JavaMailSender mailSender;
	// JavaMailSender라는 타입의 값을 Container에서 자동으로 찾아서 의존성 주입

	private int authNum;
	// 인증 번호 
	
	// 난수 발생 
	public void makeRandomNumber() {
		// 난수의 범위 : 111111 ~ 999999
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("난수 : " + checkNum);
		authNum = checkNum;
	}
	
	// 회원 가입 시 사용할 이메일 양식 
	public String joinEmail(String email) {
		makeRandomNumber();
		// 난수 생성 
		
		String setFrom = "sig6774@gmail.com";
		// email-config에서 입력한 이메일 주소 
		
		String toMail = email;
		// 수신받을 이메일 
		
		String title = "회원가입 인증 이메일 입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다."+
						 "<br><br>" +
						 "인증번호는 " + authNum + "입니다." + 
						 "<br>" +
						 
						 "해당 인증 번호를 인증번호 확인란에 기입하여 주세요.";
						// 이메일 내용 
		mailSend(setFrom, toMail, title, content);
		
		return Integer.toString(authNum);
		// 정수를 문자열로 변경해서 리턴 
	}
	
	// 이메일 전송 메서드 
	public void mailSend(String setFrom, String toMail, String title, String content) {
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			// 기타 설정들을 담당할 MimeMesageHelper 객체를 생성 
			// 생성자의 매개값으로 MimeMessage 객체, bool, 문자 인코딩 설정 
			// bool 타입에서 true 매개값을 전달하면 MultiPart(이미지, 오디오도 같이) 형식의 메세지 전달 가능 
				// 값을 전달하지 않으면 단순 텍스트만 사용 
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			
			helper.setText(content, true);
			// true를 해야 html 형식으로 전송 
			
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
