package basic.ex04;

import java.util.Arrays;

import org.springframework.context.support.GenericXmlApplicationContext;


public class Main {

	public static void main(String[] args) {

		GenericXmlApplicationContext ct = new GenericXmlApplicationContext("classpath:auto-config.xml");
		Printer pt = ct.getBean("prt", Printer.class);
		pt.showPaperInfo();
		// Autowired를 통해서 Property태그를 적지 않아도 자동으로 스프링이 Printer객체안에 paper객체 넣음

		
		
		System.out.println("----------------------------------------");
		Book book = ct.getBean("book", Book.class);
		String data = Arrays.toString(book.getPaper().data);
		System.out.println(data);
		
		ct.close();
	}
}
