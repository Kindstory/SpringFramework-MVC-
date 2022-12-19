package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

public interface BoardDAO {

	// 글쓰기 - insertBoard(vo)
	public void insertBoard(BoardVO vo);

	// 글 전체목록 - listAll()
	public List<BoardVO> listAll() throws Exception;

	// 글 1개 정보 가져오기 - getBoard(int)
	public BoardVO getBoard(Integer bno) throws Exception;

	// 글 조회수 1증가
	public void updateReadCount(Integer bno) throws Exception;
	
	// 글 수정하기
	public Integer updateBoard(BoardVO vo) throws Exception;   // 수정할 정보를 가져와야 되니까 매개변수로 받아옴.'
	
	// 글 삭제하기
	public Integer deleteBoard(Integer bno) throws Exception;
	
	// 글 전체목록 - listPage(page)
	public List<BoardVO> listPage(Integer page) throws Exception;
		
	// 글 전체목록 - listPage(pageVO)
	public List<BoardVO> listPage(PageVO vo) throws Exception;
}
