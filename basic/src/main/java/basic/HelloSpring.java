package basic;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {

		// 평소에 쓰던 객체 사용 방식 
		// 필요하면 만들어서 사용		
		SpringTest t = new SpringTest();
		t.hola();


		/* 스프링에서 객체를 가져오는 방법
			- 미리 선언해놓고 필요할 때 가져와서 사용 
			- main/resources에서 xml 파일을 생성
			- beans와 bean 태그를 활용하여 객체 등록 
			- 등록한 것을 가져와서 사용할 수 있음 
		 */
		GenericXmlApplicationContext ct = 
				new GenericXmlApplicationContext("classpath:test-config.xml");
		// classpath라는 키워드를 통해 src/main/resources영역을 쉽게 지목가능
		// test-config.xml의 내용을 ct가 가지고 있음 
		SpringTest st = ct.getBean("test", SpringTest.class);
		// ct에 등록된 이름이 test인 객체를 그 객체가 가진 형식대로 가지고 와라 라는 뜻
		st.hola();

		ct.close();

	}
}
