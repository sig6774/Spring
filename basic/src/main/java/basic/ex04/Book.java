package basic.ex04;

import javax.annotation.Resource;

public class Book {
	
	/*
	 @Resource
	 - 빈을 자동으로 주입하는 annotation 
	 - 필드, 메서드에만 적용 가능하며 생성자에는 적용 불가
	 - name속성을 통해 특정 bean의 아이디 지목 가능 
	 */
	@Resource(name="paper1")
	
	private Paper paper;

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	
	
}
