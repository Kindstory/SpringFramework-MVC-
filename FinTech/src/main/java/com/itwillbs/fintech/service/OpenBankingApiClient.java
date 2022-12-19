package com.itwillbs.fintech.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.itwillbs.fintech.vo.RequestTokenVO;
import com.itwillbs.fintech.vo.ResponseTokenVO;

@Service
public class OpenBankingApiClient {
	// 변수 정의
	private String client_id= "64716fd3-8a62-4814-a9fc-021e1da0fff5";
	private String client_secret = "ef40bd75-f9fc-48e3-a119-1fb4793b796f";
	private String redirect_uri= "http://localhost:8080/fintech/callback";
	private String grant_type = "authorization_code";
	
	// REST 방식 API 요청 -> 자바단에서 처리하기 위해
	private RestTemplate restTemplate; // 스프링에서 RestAPI를 사용할 수 있게끔 제공해주는 거   // 여기 담아서 넘길꺼임 
	// 헤더 정보 관리 클래스
	private HttpHeaders httpHeaders;
	
	
	public ResponseTokenVO requestToken(RequestTokenVO requestTokenVO) {
		
//		요청메시지 URL
//		
//		https : //testapi.openbanking.or.kr/oauth/2.0/token
//			
//		method : post;
//			
//		contentType : application/x-www-form-urlencoded; charset=UTF-8  => Json 형태로 받아옴. -> Ajax 처리 해야하는데 막아놓은데가 있어서 자바단에서 처리할 수 있게끔 해야함.
//			
//		code -> client_id, client_secret, redirect_uri, grant_type  -> requestTokenVO 에 넣음.	
		
		restTemplate= new RestTemplate();
		httpHeaders = new HttpHeaders();
		
		// Content-Type 지정 http header
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		
		requestTokenVO.setRequestToken(client_id, client_secret, redirect_uri, grant_type);
		// "application/x-www-form-urlencoded; charset=UTF-8"  => 이런 타입은 객체 저장 불가능 하기 때문에, 다른 타입으로 들고 가야함.
		MultiValueMap<String, String> parameters = 
				new LinkedMultiValueMap<String, String>();      // <name, value>  이름, 값
		parameters.add("code", requestTokenVO.getCode());
		parameters.add("client_id", requestTokenVO.getClient_id());
		parameters.add("client_secret", requestTokenVO.getClient_secret());
		parameters.add("redirect_uri", requestTokenVO.getRedirect_uri());
		parameters.add("grant_type", requestTokenVO.getGrant_type());
		
		// HttpHeader,HttpBody    // parameters에 담아서 감 HttpEntity   // Header 값에 MultiValueMap값을 담을꺼임. 왜냐면 "application/x-www-form-urlencoded; charset=UTF-8"는 객체저장 불가능 하니까 다른 타입인 MultiValue값으로 받았기 때문에
		HttpEntity<MultiValueMap<String, String>> param = 
				new HttpEntity<MultiValueMap<String, String>>(parameters, httpHeaders);
		
		
		
		String requestUrl="https://testapi.openbanking.or.kr/oauth/2.0/token";
		
		return restTemplate.exchange(requestUrl, HttpMethod.POST, param, ResponseTokenVO.class).getBody();
		
		
	}
	

}
