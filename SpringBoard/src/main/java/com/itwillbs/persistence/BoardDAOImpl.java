package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;


@Repository
public class BoardDAOImpl implements BoardDAO {

	

	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);

	// SqlSession객체 주입(DI)
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";
	
	
	@Override
	public void insertBoard(BoardVO vo) {
		log.info(" insertBoard(vo) 호출" );
		//log.info(vo + "");

		// SQL실행객체 - SqlSession객체(디비연결, MyBatis, 매퍼, 자원해제)
		// 글쓰기(insert)
		int result = sqlSession.insert(NAMESPACE+".create", vo);

		if(result > 0)
		log.info(" 글쓰기 완료! ");
		
	}
	
	@Override
	public List<BoardVO> listAll() throws Exception {
		log.info(" listAll() ");
		
		// DB - 모든정보 가져오기(SQL/mapper 호출)
		List<BoardVO> boardList =
		    sqlSession.selectList(NAMESPACE + ".listAll");
		
		//log.info(boardList+"");
		log.info(boardList.size()+"");
		
		return boardList;
	}

	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		log.info(" getBoard(Integer bno) 호출 ");
		
		BoardVO vo = sqlSession.selectOne(NAMESPACE + ".read",bno);
		
		return vo;
		
		//return sqlSession.selectOne(NAMESPACE + ".read",bno); 
	}

	@Override
	public void updateReadCount(Integer bno) throws Exception {
		log.info(" updateReadCount(Integer bno) 호출 " );
		
		// SQL - mapper 쿼리구문 호출
		
		sqlSession.update(NAMESPACE + ".updateReadCnt", bno);
		
	}

	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		 log.info(" updateBoard(BoardVO vo) 호출 ");
		
		 int cnt = sqlSession.update(NAMESPACE + ".updateBoard", vo);
		
		
		 return cnt;
	}

	@Override
	public Integer deleteBoard(Integer bno) throws Exception {
		log.info(" deleteBoard(Integer bno) 호출 ");
		
		
		return sqlSession.delete(NAMESPACE+".delete", bno);
	}

	
	@Override
	public List<BoardVO> listPage(Integer page) throws Exception {   // int로 안에서 직접 만드는 거랑 매개변수로 Integer pageSize로 받는거랑 머가 다른지 물어보기 -> 차이없음 
																	 // 인터페이스랑 메서드랑 다 고쳐야 되니까 매개변수 바꿔버리면 그래서 그냥 안에서 만드신 거임.
		log.info("listPage(Integer page) 호출");
		
		if(page <= 0) {
			page = 1;
		}
		int pageSize = 30;
		// 1 -> 0, 2 -> 10, 3 -> 20, 4 -> 30, ..... 
		page = (page - 1) * pageSize;
		
		Map<String, Object> pageObj = new HashMap<String, Object>();     // 페이지 처리 전용객체를 만드는것도 좋은 방법이 될 수 있음.
		pageObj.put("page", page);
		pageObj.put("pageSize", pageSize);    
		
		return sqlSession.selectList(NAMESPACE+".listpage2", pageObj);
	}

	@Override
	public List<BoardVO> listPage(PageVO vo) throws Exception {
		log.info ( " listPage(PageVO vo) 호출 ");
		
		
		return sqlSession.selectList(NAMESPACE + ".listPage3", vo);
	}

	
}
