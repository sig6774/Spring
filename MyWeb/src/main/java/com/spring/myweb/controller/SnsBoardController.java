package com.spring.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.command.SnsLikeVO;
import com.spring.myweb.command.UserVO;
import com.spring.myweb.snsboard.service.ISnsBoardService;
import com.spring.myweb.util.PageVO;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {
	
	@Autowired
	private ISnsBoardService service;
	
	
	
	@GetMapping("/snsList")
	public void snsList() {
		
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(SnsBoardController.class);
	// log를 출력하기 위해 사용
	
	private static final Logger logger = LoggerFactory.getLogger(SnsBoardController.class);
	// 자동 생성 추가해서 쉽게 생성 가능 
	
	// 비동기 통신 
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
		String uploadPath = "C:\\Users\\user\\Desktop\\upload\\" + filelocation;
		// String uploadPath = "/Users/sig6774/Desktop/Web/" + filelocation;
		
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
		File saveFile = new File(uploadPath + "/" + fileName);
		
		// 예외처리가 발생함으로 try catch 사용 
		try {
			file.transferTo(saveFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// db에 insert 작업 진행 
		SnsBoardVO snsBoard = new SnsBoardVO(0, writer, uploadPath, filelocation, fileName, fileRealName, content, null, 0);
		// null을 줘도 mybatis-config에서 null을 다른 값으로 바꿔줬기 때문에 상관 x 
		service.insert(snsBoard);
		// db에 값을 넣음
				
		return "Success";
	}
	
	// 비동기 통신 
	// 가져올 글 목록 
	@GetMapping("/getList")
	@ResponseBody
	public List<SnsBoardVO> getList(PageVO paging){
		paging.setCpp(5);
		System.out.println("/snsBoard/getList : GET");
		// 한페이지당 보여줄 게시물 개수 
		System.out.println(paging.getPageNum());
		List<SnsBoardVO> snsList = service.getList(paging);
		logger.info("/snsBoard/getList : GET");
		
		// 글목록을 불러올 때 마다 각 게시물의 좋아요 개수를 출력해주는 로직 
		// 반복문으로 각 게시물 마다 좋아요 개수를 설정해줌 
		for (SnsBoardVO svo : snsList) {
			svo.setLikeCnt(service.likeCnt(svo.getBno()));
			System.out.println(svo);
		}
		return snsList;
	}
	
	// 게시글의 이미지 파일 전송 요청
	// img 태그에 의해 요청이 들어오고 있음 
	// snsList.jsp페이지가 로딩되면서 글 목록을 가져오고 있고 js를 이용해서 화면을 꾸밀 때 
	// img 태그의 src에 작성된 요청 url을 통해 자동으로 요청이 들어옴 
	// 요청을 받아서 경로에 지정된 파일을 보낼 예정
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileLocation, String fileName){
		System.out.println("/snsBoard/display : GET");
		System.out.println("fileName : " + fileName + "fileLocation : " + fileLocation);
		

		
		File file = new File("C:\\Users\\user\\Desktop\\upload\\" + fileLocation + "\\" + fileName);
		//File file = new File("/Users/sig6774/Desktop/Web/" + fileLocation + "/" + fileName);

		System.out.println(file);
		
		ResponseEntity<byte[]> result = null;
		HttpHeaders headers = new HttpHeaders();
		// 응답 헤더 파일에 여러가지 정보를 담아서 전송하는 것도 가능 
		try {
			// 응답 헤더에 값을 넣음 
			// probeContentType : 파라미터로 전달받은 파일의 타입을 문자열로 변환해주는 메서드
			// 사용자에게 보여주고자 하는 데이터가 어떤 파일인지를 검사해서 응답상태 코드를 다르게 리턴할 수 있음
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			
			// ResponseEntity<>(응답 객체에 담을 내용, 헤더에 담을 내용, 상태 메세지)
			// FileCopyUtils : 파일 및 스트림 데이터 복사를 위한 간단한 유틸리티 메서드 집합체 
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			// FileCopyUtils.copyToByteArray(경로) : 경로를 byte배열로 변환 
			// ResponseEntity에 보내고자 하는 값들을 전달 
			// ResponseEntity는 응답에 관련된 여러 정보를 담아서 보낼 수 있음 
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 비동기 통신 
	@GetMapping("getDetail/{bno}")
	@ResponseBody
	public SnsBoardVO detail(@PathVariable int bno) {
		System.out.println("/snsBoard/getDetail : GET");
		System.out.println("detail 파라미터 가져오는지 확인 : " + bno);
		
		SnsBoardVO board = service.getDetail(bno);
		return board;
	}
	
	@PostMapping("delete/{bno}")
	@ResponseBody
	public String delete(@PathVariable int bno, HttpSession session) {
		System.out.println("/snsBoard/delete : POST");
		System.out.println("delete 파라미터 가져오는지 확인 : " + bno);
		
		SnsBoardVO board = service.getDetail(bno);
		UserVO user = (UserVO) session.getAttribute("login");
		
		if (user == null || !user.getUserId().equals(board.getWriter())) {
			// 사용자가 다르면 삭제 못하게 막음 
			
			return "noAuth";			
		}
		
		// 세션에 저장된 사용자와 현재 사용자가 같은지 즉, 같은 사용자가 삭제를 수행할려고 하는지 검증하는 로직 
		service.delete(bno);
		
		// 글이 삭제되었다면 로컬에 저장된 파일도 삭제 
		File file = new File(board.getUploadpath() + "\\" + board.getFilename());
		// 지워야하는 파일의 로컬 경로 
		
		return file.delete() ? "Success" : "fail";
		// 파일 삭제 메서드
		
	}
	
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<byte[]> download(String fileLoca, String fileName){
		System.out.println("/snsBoard/download : GET ");
		System.out.println("filename : " + fileName);
		System.out.println("fileLoca : " + fileLoca);
		
		File file = new File("/Users/sig6774/Desktop/Web/" + fileLoca + "/" + fileName);
		// File 객체를 사용 
		
		ResponseEntity<byte[]> result = null;
		
		//응답하는 본문을 브라우저가 어떻게 표시해야 할 지 알려주는 헤더 정보를 추가합니다.
        //inline인 경우 웹 페이지 화면에 표시되고, attachment인 경우 다운로드를 제공합니다.
        
        //request객체의 getHeader("User-Agent") -> 단어를 뽑아서 확인
        //ie: Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko  
        //chrome: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36
        
        //파일명한글처리(Chrome browser) 크롬
        //header.add("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") );
        //파일명한글처리(Edge) 엣지 
        //header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //파일명한글처리(Trident) IE
        //Header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " "));
		
		HttpHeaders header = new HttpHeaders();
		
		header.add("Content-Disposition",  "attachment; filename=" + fileName);
		// 응답 헤더 파일에 content-disposition을 attachment로 준다면 브라우저 내에서 다운로드로 처리 
		// filename= 파일명.확장자 로 전송 
		// attachment라는 키워드를 적어야 download가 가능 
		// 특정 이름으로 값을 header에 저장 
		
		try {
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// 좋아요 버튼 클릭 처리 
	@PostMapping("/like")
	@ResponseBody
	public String likeConfirm(@RequestBody SnsLikeVO like) {
		// JSON으로 값이 들어오기 때문에 @RequestBody와 커맨드 객체를 사용해서 JSON 타입을 객첼 변경 
		System.out.println("/snsBoard/like : POST ");
		System.out.println("좋아요 기능 값을 가져오는지 확인 : " + like);
		
		// 좋아요 버튼은 버튼이 하나임으로 버튼을 클릭 유무에 따라 좋아요 선택 및 취소를 뜻
		
		int result = service.searchLike(like);
		// 좋아요를 눌렀다면 1이 오고 좋아요를 누르지 않았다면 0이 옴 
		System.out.println("좋아요 유무 확인 : " + result);
		if(result == 0) {
			// 좋아요를 누르지 않았다면 해당 정보를 db에 저장 
			service.createLike(like);
			return "like";
		}
		else {
			// 좋아요를 눌렀으므로 db에서 해당 값 삭제 
			service.deleteLike(like);
			return "delete";
		}
	}
	
	// 회원이 글 목록 진입시 좋아요 게시물 수 체크 
	@PostMapping("/listLike")
	@ResponseBody
	public List<Integer> listLike(@RequestBody String userId){
		System.out.println("/snsBoard/listLike : POST");
		System.out.println("좋아요 게시물 체크 : " + userId);
		
		List<Integer> likeList = service.listLike(userId);
		System.out.println(likeList);
		return likeList;
	}
}
