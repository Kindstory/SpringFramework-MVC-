package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

	private static final Logger log 
	  = LoggerFactory.getLogger(CommonController.class);
	
	@GetMapping("/accessError")
	public void accessDenied(Model model,Authentication auth) {
		log.info("accessDenied() 호출 ");
		log.info(" 접근 권한이 없음 ! ");
		
		model.addAttribute("msg", "접근권한 없는 사용자!");
		model.addAttribute("auth", auth);
		
	}
	
	@RequestMapping(value = "/customLogin",method = RequestMethod.GET)
	public void myLoginGET() {
		log.info(" myLoginGET() 호출 ");
		
		
	}
	
	@GetMapping("/customLogout")
	public void myLogoutGET() {
		log.info(" myLogoutGET() 호출! ");
	}
	
	@PostMapping("/customLogout")
	public void myLogoutPOST() {
		log.info(" myLogoutPOST() 호출! ");
	}
	
	
	
	
}
