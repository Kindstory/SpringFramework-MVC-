package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {

	
	private static final Logger log = LoggerFactory.getLogger(SampleController4.class);
	
	
	// http://localhost:8080/web/doE
	@RequestMapping("/doE")
	public String doE(Model model, RedirectAttributes rttr) {
		log.info(" /doE 호출 ");
		
		
		//return "/doF";
		// -> sendRedirect 동작 수행가능(주소, 화면 모두 변경)	
		// return "redirect:/doF?msg=itwill";
		
		// -> forward 동작 수행가능(주소 변경x, 화면 변경o)
		//return "forward:/doF";
		// 주소줄에 데이터 보내는거 말고, 다른 방법 -> Model 객체 생성
		
		// model.addAttribute("msg", "busan");
		
	   // rttr.addFlashAttribute("msg", "spring");    //  model이랑 다르게 rttr은 addFlashAttribute가 존재함  
													  // 주소줄에 ?파라미터가 없음. 새로고침하면 메세지 사라짐. (한 번만 데이터 표시)
		
		/* 
		 * model.addAttribute()
		 * => 전달값이 URI표시O, F5 실행시 데이터 유지O (영구적)
		 * rttr.addFlashAttribute()  -> 한 번 밖에 안되니까, 조회수가 1번 올라가고 안 올라감. 일회성의 데이터를 만들어야 할 때, 한 번 확인하고 왔다. 라는 의미
		 * => 전달값이 URI표시X, F5 실행시 데이터 유지X (일시적)
		 * */
		
	
		
		return "redirect:/doF";   // "forward:/doF"도 가능

	}
	
	@RequestMapping("/doF")
	public void doF(@ModelAttribute("msg") String msg) {
		log.info(" /doF 호출 ");
		log.info(" msg: " + msg);
		
	}
}
