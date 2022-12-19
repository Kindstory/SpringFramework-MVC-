package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	@Override
	public Integer addBoard(BoardVO vo) throws Exception {
		return dao.addBoard(vo);
	}

	@Override
	public List<BoardVO> getListALL() throws Exception {
		return dao.getListALL();
	}
	
	

}
