package basic.ex01;

public class Restaurant {
	
	private Chef chef;
	// 레스토랑은 쉐프를 가지고 있음 
	public Restaurant() {
		System.out.println("레스토랑 생성");
	}
	
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	public void orderDinner() {
		System.out.println("저녁 식사를 주문받습니다.");
		chef.cook();
		// 레스토랑이 보유한 쉐프에 명령을 넣음
	}
}
