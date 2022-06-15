package com.spring.mvc.board;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UrlComponentTest {

	@Test
	public void testUriComp() {
		// uri를 쉽게 작성할 수 있도록 도와주는 유틸 클래스 
		// UriComponentBuilder
		
		// 원하는 파라미터와 값을 부여 
		UriComponents ucp = UriComponentsBuilder.newInstance().queryParam("page", 3)
							.queryParam("cpp", 10)
							.queryParam("keyword", "하윙")
							.queryParam("condition", "title")
							.build();
		System.out.println("/board/list" + ucp.toUriString());
	}
}
