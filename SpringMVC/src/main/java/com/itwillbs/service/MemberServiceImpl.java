package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// MemberDAO객체를 주입(DI) - 객체를 직접생성 X,만들어진거 사용하기 (root-context.xml)
	@Inject
	private MemberDAO dao;
	
	@Override
	public void memberJoin(MemberVO vo) {  
		log.info("컨트롤러 -> 서비스(Impl)");
		log.info("MemberServiceImpl-memberJoin() 호출");
		log.info(" 서비스(Impl) -> DAO(Impl) ");
		
		dao.insertMember(vo);
		
		log.info(" DAO 동작 완료!! 서비스 -> 컨트롤러 ");	
		
		
		// DAO 객체 생성 - 메서드 호출 -> 직접생성x (강한결합이니까) -> 그러니 이미 만들어진걸 쓰는게 어떻겠냐 이거지.
		// MemberDAO 객체를 주입(DI) - 객체를 직접 생성x, 만들어진거 사용하기(root-context.xml 안에 만들어져있음 )
		// <context:component-scan base-package="com.itwillbs.persistence" /> 이 태그 떄문에, 객체직접생성 안해도 됨.
		
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {  // 내가  서비스에서 구현할꺼는 서비스에서 DB로 보내주는거(DAO)만 구현하면 됨.
		
		// 컨트롤러 -> 로그인 정보 (id, pw) -> 서비스 -> DB로 보냄(DAO로 이동)   
		 log.info("memberLogin(MemberVO vo) 호출"); 
		// 서비스 -> 로그인 정보(vo[id, pw]) -> DAO
		 log.info(" DAO 로그인 메서드 호출 ! ");
		 MemberVO loginVO = dao.loginMember(vo);
		 
		 log.info(" loginVO : " + loginVO);
		 
		 return loginVO;
		 
		 // return dao.loginMember(vo);  -> 단순 정보전달의 목적으로는 이렇게  return에 메서드 바로 적는게 좋음 간단하게.
	}

	@Override
	public MemberVO memberGet(String userid) {
		log.info(" memberGet(userid 호출 ");
		// 주입 객체 사용 - 메서드 호출
		
		return dao.getMember(userid);
	}


}
