package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	
	public Integer addBoard(BoardVO vo) throws Exception;
	
	public List<BoardVO> getListALL() throws Exception;
}
