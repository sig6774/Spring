package com.spring.mvc.board;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
								  "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
// controller는 servlet에 controller에 대한 설정이 있으므로 그것도 가져옴 

@WebAppConfiguration
@Log4j
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	/*
	 WebApplicationContext
	 - test환경에서 가상의 DispatcherServlet을 사용하기 위한 객체 자동 주입
	 - 가상 환경을 만들어서 controller가 요청을 받을 수 있도록 해줌
	 - Spring에서 제공되는 servlet들을 사용할 수 있도록 정보를 저장하는 객체
	 - @WebAppConfiguration 통해서 가져올 수 있음
	 */
	
	// MockMvc는 웹 어플리케이션을 서버에 배포하지 않아도 스프링 MVC 동작을 
	// 구현할 수 있는 가상의 구조를 만들어 줌
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		
		// 가상 구조 세팅 
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		// 가상의 mvc 구조 완성 
		// Spring container에 등록된 모든 bean과 의존성 주입까지 load해서 사용 가능
	}
	// 위의 3개는 Spring에 있는 모든 Controller를 사용할 때의 Setup
	
// 만약 하나의 Controller만 사용하고 싶을 때 
//	@Autowired
//	private BoardController controller;
	
	// private MockMvc mockMvc;
	// setup메서드에 지정 
//	this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	// 이렇게 지정하면 하나의 Controller만 테스트 가능 
	
	@Test
	public void testList() throws Exception {
		log.info(
					this.mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
					.andReturn()
					.getModelAndView()
					.getModelMap()
					// 가상환경에서도 get 요청을 보낼 수 있고 요청을 받아서 model을 꺼내서 확인하는 작업
					// getModelMap()은 map형식으로 확인할 수 있게 해주는 것`
					// controller에서 return 했을 때 요기로 와서 연산이 제대로 되는지 확인
					// 즉, Controller가 제대로 동작하는지 확인
				);
	}
	
	@Test
	public void testWrite() throws Exception {
		
		String viewPage = this.mockMvc.perform(MockMvcRequestBuilders.post("/board/write")
				.param("content", "controller 테스트")
				.param("writer", "controller 테스트")
				).andReturn().getModelAndView().getViewName();
		// 매개변수가 있는 메서드를 테스트할 때에는 .param("이름", "값")을 통해 넣어줄 수 있음
		// getViewName()을 통해 어디로 return되는지 확인
				
		log.info(viewPage);
		// controller를 통해 실제 요청이 되었고 db에 들어가는 것을 확인
		// 이 뜻은 Mapper와 연결되어 실제로 진행된 것 
	}
	
	   //게시글 상세보기 요청 넣어보기.
	   //컨트롤러에서는 DB에서 가지고 온 글 객체를 model에 담아서 jsp로 이동시킬 것입니다.
	   //42번글을 보여달라는 요청을 넣으시고, 요청 결과가 들어있는 model을 출력해 보세요.
	   // /board/content -> get
	@Test
	public void testContent() throws Exception {
		log.info(this.mockMvc.perform(MockMvcRequestBuilders.get("/board/content")
				// 해당 요청을 Controller에 보냄
				.param("boardNo", "35"))
				// 파라미터가 있으므로 파라미터도 추가 
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}
	
	   //5번글의 제목과 내용을 수정하는 요청을 보낼 예정입니다.
	   //전송방식은 post 방식입니다.
	   //수정 후 이동하는 페이지는 해당 글의 상세보기 페이지입니다.
	   //컨트롤러가 리턴하는 viewName을 출력해 주세요. ("/board/modify")
	@Test
	public void testModify() throws Exception {
		String viewName = this.mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("title", "test title 수정 테스트123")
				.param("content", "test content 수정 테스트123")
				.param("boardNo", "5")
				).andReturn().getModelAndView().getViewName();
		
		log.info(viewName);
	}

	   //42번글을 삭제하세요.
	   //전송 방식은 post방식이고, 이동하는 곳은 목록 요청이 재요청될 것입니다.
	   //viewName을 출력해 주세요.   
	@Test
	public void testDelete() throws Exception {
		String viewname = this.mockMvc.perform(MockMvcRequestBuilders.post("/board/delete")
				.param("boardNo", "33"))
				.andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info(viewname);
	
	}
}
