package com.spring.pr.utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCreator {
	
	private PageVO paging;
	private int totalArticle; 
	// 전체 게시물 개수 
	
	private int endPage;
	private int beginPage;
	
	private boolean previous;
	private boolean next;
	
	private final int buttonNum = 5;
	
	private void calPage() {
		this.endPage = (int) Math.ceil(paging.getPageNum() / (double) this.buttonNum) * this.buttonNum;
		
		this.beginPage = (this.endPage - this.buttonNum) + 1; 
		
		this.previous = (this.beginPage == 1) ? false : true;
		
		this.next = this.totalArticle <= (this.endPage * this.paging.getCountPerPage()) ? false:true;
		// 게시글 전체 개수보다 끝페이지 * cpp를 한 값이 작다면 next 페이지 활성화 
		
		if (!this.next) {
			this.endPage = (int) Math.ceil(this.totalArticle / (double) this.paging.getCountPerPage());
			// next가 비활성화 되었다면 전체 페이지 개수에서 cpp를 나눠서 올림한 값이 endpage
		}
	}
	
	public void settotalAritcle(int totalcount) {
		
		this.totalArticle = totalcount;
		
		calPage();
	}
	
	// URI 파라미터를 쉽게 만들어주는 유틸 메서드 
	public String makeURI(int page) {
		UriComponents ucp = UriComponentsBuilder.newInstance().queryParam("pageNum", page)
															  .queryParam("countPerPage", paging.getCountPerPage())
//															  .queryParam("keyword", paging.getKeyword())
//															  .queryParam("condition", paging.getCondition())
															  .build();
		// uri를 컨트롤러에서 받은 searchVO객체인 paging에서 받아서 구성 
		return ucp.toUriString();
	}

}
