package com.spring.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.myweb.command.MultiUploadVO;
import com.spring.myweb.command.UploadVO;

@Controller
@RequestMapping("/fileupload")
public class UploadController {

	@GetMapping("/upload")
	public void form() {
		System.out.println("/fileupload/upload : GET");
	}

	@PostMapping("/upload_ok")
	public String upload(@RequestParam("file") MultipartFile file) {
		// file이라는 이름의 데이터를 MultipartFile라는 인터페이스 형태로 받음 

		try {
			String fileRealName = file.getOriginalFilename();
			// 파일 원본 명 
			long size = file.getSize();
			// getSize() : 파일에 대한 크기

			System.out.println("파일 이름 : " + fileRealName);
			System.out.println("파일 크기 : " + size);

			// DB에는 파일 경로를 저장하고 실제 파일은 서버 컴퓨터의 로컬 경로에 저장하는 방식 
			String uploadFolder = "C:\\uploadtest\\upload";
			// 바탕화면에 폴더 만들 예정 
			File folder = new File(uploadFolder);
			// File이라는 객체로 경로를 만듬 
			if(!folder.exists()) {
				// 지정한 경로에 원래 폴더가 존재하지 않는다면 
				folder.mkdirs();
				// 즉 폴더가 존재하지 않는다면 해당 경로에 폴더를 생성 
			}

			// 저장할 파일의 확장자 
			String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
			// substring을 통해 업로드 파일 확장자 추출
			/*
		        파일 업로드 시 파일명이 동일한 파일이 이미 존재할 수도 있고,
		        사용자가 업로드하는 파일명이 영어 이외의 언어로 되어있을 수 있음

		        그리고 타 언어를 지원하지 않는 환경에서는 정상 동작이 되지 않음으로 (리눅스)
		        고유한 랜덤 문자를 통해 DB와 서버에 저장할 파일명을 새롭게 만들어 줌*/

			// 고유한 랜덤 문자 생성 
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());

			String[] uuids = uuid.toString().split("-");
			// 중간에 -이 존재하기 때문에 -없애야함 
			System.out.println("생성된 고유 문자열 : " + uuids[0]);
			System.out.println("확장자명 : " + fileExtension);
			// 생성된 고유 문자열로 사용을 할 예정 

			// 업로드 받은 파일을 만들어주는 과정 
			folder = new File(uploadFolder + "\\" + uuids[0] + fileExtension);

			file.transferTo(folder);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		// 매개변수로 받은 File 객체를 이용해 실제 업로드 받은 파일 저장 

		return "fileupload/upload_ok";
	}

	@PostMapping("/upload_ok2")
	public String upload2(MultipartHttpServletRequest files) {
		// 여러 파일이 controller로 들어오기 때문에 MultipartHttpServletRequest 인터페이스를 통해 
		// 가져올 수 있음 

		// 서버에서 파일을 저장할 경로 
		String uploadFolder = "C:\\uploadtest\\upload";

		List<MultipartFile> fileList = files.getFiles("files");
		// getFiles안에는 Controller로 들어오는 파라미터 이름을 작성하면 list형태로 받을 수 있음

		for (MultipartFile file : fileList) {
			try {
				String fileRealName = file.getOriginalFilename();
				// 파일 이름 가져오기 
				long size = file.getSize();
				// 파일 크기 가져오기 

				System.out.println("파일 이름 : " + fileRealName);
				System.out.println("파일 크기 : " + size);

				File saveFile = new File(uploadFolder + "/" + fileRealName);
				// File객체를 사용해서 경로 지정 

				file.transferTo(saveFile);
				// 위에서 지정한 경로로 값을 보냄 
			}
			// 반복문을 활용하여 값을 넣을 수 있음 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "fileupload/upload_ok";
	}


	@PostMapping("/upload_ok3")
	public String upload3(@RequestParam("file") List<MultipartFile> fileList) {
		// 받을 때 list를 선언해서 받아옴 

		String uploadFolder = "C:\\uploadtest\\upload";
		for (MultipartFile file : fileList) {
			try {
				String fileRealName = file.getOriginalFilename();
				// 파일 이름 가져오기 
				long size = file.getSize();
				// 파일 크기 가져오기 

				System.out.println("파일 이름 : " + fileRealName);
				System.out.println("파일 크기 : " + size);

				File saveFile = new File(uploadFolder + "/" + fileRealName);
				// File객체를 사용해서 경로 지정 

				file.transferTo(saveFile);
				// 위에서 지정한 경로로 값을 보냄 
			}
			// 반복문을 활용하여 값을 넣을 수 있음 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "fileupload/upload_ok";
	}
	
	@PostMapping("/upload_ok4")
	public String upload4(MultiUploadVO files) {
		// 커맨드 객체 활용 
		// uploadVO객체가 여러개인 값을 받음
		String uploadFolder = "C:\\uploadtest\\upload";
		List<UploadVO> fileList = files.getFileList();
		// uploadVO가 들어잇는 리스트 형태로 가져옴 
		
		for(UploadVO file : fileList) {
			
			try {
				String fileName = file.getName();
				String realName = file.getFile().getOriginalFilename();
				
				String fileExtension = realName.substring(realName.lastIndexOf("."), realName.length());
				
				File saveFile = new File(uploadFolder + "/" + fileName + realName);
				file.getFile().transferTo(saveFile);
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "fileupload/upload_ok";
	}
}
