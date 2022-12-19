package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//  AOP 관점지향프로그램이 내가 신경써야 할 일과 안 써도 될 일을 구분 하는 그런 프로그래밍인데, 거기서 나온게  Advice임. 
//  CommonExceptionAdvice는 예외를 처리하는 보조기능 객체임. 주 기능은 게시판 같은 CRUD이니까. 예외처리는 보조기능임. 

//  @ControllerAdvice : 컨트롤러에서 발생한 예외(Exception)를 전문적으로 처리하는 객체라는 의미 

@ControllerAdvice
public class CommonExceptionAdvice {
	

	private static final Logger log 
		= LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
//	@ExceptionHandler(Exception.class)
//	public void test() {
//		log.info("test()");
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public String common(Exception e, Model model) { // => 예외정보를 받아올수 있게 파라미터에 넣어줌   // 정보전달에는 모델객체나 RedirectAttributes
//		log.info(" common() 호출 ");
//		log.info(e.getMessage());
//		log.info(e.toString());
//	
//		
//		model.addAttribute("e", e.getMessage());   // model객체는 controller에서 view로 정보를 보내줄떄 쓰는거임 사실은.  
//													// 이 페이지는 컨트롤러가 아니라 예외처리 페이지임.
//	
//		
//		return "commons";    
//	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e, Model model) { // => 예외정보를 받아올수 있게 파라미터에 넣어줌   // 정보전달에는 모델객체나 RedirectAttributes
		log.info(" common(mav) 호출 ");
		log.info(e.getMessage());
		log.info(e.toString());
	
		ModelAndView mav = new ModelAndView();												// ModelAndView는 컨트롤러 뿐만아니라 예외를 포함시켜서 정보를 뷰로 전달해줄 수 있는 객체임.
		mav.setViewName("/commons");
		mav.addObject("e", e.getMessage());
		
		return mav;    
	}
	
	
	// 결국은 Controller가 예외처리를 해야함    그래서 try - catch 문 작성할꺼임.     
	// CommonExceptionAdvice 이 클래스가 이 프로젝트 전반에 나타나는 예외를 다 처리해주는 클래스임 .
	// 굳이 컨트롤러 마다 try - catch문 안 적어도 되고 
	
	
}
