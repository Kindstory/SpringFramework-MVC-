package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberVO;


//@RestController : REST방식으로 처리 설정 (@ResponseBody 리턴되는 타입으로 처리)


@RestController  // framework 4버전 이상만 쓸 수 있음.
@RequestMapping("/rest/*")
public class RestController1 {
	
	

	private static final Logger log 
		= LoggerFactory.getLogger(RestController1.class);
	
	@RequestMapping("/test1")
	public @ResponseBody String Test1() throws Exception{ // 이전 버전에서는 @ResponseBody 저렇게 썼음.
		log.info("Test1() 호출");
		
		// 문자열 데이터(리소스)를 생성  
		 
		return "ITWILL";    // 개발자 도구 들어가보면 HTML로 저장되어있음.
	}
	
	@RequestMapping("/test2")
	public @ResponseBody MemberVO Test2() throws Exception{ // 이전 버전에서는 @ResponseBody 저렇게 썼음.
		log.info("Test2() 호출");
		
		
			MemberVO vo = new MemberVO();
			vo.setId("admin");
			vo.setPw("1234");
			vo.setEmail("admin@admin.com");
			vo.setName("Chris");
			
		
		// 원래는 객체를 바로 못 받음.
		// jackson-databind 라이브러리 넣어주면 받을 수 있음.
		
		
		// 객체 리턴 -> 객체를 JSON타입으로 변경(jackson-databind 라이브러리)   
		
		// 원래는 컨트롤러에서 객체 리턴 안됨.
		
		
		
		 
		return vo;    
	}

	@RequestMapping("/test3")    // 따로 설정안했으니 다 GET 방식임 기본설정
	public List<MemberVO> test3() throws Exception{
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		for(int i =0; i<5; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("admin" + i);
			vo.setPw("1234"+i);
			vo.setEmail("admin@admin.com");
			vo.setName("Chris"+i);
			
			memberList.add(vo);
		}
		
		// JSON 문법의 대괄호는 [배열]  중괄호는 {객체}   객체 => key, value -> Map형식과 유사
		
		return memberList;
	}
	
	@RequestMapping("/test4")    // 따로 설정안했으니 다 GET 방식임 기본설정
	public Map<Integer, MemberVO> test4() throws Exception{
		
		Map<Integer, MemberVO> memberMap = new HashMap<Integer, MemberVO>();
		
		
		for(int i =0; i<5; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("admin" + i);
			vo.setPw("1234"+i);
			vo.setEmail("admin@admin.com");
			vo.setName("Chris"+i);
			
			memberMap.put(i, vo);
		}
		
		return memberMap;
	}
	
		// http://localhost:8080/controller/rest/test5
		// http://localhost:8080/controller/rest/test5/1000
		@RequestMapping(value = "/test5/{num}")
		public int test5(@PathVariable("num") int num) throws Exception {
			
			log.info("num : "+num);
			// 서비스 동작 호출(num)
			
			return num;
		}
		
		
		//@RequestBody : 전달되는 정보(객체)를 JSON -> 객체 
		//@ResponseBody : 전달된 정보(객체)를 객체 -> JSON
		@RequestMapping(value = "/info",method = RequestMethod.POST)
		public void testInfo(@RequestBody MemberVO vo) throws Exception {
			
			log.info("testInfo() 실행");
			log.info(vo+"");
			
		}
		
		// http://localhost:8080/controller/rest/testResp
		@RequestMapping("/testResp")
		public ResponseEntity<Void> testResponse() throws Exception{
			
			log.info("testResponse() 정상실행");
			
//			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//			return new ResponseEntity<Void>(HttpStatus.OK);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// http://localhost:8080/controller/rest/testResp2
		@RequestMapping("/testResp2")
		//public ResponseEntity<String> testResponse2() throws Exception{
		public ResponseEntity<List<MemberVO>> testResponse2() throws Exception{
//			if(true) {
//				return new ResponseEntity<String>("test!!",HttpStatus.OK);
//				
//			}else {
//				return new ResponseEntity<String>("test!!",HttpStatus.NOT_FOUND);
//			}

			return new ResponseEntity<List<MemberVO>>(new ArrayList<MemberVO>(),HttpStatus.NOT_FOUND);
			
		}
	
	
}
