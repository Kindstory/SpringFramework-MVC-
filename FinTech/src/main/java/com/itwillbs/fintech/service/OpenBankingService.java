package com.itwillbs.fintech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.fintech.vo.RequestTokenVO;
import com.itwillbs.fintech.vo.ResponseTokenVO;

@Service
public class OpenBankingService {
	
	// OpenBankingApiClient 객체생성 
	private OpenBankingApiClient openBankingApiClient;
	
	
		
	// 토큰 발급 요청
	public ResponseTokenVO requestToken(RequestTokenVO requestTokenVO) {
		
		
		
		return openBankingApiClient.requestToken(requestTokenVO);
		
	}
	
	
}
