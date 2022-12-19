package com.itwillbs.fintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.fintech.service.OpenBankingService;
import com.itwillbs.fintech.vo.RequestTokenVO;
import com.itwillbs.fintech.vo.ResponseTokenVO;
	


@Controller
public class OpenBankingController {

	// 객체생성
	@Autowired
	private OpenBankingService openBankingService;
	
	
	@RequestMapping(value= "/callback", method = RequestMethod.GET )
	public String getToken(RequestTokenVO requestTokenVO, Model model) throws Exception{
		
		// 인증
		System.out.println(requestTokenVO.getCode());
//		System.out.println(requestTokenVO.getScope());
//		System.out.println(requestTokenVO.getClient_info());
//		System.out.println(requestTokenVO.getState());
		
		// 토큰발급
		ResponseTokenVO responseToken= 
				openBankingService.requestToken(requestTokenVO);
		
		// 정보들고
		model.addAttribute("responseToken", responseToken);
		
		return "bank_main";
		
	}
	
	
	
	
}
