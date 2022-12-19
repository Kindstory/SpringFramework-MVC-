package com.itwillbs.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoopPasswordEncoder implements PasswordEncoder {
	// password="{noop}1234"
	// 비밀번호 암호화(인코딩) 필수 (스프링5) -> 임시로 인코딩

	private static final Logger log 
	  = LoggerFactory.getLogger(CustomNoopPasswordEncoder.class);
	
	
	@Override
	public String encode(CharSequence rawPassword) {
		// 사용자가 입력한 비밀번호를 인코딩(암호화)
		log.info(" rawPassword : " + rawPassword);
		
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword,
			               String encodedPassword) {
		// 입력한 비밀번호랑 암호화한 비밀번호가 같은지 체크
		log.info(" rawPassword : "+rawPassword+", encodedPassword : "+encodedPassword);
		
		return rawPassword.toString().equals(encodedPassword);
	}

}
