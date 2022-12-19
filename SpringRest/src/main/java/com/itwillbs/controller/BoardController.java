package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

/*
 *  HttpMethod 
 *  
 *  GET : 조회 (Select)
 *  POST : 추가/처리 (Create)
 *  PUT : 전체 수정 (Update) 
 *  PATCH : 일부 수정 (Update)
 *  DELETE : 삭제 (Delete)
 *  
 *  
 *  * REST방식의 페이지 요청 방식
 *    [  /작업명/기본키 + 메서드 + 데이터  ]
 *    
 *    작업명 : 요청하는 작업의 종류(member/board/goods/order....)
 *    기본키 : 요청 작업의 대상을 나타내는 기본값 (/member/insert)
 *    메서드 : 요청기능
 *    데이터 : 기능 실행에 필요한 데이터(JSON)
 *    
 *    
 *    URI					|	Method			|	설명
 *    /boards/100			|	GET				|  게시판 글번호 100번 글 조회
 *    /boards/				|	GET				|  게시판 전체글 조회
 *    /boards + 데이터		|	POST			|  게시판 글쓰기
 *    /boards/100 + 데이터	|	PUT				|  게시판 글 수정하기(글번호100)
 *    /boards/100			|	DELETE			|  게시판 글번호 100번 글 삭제
 *    
 *  
 */


//REST방식으로 게시판CRUD처리
@RestController
@RequestMapping("/boards")
public class BoardController {

	@Inject
	private BoardService service;
	
	private static final Logger log 
	   = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<String> addBoard(@RequestBody BoardVO vo) throws Exception{
		log.info(" REST컨트롤러 :  addBoard() 호출 ");
		
		log.info(vo+"");
		
		int result = service.addBoard(vo);
		
		if(result == 1) {
			return new ResponseEntity<String>("Add_OK",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Add_Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> getListAll() throws Exception{
		log.info(" REST : getListAll() 실행");
		
		//log.info(service.getListALL()+"");
		List boardListAll = service.getListALL();
		
		
		return new ResponseEntity<List<BoardVO>>(boardListAll,HttpStatus.OK);
		//return new ResponseEntity<List<BoardVO>>(service.getListALL(),HttpStatus.OK);
	}
	
	
	
	
	

}
