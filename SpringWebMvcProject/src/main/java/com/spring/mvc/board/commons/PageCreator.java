package com.spring.mvc.board.commons;



import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCreator {

	// 페이지 알고리즘에 의해 이전, 다음 버튼 및 페이지 버튼 개수 및 번호를 관장할 객체
	private SearchVO paging;
	// 사용자가 선택한 페이지 정보를 가지고 있으며 keyword와 condition도 가지고 있음
	private int articleTotalCount;
	private int beginPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	// 한 화면에 보여질 페이지 버튼 개수
	private final int displayPageNum = 10;
	// 바꿀려면 직접 변경 
	
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
	
	// 페이징 알고리즘을 수행할 메서드 
	private void calcDataOfPage() {
		this.endPage = (int) (Math.ceil(this.paging.getPage() / (double) this.displayPageNum )) * this.displayPageNum;
		
		this.beginPage = (this.endPage - this.displayPageNum) + 1;
		
		this.prev = (beginPage == 1) ? false:true;
		
		this.next = (this.endPage * this.paging.getCpp() >= this.articleTotalCount) ? false:true;
		
		if(!next) {
			this.endPage = (int) Math.ceil(this.articleTotalCount / (double) this.paging.getCpp());
		}
	}
	
	public void setArticleTotalCount(int articleTotalCount) {
		this.articleTotalCount = articleTotalCount;
		
		calcDataOfPage();
	}
	
}
