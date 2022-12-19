package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageMakerVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller  // => 이거 하나면 다 맵핑 JSP에서 하던거 다 안해도 됨
@RequestMapping("/board/*") // => .bo 랑 같은거
public class BoardController {

	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	
	// 서비스객체 필요(의존적)
	@Inject
	private BoardService service;  // Impl객체를 주입 한다 service에. 결합도 떨구려고 
	
	
	
	
	// http://localhost:8080/controller/test (x)
	// http://localhost:8080/controller/board/test (o)  -> @RequestMapping("/board/*") 이거 한 이후의 주소
	// http://localhost:8080/board/test ( 서버 모듈 설정 후 ( / 로 바꾼후) )
	
	// 이래서 한 서버에 프로젝트 하나씩 배치해둠. 주소가 겹치거나 하면 안되서.
	
	
//	@RequestMapping("/test")
//	public void test() {
//		log.info("test() 호출");
//	}
	
	
	
	
	// http://localhost:8080/board/regist
	// 글쓰기  -  GET   -> 사용자에게 보여주기
	@RequestMapping(value= "/regist", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		log.info(" registerGET() 호출 ");
		log.info(" /board/regist (get) -> /board/regist.jsp "); 		// return 값이 void니까 주소. jsp    board가 controller안에 있기 때문에, 붙여줘야 함.
		
	}
	
	
	
	// 글쓰기  -  POST  -> 처리하는 동작
	@RequestMapping(value="/regist", method= RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr /*Model model*/) throws Exception{
		log.info("registerPOST() 호출");     // 주소 매핑이 되면 404 error뜨고 매핑자체가 안 되있으면 405가 뜸.
		// 한글처리(필터)
		// 전달된 정보 저장
		log.info("글쓰기 정보 : " + vo);
		
		// 서비스 - 글쓰기 동작  => DAO에서 직접호출하지 않고 중간다리 Service만들어서 결합도 떨궈줌.
		service.boardWrite(vo);
		
		log.info(" 글쓰기 완료 !! ");
		
		//model.addAttribute("msg", "OK");
		
		// RedirectAttributes 객체 => rediret 페이지 이동시에만 사용가능
		rttr.addFlashAttribute("msg", "OK");
		// -> 1회성 데이터 (체크용), URL에 표시 x
		
		// 페이지 이동(리스트) 화면,주소 모두 변경
		
		//return "/board/success";
		//return "redirect:/board/listAll?msg=OK";
		return "redirect:/board/listAll";
			
	}
	
		
		
		//http://localhost:8080/board/listAll
		// 게시판 리스트 - 조회 (GET)
		@RequestMapping(value = "/listAll",method = RequestMethod.GET)
		public void listALLGET(@ModelAttribute("msg") String msg, Model model, HttpSession session) throws Exception {
			log.info("listALLGET() 호출 ");
			
			// 1) 글쓰기 -> 리스트, 2) 리스트
			log.info("msg : "+msg);
			// 연결된 view페이지로 정보 전달
			model.addAttribute("msg", msg);
			
			// 서비스 - 글전체 목록 가져오는 메서드
			List<BoardVO> boardList = service.getBoardListAll();
			
			model.addAttribute("boardList", boardList);
			
			// 세션객체 - isUpdate 정보 전달
			session.setAttribute("isUpdate", false);
			
			
			log.info("/board/listAll -> /board/listAll.jsp ");	
		}
		
		
		// http://localhost:8080/board/read?bno=12
		
