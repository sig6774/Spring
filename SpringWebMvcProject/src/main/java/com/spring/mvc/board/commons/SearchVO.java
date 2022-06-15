package com.spring.mvc.board.commons;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO extends PageVO {
	// pageVO에 상속을 받으면 SearchVO에 변수를 작성할 필요가 없음

	private String keyword;
	private String condition;
	
}
