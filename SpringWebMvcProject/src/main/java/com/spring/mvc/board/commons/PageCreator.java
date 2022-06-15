package com.spring.mvc.board.commons;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCreator {

	// 페이지 알고리즘에 의해 이전, 다음 버튼 및 페이지 버튼 개수 및 번호를 관장할 객체
	private PageVO paging;
	// 사용자가 선택한 페이지 정보를 가지고 있는 객체 
	private int articleTotalCount;
	private int beginPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	// 한 화면에 보여질 페이지 버튼 개수
	private final int displayPageNum = 10;
	// 바꿀려면 직접 변경 
	
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
