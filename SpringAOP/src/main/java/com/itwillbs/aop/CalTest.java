package com.itwillbs.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalTest {
	
	public static void main(String[] args) {
		// 주기능 사용
		Calculator c = new Calculator();
		
		c.add(100, 200);
		
		System.out.println("----------------------------------");
		
		// 주기능 + 보조기능 사용
		
		ApplicationContext CTX = new ClassPathXmlApplicationContext("AOPTest.xml");
		System.out.println(" CTX : "+CTX);
		
		// proxyCal (주기능-계산기 + 보조기능-로그)
		// => 의존관계 주입(DI)
		 Calculator cal = (Calculator)CTX.getBean("proxyCal");
		 //Calculator cal2 = CTX.getBean("proxyCal",Calculator.class);
		
		 cal.add(500, 500);
		
		
		
	}

}
