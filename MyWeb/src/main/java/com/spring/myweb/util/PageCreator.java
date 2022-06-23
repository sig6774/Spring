package com.spring.myweb.util;

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
    private int articleTotalCount;
    // PageVO에서 받아오는 값들이며 이것들을 통해 연산 수행 
    
	private int endPage;
	private int beginPage;
	private boolean prev;
	private boolean next;
	
	private final int buttonNum = 5;
	
	private void calcDataOfPage() {
		this.endPage = (int) Math.ceil(paging.getPageNum() / (double) this.buttonNum) * this.buttonNum;
		
		this.beginPage = (this.endPage - this.buttonNum) + 1;
		
		this.prev = (this.beginPage == 1) ? false : true;
		
		this.next = this.articleTotalCount <= (this.endPage * this.paging.getCpp()) ? false : true;
		
		if (!this.next) {
			this.endPage = (int) Math.ceil(this.articleTotalCount / (double) this.paging.getCpp());
		}
	}
	
   //컨트롤러가 총 게시물의 개수를 PageCreator에게 전달한 직후에 
   //바로 페이징 버튼 알고리즘이 돌아갈 수 있도록 setter를 커스텀.
   public void setArticleTotalCount(int articleTotalCount) {
      this.articleTotalCount = articleTotalCount;
      
      calcDataOfPage();
   }
   
	// URI 파라미터를 쉽게 만들어주는 유틸 메서드 
	public String makeURI(int page) {
		UriComponents ucp = UriComponentsBuilder.newInstance().queryParam("page", page)
															  .queryParam("cpp", paging.getCpp())
															  .queryParam("keyword", paging.getKeyword())
															  .queryParam("condition", paging.getCondition())
															  .build();
		// uri를 컨트롤러에서 받은 searchVO객체인 paging에서 받아서 구성 
		return ucp.toUriString();
	}
	   
}
