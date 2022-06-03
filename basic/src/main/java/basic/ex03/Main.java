package basic.ex03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		GenericXmlApplicationContext 
		ct = new GenericXmlApplicationContext("classpath:prototype-config.xml");
		Person hong = ct.getBean("person", Person.class);
		System.out.println(hong.getAge());
		System.out.println(hong.getName());

		// 추가 객체가 필요하다고 가정 
		Person lee = ct.getBean("person", Person.class);
		lee.setAge(30);
		lee.setName("이짱구");

		System.out.println("hong 이름 : " + hong.getName());
		System.out.println("hong 나이 : " + hong.getAge());
		System.out.println("lee 이름 " + lee.getName());
		System.out.println("lee 나이 : " + lee.getAge());
		// 동일한 객체에서 값이 변경되고 있으므로 하나의 객체를 사용하고 있는것을 확인할 수 있음 

		System.out.println("hong 주소 값 " + hong);
		System.out.println("lee 주소 값 " + lee);
		System.out.println("hong과 lee은 같은 객체입니까 ? " + (hong == lee));
		// 주소값 확인을 통해서도 같은 객체인 것을 확인할 수 있음

		// xml에서 scope 속성을 사용했더니 싱글톤 특성이 사라짐
		// scope에서 prototype를 통해서 객체를 생성할 때 마다 다른 객체를 만들어줌
	}
}
