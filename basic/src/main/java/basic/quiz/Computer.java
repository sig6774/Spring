package basic.quiz;

import org.springframework.beans.factory.annotation.Autowired;

public class Computer {
	
	@Autowired
	private Keyboard keyboard;
	// container에서 타입이나 이름으로 bean 찾아줌 
	
	@Autowired
	private Mouse mouse;
	// container에서 타입이나 이름으로 bean 찾아줌 

	@Autowired
	private Monitor monitor;
	// container에서 타입이나 이름으로 bean 찾아줌 

	// container에서 객체를 찾기 위해서 각 변수마다 @Autowired를 해주면 spring에서 container로 가서 타입을 바탕으로 객체를 찾고 찾지 못하면 이름으로 찾음
		
	public void ComputerInfo() {
		System.out.println("*** 컴퓨터 정보");
		keyboard.info();
		mouse.info();
		monitor.info();
	}

}
