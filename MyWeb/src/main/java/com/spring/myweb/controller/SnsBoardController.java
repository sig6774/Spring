package com.spring.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.command.UserVO;
import com.spring.myweb.snsboard.service.ISnsBoardService;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {
	
	@Autowired
	private ISnsBoardService service;
	
	@GetMapping("/snsList")
	public void snsList() {
		
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file, 
						 @RequestParam("content") String content, HttpSession session) {
		// FormData 객체가 값을 보내는 것을 받음 
		System.out.println("snsBoard/upload : POST");
		String writer = ((UserVO) session.getAttribute("login")).getUserId();
		// session에 저장된 id를 가져옴 
		
		// 날짜별로 폴더를 생성해서 파일을 관리 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 원하는 형태로 지정 
		Date date = new Date();
		String filelocation = sdf.format(date);
		
		// 저장할 폴더 경로 
		String uploadPath = "Users\\user\\Desktop\\upload" + filelocation;
		
		// 폴더가 없으면 생성하도록 진행 
		File folder = new File(uploadPath);
		if(!folder.exists()) {
			// 폴더가 존재하지 않는다면 
			folder.mkdirs();
			// 상위 폴더까지 모두 생성 
		}
		
		// 파일 명을 고유한 랜덤 문자로 생성
		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString().replaceAll("-", "");
		
		// 확장자 추출 로직  
		String fileRealName = file.getOriginalFilename();
		// 원본 파일 명
		
		String fileExtention = fileRealName.substring(fileRealName.indexOf("."), fileRealName.length());
		// 원본 파일의 확장자 추출
		System.out.println("저장할 폴더 경로 : " + uploadPath);
		System.out.println("실제 파일 명 : " + fileRealName);
		System.out.println("폴더 명  : " + filelocation);
		System.out.println("확장자 : " + fileExtention);
		System.out.println("고유 랜덤 문자 : " + uuids);
		
		String fileName = uuids + fileExtention;
		
		// 업로드한 파일을 서버 컴퓨터 내에 지정한 경로에 실제 저장 
		File saveFile = new File(uploadPath + "\\" + fileName);
		
		// 예외처리가 발생함으로 try catch 사용 
		try {
			file.transferTo(saveFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// db에 insert 작업 진행 
		SnsBoardVO snsBoard = new SnsBoardVO(0, writer, uploadPath, filelocation, fileName, fileRealName, content, null);
		// null을 줘도 mybatis-config에서 null을 다른 값으로 바꿔줬기 때문에 상관 x 
		service.insert(snsBoard);
		// db에 값을 넣음
				
		return "Success";
	}
}
