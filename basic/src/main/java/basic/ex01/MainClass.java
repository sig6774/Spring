package basic.ex01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		Chef chef = new Chef();
		Restaurant res = new Restaurant();

		// 쉐프 지정 
		res.setChef(chef);
		
		// 호텔에 레스토랑 지정 
		Hotel h = new Hotel(res);
		// 호텔 생성 
		
		// 위와 같이 하면 연쇄적으로 객체간 연쇄적으로 명령 수행 가능 
		
		h.reserveRestaurant();
		// 호텔에 명령 내림 
		
		// 기존에는 이렇게 생성 
		
		// Spring에서는 이렇게 생성하지 않고 
		// test-config.xml에 넣어놓으면 훨씬 편하게 필요할 때 가져올 수 있음
		// hotel만 불러서 필요할 때 불러올 수 있음
		System.out.println();
		System.out.println("Spring 객체 불러오기 및 의존성 주입 확인");
		System.out.println();
		GenericXmlApplicationContext ct = new GenericXmlApplicationContext("classpath:test-config.xml");
		Hotel hotel = ct.getBean("hotel", Hotel.class);
		// test-config.xml에서 hotel이라는 값을 불러옴 
		
		hotel.reserveRestaurant();
		// Hotel 클래스에 있는 메소드 가져올 수 있음 
		// 미리 등록했고 의존성도 주입했기 때문
		// 필요할 때 꺼내올 수 있다는 것을 확인 
		
	}

}
