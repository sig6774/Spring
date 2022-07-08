package com.spring.pr.utils;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
public class PageVO {
	
	private int pageNum;
	private int countPerPage;
	
//	private String keyword;
//	private String condition; 
	
	public PageVO() {
		this.pageNum = 1;
		this.countPerPage = 10;
	}
	
	
}