		// 글 본문보기 - GET
		@RequestMapping(value = "/read",method = RequestMethod.GET)
		public void readGET(HttpSession session, @RequestParam("bno") int bno, Model model /* @ModelAttribute("bno") int bno */) throws Exception {
			log.info("readGET() 호출");
			// 전달정보 (bno)
			log.info(" bno : "+bno);
			
			boolean isUpdate = (boolean)session.getAttribute("isUpdate");  //  처음에 가지고 왔을떄, if 조건 걸어주기전에는 둘다 false 임 초기값이라.
			log.info(" isUpdate : " + session.getAttribute("isUpdate"));
			
			if(!isUpdate) {
				// 서비스 - updateReadCount(BNO)
				service.updateReadCount(bno);
				log.info(" 조회수 1증가! ");
				session.setAttribute("isUpdate", true);
			}
			
			// 서비스 - updateReadCount(BNO) -> 정보가 필요하니까 조회수 체크하려면 그 정보를 매개변수에 넣어주면 됨.
			
			service.updateReadCount(bno);
			log.info(" 조회수 1증가! ");
			session.setAttribute("isUpdate", true);
			
			// 서비스 - getBoard(int)
			BoardVO vo = service.getBoard(bno);
			log.info(vo+"");
			model.addAttribute("vo", vo);
			
			log.info("/board/read -> /board/read.jsp");
		}
		
		
		// 글 수정하기 - GET (기존의 정보 조회 출력 + 수정할 정보 입력)
		@RequestMapping(value = "/modify", method = RequestMethod.GET)
		public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception{
			
			// 전달정보 저장(bno)
			log.info("@@@" + bno);
			
			// 서비스 - 게시판 글 정보를 가져오는 메서드 
			service.getBoard(bno);
			
			// 연결된 뷰에 정보 전달(Model 객체)
			model.addAttribute("vo", service.getBoard(bno));
			
			
			// 페이지 이동(출력)  /board/modify
			
		}
		
		// 글 수정하기 - POST(수정할 데이터 처리)
		@RequestMapping(value="/modify", method=RequestMethod.POST)
		public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
			log.info("modifyPOST() 호출 ");
			
			// 한글처리(생략) 					 => 필터를 걸어놔서 생략이 되는거고, 생략을 안하면 직접 구현해야함. 이 사실을 알고 있어야 하기 때문에, 적어주는 거임.
			
			// 전달정보 저장(수정할 정보) VO 
			log.info("@@수정할 정보@@" + vo);
			
			// 서비스 - 글 수정메서드     => Service로 로직 구현하고, DAO는 mapper.xml에서 쿼리문 불러와서 DB에 저장하는 용임. 
			int cnt = service.updateBoard(vo);
			
			
			// 수정성공시 /listAll 페이지 이동 
			if(cnt==1) {
				
				rttr.addFlashAttribute("msg", "MODOK");
				
				return "redirect:/board/listAll";
				
			}else {
				
				// 예외처리
				// new NullPointerException();
				
				return "/board/modify?bno=" + vo.getBno();
			}
			
			
		}
	
		@RequestMapping(value="/delete", method=RequestMethod.POST)
		public String deletePOST(Integer bno, RedirectAttributes rttr) throws Exception{
			

			// 한글처리(생략) 					 => 필터를 걸어놔서 생략이 되는거고, 생략을 안하면 직접 구현해야함. 이 사실을 알고 있어야 하기 때문에, 적어주는 거임.
			
			// 전달정보 저장(수정할 정보) VO 
			log.info("bno : " + bno);
			
			// 서비스 - 글 삭제  메서드
			int cnt = service.deleteBoard(bno);

			if(cnt == 1) {
				log.info("글 삭제 성공!");
				
				rttr.addFlashAttribute("msg", "DELOK");
				
				return "redirect:/board/listAll";
			}
			else {
				log.info("글 삭제 실패!");
				
				return "redirect:/board/listAll";
			}

			
			
		}
		
		// http://localhost:8080/board/listPage
				// http://localhost:8080/board/listPage?page=2
				// http://localhost:8080/board/listPage?perPageNum=50
				// http://localhost:8080/board/listPage?page=3&perPageNum=20
				// 게시판 리스트(페이징처리) - GET
				@RequestMapping(value = "/listPage",method = RequestMethod.GET)
				public String listPageGET(PageVO vo, Model model) throws Exception{
					log.info( " listPageGET() " );
//					PageVO vo = new PageVO();
//					vo.setPage(2);
//					vo.setPerPageNum(30);
					
					model.addAttribute("boardList" , service.listPage(vo));
					
					// 페이징 처리 하단부 정보 저장
					PageMakerVO pm = new PageMakerVO();
					pm.setVo(vo);
					pm.setTotalCnt(5631); // 글 개수
					
					model.addAttribute("pm", pm);
					
					
					return "/board/listAll";
					
				}


		
		
	
	
}
