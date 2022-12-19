package com.itwillbs.persistence;

import java.beans.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 스프링(root-context.xml)에서 파일을 DAO파일로 인식하도록 설정 -> 인식되면 S가 떠야함. 근데 아직 안뜸.

@Repository // 이 어노테이션 떄문에 root-context.xml이 찾을 수 있고, bean으로 만들어 줌. S가 뜸. repository 어노테이션 없으면 S안뜸.
public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// DB에 관련된 동작을 수행
	
	// 디비연결 정보 필요 => 의존관계
	// sqlSessionFactory 객체 필요함(주입)
	
	
	// 이미 생성된 객체 [root-context.xml - sqlSessionFactory객체(빈)]
	// @Inject
	// private SqlSessionFactory factory;
	
	
	// 디비연결 + MyBatis설정(mapper) + 자원해제
	
	@Inject
	private SqlSession sqlSession;  // DI     sqlSession이 DB연결도 할 꺼고, 객체도 생성할거고
	
	
	// mapper의 주소(이름) - 상수화
	private static final String NAMESPACE
		= "com.itwillbs.mapper.MemberMapper";
	
	@Override
	public String getTime() {
		
		
		// 1.2. 디비 연결
		
		// 3. sql 작성   -> memberMapper.xml에서 작성함.
		
		// 4. sql 실행    
						// ** 다 안해도 됨
		// 5. 데이터 처리
		
		// SqlSession sqlSession = factory.openSession();
		
		String now
		= sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");   // Mapper라는 객체가 없음
		
		
		
		return now;
	}



	@Override
	public void insertMember(MemberVO vo) {
		log.info("@@@@@ 1. 2. 디비 연결 - sqlSession(DI 객체)" );
		log.info("@@@@@    3. sql 작성 - (memberMapper.xml)" );
		log.info("@@@@@    3. pstmt 객체 생성 - sqlSession(DI 객체) ");
		log.info("@@@@@ 4. SQL 실행 - sqlSession(DI객체)");
		
		// sqlSession.insert(SQL구문, 전달할 객체);
		sqlSession.insert(NAMESPACE +"insertMember", vo);
		//com.itwillbs.mapper.MemberMapper.insertMember
		log.info("@@@@@ 전달하는 vo 객체는 mapper에서 자동으로매핑 후 정보 전달 ");
		log.info("@@@@@ DAOImpl => mapper 이동 => MYSQL 이동 ");
		
		log.info("@@@@@    자원해제 - sqlSession(DI객체) ");
			
	}



	@Override
	public MemberVO loginMember(MemberVO vo) {
		log.info("loginMember(vo) 호출");
		
		MemberVO resultVO 
			=sqlSession.selectOne(NAMESPACE + ".loginMember", vo); 
		
		// mappper에서 쿼리 실행 결과 저장해서 리턴
		
		return resultVO;
	}



	@Override
	public MemberVO loginMember(String userid, String userpw) {
		log.info("loginMember(userid, userpw) 호출");
		
		// sqlSession.selectOne(NAMESPACE + ".loginMember", userid, userpw);  // 전달인자를 하나만 받을 수 있다 selectOne 메서드는 
		
		// 전달된 정보를 하나의 도메인 객체에 저장 후 처리
		MemberVO vo = new MemberVO();
		vo.setUserid(userid);
		vo.setUserpw(userpw);
		
		sqlSession.selectOne(NAMESPACE + ".loginMember", vo);
		
		
		// 회원정보 + 게시판정보 => 하나의 도메인(MemberVO) 저장이 안되는 경우(다른 게시판 혹은 다른 회원인 경우)는 쿼리를 어떻게 실행할까??
		// => 컬렉션을 사용 (연관없는 데이터를 한번에 저장)  ex) join을 할때나 등등 일반적인 경우에선 굳이 사용할 필요가 없음.
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); // Map은 인터페이스라 객체생성 안됨.
		//paramMap.put("컬럼명", 데이터 값);
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		sqlSession.selectOne(NAMESPACE + ".loginMember", paramMap);
		
		
		
		return null;
	}



	@Override
	public MemberVO getMember(String id) {
		
		log.info(" getMember(String id)호출 ");
		log.info(" mapper-sql 구문 호출 동작 ");
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE + ".getmember", id);
		// "com.itwillbs.mapper.MemberMapper.getMember"
		log.info(resultVO + "");
		log.info("테스트 파일로 이동");
		
		
		return resultVO;
	}



	@Override
	public Integer updateMember(MemberVO uvo) {
		
		log.info(" 테스트 -> updateMember(MemberVO uvo) 호출 ");
		int result = sqlSession.update(NAMESPACE + ".udpateMember", uvo);
		
		log.info(" 회원 정보 수정 완료 ");
		// result => 0 (수정 x), 1 (수정 o)
		log.info(" updateMember -> 테스트 호출 ");
		
		return result;
	}


	@Override
	public Integer deleteMember(MemberVO dvo) {
		
		log.info(" 테스트 -> deleteMember(MemberVO dvo) 호출 ");
		int result = sqlSession.delete(NAMESPACE + ".deleteMember" , dvo);
		
		log.info(" 회원 정보 삭제 완료 ");
		// result => 0 (삭제 x), 1 (삭제 o) 
		log.info(" deleteMember -> 테스트 호출 ");
		
		return result;
		
	}



	@Override
	public List<MemberVO> getMemberList() {
		
		
		// DB에서 vo형태의 객체가 전달되면,
		// List형태로 저장
		
		List<MemberVO> memberList = sqlSession.selectList(NAMESPACE + ".getMemberList");
		
		
		return memberList;  
		
		// return sqlSession.selectList(NAMESPACE + ".getMemberList");
		
		
		// 위에 방식은 더 넘겨줄게 있을때,
		
		// 밑에 방식은 넘겨줄게 없을때, 그냥 바로 리턴할때,
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
