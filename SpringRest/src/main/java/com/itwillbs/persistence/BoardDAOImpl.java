package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";
	
	@Override
	public Integer addBoard(BoardVO vo) throws Exception {
		
		return sqlSession.insert(NAMESPACE + ".create", vo);
	}

	@Override
	public List<BoardVO> getListALL() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".list");
	}
	
	

}
