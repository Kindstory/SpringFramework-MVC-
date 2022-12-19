package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwillbs.domain.MemberVO;

public class SampleController3 {

	
	private static final Logger log = LoggerFactory.getLogger(SampleController3.class);
	
	// http:// localhost:8080/web/doD
	// http:// localhost:8080/web/doD?id=admin
	
	@RequestMapping(value = "/doD")
	public void doD(@ModelAttribute("id") String id) {
		
		log.info(" /doD() 호출 -> doD() 호출 -> views/doD.jsp 호출");
		
	}
	
	//  [doD1] 주소 호출 ->  test.jsp 페이지에 정보 출력
	//  /doD1?email=test@test.com
	
	@RequestMapping("/doD1")
	public String doD1(@ModelAttribute("email") String email) {
	
	  log.info(" doD1() 호출 -> test.jsp 이동 " );
		
	  return "test";
	}
	
	//  /doBean -> test.jsp (MemberVO 객체 전달)
	
	
	// http://localhost:8080/web/doBean?userid=admin&pw=1234(x)
	// http://localhost:8080/web/doBean?userid=admin&userpw=1234
	
	@RequestMapping(value= "/doBean")		
	public String doBeanTest(MemberVO vo, Model model) {
								// @ModelAttribute 생략형태
								// => 뷰페이지에서 호출할 이름X
								// => 전달하는 객체이름의 첫글자를 소문자로 변경해서 
								//    이름으로 사용한다.
		log.info("/doBean 호출 -> doBeanTest()실행 -> test.jsp 호출");
		log.info("vo : " + vo);
		
		// /doBean 호출했을떄
		// 디비 sql결과로 만들어진 값(가정)
		
		MemberVO DBVO = new MemberVO();
		DBVO.setUserid("user01");
		DBVO.setUserpw("1234");
		DBVO.setUsername("사용자");
		DBVO.setUseremail("user01@naver.com");
		
		// Model														// Model이라는 객체는 버스같이 정보를 전달하기 위해 왔다갔다 하는 객체임.
		// => 스프링에서 제공하는 상자객체(정보를 담아서 전달목적)
		// 컨트롤러의 정보를 뷰페이지로 보내는 객체                 
		
		// request.setAttribute(이름, 값); 와 유사한 형태의 동작
		model.addAttribute("DBVO", DBVO);
		// Key, value 쌍으로 정보를 저장해서 사용 
		model.addAttribute(DBVO);
		// => 전달되는 타입의 클래스명의 
		// 첫글자를 소문자로 변경해서 이름으로 사용
		
		
		
		return "test";
	}
	
	
	
}
