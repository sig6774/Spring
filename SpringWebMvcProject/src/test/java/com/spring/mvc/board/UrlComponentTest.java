package com.spring.mvc.board;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UrlComponentTest {

	@Test
	public void testUriComp() {
		// uri를 쉽게 작성할 수 있도록 도와주는 유틸 클래스 
		// UriComponentBuilder
		
		UriComponents ucp = UriComponentsBuilder.newInstance().path("/board/list");
	}
}
