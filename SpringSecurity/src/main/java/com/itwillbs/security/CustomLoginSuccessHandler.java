package com.itwillbs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private static final Logger log = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info(" 로그인 성공!!!!  ");	
		
		// 로그인한 유저의 권한정보를 체크
		
		// 권한정보를 저장하는 리스트
		List<String> roleNames = new ArrayList<String>();
		
		authentication
		.getAuthorities()
		.forEach(
				authority -> roleNames.add(authority.getAuthority())
				);
		
		log.info(roleNames+"");
		
		// 권한에 따른 페이지 이동
		if(roleNames.contains("ROLE_ADMIN")) {
			log.info(" 관리자님 로그인 성공! ");
			
			response.sendRedirect("/sample/admin");
			return;			
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			log.info(" 일반사용자 로그인 성공! ");
			response.sendRedirect("/sample/member");
			return;
		}
		
		// 비회원/권한이 없는 회원 로그인성공!
		log.info("권한 없는 로그인 성공!(비회원)");
		//response.sendRedirect("/");
		response.sendRedirect("/sample/all");
		
	}
	
}
