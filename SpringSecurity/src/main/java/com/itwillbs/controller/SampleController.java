package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sample/*")
public class SampleController {
	
	private static final Logger log 
	    = LoggerFactory.getLogger(SampleController.class);
	
	
	//  /sample/all -> 로그인 X, 페이지 접속
	//	@RequestMapping(value = "/all",method = RequestMethod.GET)
	// http://localhost:8080/controller/sample/all
	// http://localhost:8080/sample/all
	@GetMapping(value = "/all")
	public void allGet() {
		log.info(" allGet() 호출 ");
	}
	
	//  /sample/member -> 로그인 O
	//http://localhost:8080/controller/sample/member
	//http://localhost:8080/sample/member
	@GetMapping(value = "/member")
	public void memberGet() {
		log.info(" memberGet() 호출 ");
	}
	
	//  /sample/admin -> 로그인O,관리자O 
	//http://localhost:8080/controller/sample/admin
	//http://localhost:8080/sample/admin
	@GetMapping(value = "/admin")
	public void adminGet() {
		log.info(" adminGet() 호출 ");
	}

}
