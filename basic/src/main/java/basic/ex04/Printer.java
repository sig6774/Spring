package basic.ex04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Printer {
	
	/*
	 @Autowired
	 - 객체를 자동 주입할 때 사용하는 annotation
	 - 스캔 명령을 통해 객체를 찾아 주입하는데 타입 이름으로 검사 
	 - 타입을 찾아내지 못하면 이름(id속성)을 통해 검색 
	 - 생성자, 메서드, 필드에 적용 가능 
	 */
	
	/*
	 @Qualifier
	 - Autowired를 사용할 때 동일 타입의 빈이 여러개 있는 경우 어떤 빈을 주입해야 하는지 선택해주는 추가 annotation
	 */
	
	@Autowired
	@Qualifier("paper2")
	private Paper paper;
	// 변수의 타입을 container에서 찾아서 자동 주입
	// @Qaulifier를 통해 container안에 특정 bean을 찾도록 해줌
	
//	@Autowired
//	public Printer(Paper paper) {
//		this.paper = paper;
//	}
	// 매개값의 타입을 container에서 찾아서 자동 주입 
	
//	@Autowired
//	public void setPaper(Paper paper) {
//		this.paper = paper;
//	}
	// setter를 통해 값 전달
	// setPaper가 없으면 Printer객체는 동작을 할 수 없음(의존성)
	// Autowired를 통해서 xml에 property를 안적어도 자동으로 스프링이 연결해줌

	public void showPaperInfo() {
		for(String info : paper.data) {
			System.out.println(info);
		}
	}
}
