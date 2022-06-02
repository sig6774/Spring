package basic.ex01;

public class Hotel {
	
	private Restaurant res; 
	// hotel은 레스토랑을 가지고 있음 
	public Hotel(Restaurant res) {
		System.out.println("호텔 생성 ");
		this.res = res;
		// hotel 객체가 생성될 때 restaurant객체를 넣어줘서 지정
		
	}
	
	public void reserveRestaurant() {
		System.out.println("레스토랑을 예약");
		res.orderDinner();
		// 호텔은 가지고 있는 레스토랑에 명령을 넣음
	}
}
