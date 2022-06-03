package basic.ex02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		GenericXmlApplicationContext ct = 
				new GenericXmlApplicationContext("classpath:db-config.xml");

		MemberDAO dao = ct.getBean("dao", MemberDAO.class);
		// container에 dao라는 이름으로 되어 있는 것을 가져오는데 MemberDAO 타입에 맞게 가져와라 라는 뜻 
		dao.showDBInfo();
		ct.close();
	}
}
