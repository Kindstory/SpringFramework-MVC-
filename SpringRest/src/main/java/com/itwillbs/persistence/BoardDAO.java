package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {

	public Integer addBoard(BoardVO vo) throws Exception;
	
	public List<BoardVO> getListALL() throws Exception;
}
