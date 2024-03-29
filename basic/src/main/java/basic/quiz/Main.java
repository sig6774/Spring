package basic.quiz;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		/*
	       1. quiz-config.xml 파일을 생성(auto-config.xml 파일을 복사)
	        Monitor, Keyboard, Mouse, Computer 빈을 생성 후
	       2. Computer 클래스에서 자동 주입을 설정해 보세요.
	       (Computer는 나머지 부속품 객체들과 의존 관계에 있습니다.)
	       3. MainClass에서 xml에 등록된 빈을 얻은 후 
	       computerInfo() 메서드를 실행해 보세요. 
	       computerInfo() 메서드는 각 부속품의 info()들을 한번에 
	        출력해 주시면 됩니다.
		 */
		
		GenericXmlApplicationContext ct = new GenericXmlApplicationContext("classpath:quiz-config.xml");
		Computer com = ct.getBean("computer", Computer.class);
		// container에 저장되어 있는 bean들을 자동으로 찾아서 동작을 할 수 있게 해줌
		com.ComputerInfo();
	}

}
