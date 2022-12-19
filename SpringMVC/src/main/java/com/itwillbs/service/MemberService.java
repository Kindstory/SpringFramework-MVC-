package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	// 회원가입
	public void memberJoin(MemberVO vo);
	
	// 로그인 체크
	public MemberVO memberLogin(MemberVO vo); // MemberDAO에서 동작이 MemberVO를 리턴하고 있기 때문에, 똑같이 리턴한다.
	
	
	// public MemberVO memberLogin(String userid, String userpw); // DAO랑 똑같이 오버로딩 해도 되지만, MemberVO로 통째로 받을꺼라서 여기서 안 씀. 점점 이렇게 
																// 따로 받는 형태를 줄어듬. 위와 같이 통째로 받는 형태를 많이 씀.

	public MemberVO memberGet(String userid);
}
