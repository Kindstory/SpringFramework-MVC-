package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/itwill/*") => 각각의 컨트롤러를 구분가능

@Controller   // Controller기 때문에 주소랑 메서드를 매핑할 수 있다.
//@RequestMapping("/itwill/*")
public class SampleController2 {
	
	// https://localhost:8080/web/doC
	// https://localhost:8080/web/itwill/doC
	
	// https://localhost:8080/web/doC?msg=busan
	
	// @ModelAttribute("파라미터이름")
	// 페이지 요청시 전달되는 파라미터 이름의 정보를 사용해서 
	
	// 1) 문자열 변수에 저장
	// 2) 방금 저장한 정보를 뷰페이지로 전달(자동으로 처리)
	
	//  원래 JSP에서는 request.setAttribute("msg", msg); 이런식으로 처리했는데, 안 해도 됨 스프링에서는
	
	private static final Logger log = LoggerFactory.getLogger(SampleController2.class);
	
	
	@RequestMapping("/doC")
	public String doC(@ModelAttribute("msg") String msg) {
		log.info("doC() 실행");
		log.info(" msg : " + msg);
		return "itwill";
	}
	
	
	// doC1 주소를 호출해서 doA.jsp 페이지에 
	// 정보 출력
	// 호출주소 :  /doC1?name=itwill&tel=0518030909

	@RequestMapping("/doC1")
	public String doC1(@ModelAttribute("name") String name, 
			@ModelAttribute("tel") String tel) {
		
		log.info("testC() 호출");
		
		return "doA";
	}
	
	
	
}


	//=> 메서드의 리턴타입이 String일때,
	// 리턴되는 문자열.jsp 페이지를 호출(자동연결)

	// Controller에서 매핑할 수 있는 타입은 딱 2가지 void, String
	// int, double 형태의 동작 사용 X
