package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		//lombok 테스트
//		HelloLombokTest helloLombokTest = new HelloLombokTest();
//		helloLombokTest.setData("hello");
//		String data = helloLombokTest.getData();
//		System.out.println("data = " + data);

		SpringApplication.run(JpashopApplication.class, args);
	}

}
