package com.itwillbs.web;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping("/member/*") // member로 시작되는 모든 주소의 형태를 내가 관리하겠다.
public class MemberController {

	

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	// 서비스 객체 주입(DI)
	@Autowired
	private MemberService service;
	
	@Inject
	private MemberDAO dao;
	
	// 위치상 src.main.java 에 있어서 test파일이 아님. test를 하는 이유는 더 제대로 이해하기위해 더 높은 곳을 위해 
	// Test파일이 아니라서 서버가 필요함. 
	
	
	// http://localhost:8080/web/member/test(x)
	// http://localhost:8080/member/test(O)  -> 서버당 프로젝트가 하나 일때만 실행 가능 함. 프로젝트가 2개면 둘다 걸려서 충돌 남. /web이 아니라 root로 초기화 시킴 
	
//	@RequestMapping("/test")
//	public void TestMember() {
//		log.info("MemberController 실행!!!");
//	}
	
	// http://localhost:8080/member/insert
    
	// 회원가입 GET (조회, 입력)    - /member/insert     member는 컨트롤러를 찾는 주소임.	       // 회원가입 동작은 2가지로 나눌수있음. 입력받는거, 처리하는거
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertGET() {     // void or String 둘 다 써도 됨 
		log.info(" insertGET() 호출 ");
		log.info(" 연결된 view페이지 출력 ");
		
	}
	
	// 브라우저에 mapping이 되있으면 메시지(경로)가 뜨고, 아애 매핑이 안되있으면 메시지(경로)가 안뜸. 
	
	
	// 회원가입 POST (처리)  					// 먼가를 처리하면 다 POST임.
	//@PostMapping()
	@RequestMapping(value= "/insert", method = RequestMethod.POST)
	public String insertPOST(MemberVO vo, HttpServletRequest request) throws Exception { 
		log.info(" insertPOST() 호출 ");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");

		// 전달정보 저장 (userid, userpw, username, useremail)
		// MemberVO 객체 저장
		// MemberVO vo = new MemberVO();   // 매개변수로 MemberVO vo 하나만 받아도 vo관련 parameter를 다 받아줌.  
											// 밑에 따로 안 받아도 됨. 단, view page에서 name이 MemberVO 변수명이랑 같아야 됨.
//		vo.setUserid(request.getParameter("userid"));
//		vo.setUserpw(request.getParameter("userpw"));
//		vo.setUsername(request.getParameter("username"));
//		vo.setUseremail(request.getParameter("useremail"));
		
		log.info(vo +"");
		
		// 회원가입 -> DB에 저장
        // MemberDAO dao = new MemberDAOImpl(); (x)
		
		// DB작업이 필요하다 하면, -> service를 호출해서 service가 DB를 호출하게 함. controller가 DB를 호출하는게 아니라.
		
		// 회원가입 -> 서비스 -> DB에 저장(DAO 객체 생성)		
		// MemberDAO dao = new MemberDAOImpl();(x)
	    // MemberService service = new MemberServiceImpl();(x)
		service.memberJoin(vo);
		
		log.info(" 회원가입 성공! ");
		
		
		
		
		// 페이지 이동(로그인 페이지)
		
		//return "/member/login";
		return "redirect:/member/login";
	}
	
	// 똑같이 insert지만, get이나 post에 따라 달라지는 거임   get은 조회, 입력 post는 데이터 처리 
		
	
	// 로그인GET
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginGET() {
		log.info(" loginGET() 실행 ");
		log.info(" 연결된 뷰 페이지로 이동 ");
		
		return "/member/memberLogin";  // String은 풀네임 적어줘야함. login만 말고 memberLogin
	}
	
	// 로그인POST 
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public String loginPOST(/*@ModelAttribute("userpass") String userpass,*/ MemberVO vo, HttpSession session) { // MemberVO는 userpass를 못 받아옴. view 페이지에 name = "userpw"로 바꾸면 받아짐
																												// 아니면 따로 받을때는, 그냥 @ModelAttribute로 받으면 됨
		log.info(" loginPOST()  호출 " );	
	
		// 한글처리 -> 필터사용(생략가능)
		// 전달 정보 저장( 파라미터 - userid, userpw)
		
		// log.info("userid : " + userpass);
		log.info("vo : " + vo);
		// DB에서 확인 ( 컨트롤러 -> 서비스 -> DAO )
		MemberVO loginVO = service.memberLogin(vo);
		
		log.info("loginVO : " + loginVO);
		
		// 로그인 여부 확인 
		if(loginVO != null) {
			
			// 성공 -> 메인페이지 이동, 로그인 정보를 저장(세션)
			
			// JSP(view)에서 session 정보를 가져와서 사용
			session.setAttribute("loginVO", loginVO);
			return "redirect:/member/main";
			
		} else {
			
			// 실패 -> 로그인페이지 이동 (redirect 해라 원래 페이지로)
			
			return "redirect:/member/Login";
			
		}
		
	}
	
	// 메인페이지 GET/POST 
	
	// 메인페이지 GET	http://localhost:8080/member/main  앞에 member는 컨트롤러에서 매핑 되있음.
	@RequestMapping(value="name", method=RequestMethod.GET)
	public void mainGET() {
		log.info(" mainGET() 호출 ");
		log.info(" void 리턴 : /member/join.jsp 뷰 호출 ");
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		// 로그아웃 -> 세션 초기화
		session.invalidate();
		log.info("세션 초기화 완료 => 로그아웃 성공");
		
		// 페이지 이동

		return "redirect:/member/main";
		
	}
	// 회원정보 조회 GET
	@RequestMapping(value="/info",method=RequestMethod.GET)
	public void infoGET(HttpSession session, Model model) {
		log.info(" infoGET() 호출" );
		// main 페이지(session) -> ID정보 -> info페이지
		MemberVO vo = (MemberVO) session.getAttribute("loginVO");
		//vo.getUserid();
		log.info(" ID : " + vo.getUserid());
		// 서비스 사용 -> DB정보를 가져오기
		MemberVO userVO = service.memberGet(vo.getUserid());
		log.info("@@@@@@ userVO : " +userVO);
		// 전달정보를 Model객체에 저장 / view 출력	
		model.addAttribute("userVO", userVO);
		
		
	}
	 
	
	
}
