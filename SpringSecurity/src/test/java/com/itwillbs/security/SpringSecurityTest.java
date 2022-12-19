package com.itwillbs.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	{
	   "file:src/main/webapp/WEB-INF/spring/root-context.xml",
	   "file:src/main/webapp/WEB-INF/spring/security-context.xml"	
	}
)
public class SpringSecurityTest {
	
	
	@Inject
	private DataSource ds;
	
	@Inject
	private PasswordEncoder pwEncoder;
	

	private static final Logger log 
	    = LoggerFactory.getLogger(SpringSecurityTest.class);
	
	
	
	//@Test
	public void 암호화테스트() throws Exception{
		log.info(pwEncoder.encode("12345"));
	}
	
	//@Test
	public void 사용자_생성() throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		for(int i=0;i<10;i++) {
			
			con = ds.getConnection();
			String sql = "insert into tbl_member(userid,userpw,username,useremail) "
					+ "values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// 아이디 : user1, 비밀번호 : pw1
			
			pstmt.setString(2, pwEncoder.encode("pw"+i)); 
			
			if(i<8) {
				pstmt.setString(1, "user"+i);
				pstmt.setString(3, "사용자"+i);
				pstmt.setString(4, "user"+i+"@itwill.com");
			}else if(i<9) {
				pstmt.setString(1, "manager"+i);
				pstmt.setString(3, "매니저"+i);
				pstmt.setString(4, "manager"+i+"@itwill.com");
			}else {
				pstmt.setString(1, "admin"+i);
				pstmt.setString(3, "관리자"+i);
				pstmt.setString(4, "admin"+i+"@itwill.com");
			}
			
			pstmt.executeUpdate();
			
		}
		
	}
	
	@Test
	public void 사용자_인증정보_만들기() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		for(int i=0;i<10;i++) {
			
			con = ds.getConnection();
			String sql = "insert into tbl_member_auth(userid,auth) values(?,?)";
			pstmt = con.prepareStatement(sql);
			// 아이디 : user1, 권한 : ROLE_USER,ROLE_MANAGER,ROLE_ADMIN
			
			if(i<8) {
				pstmt.setString(1, "user"+i);
				pstmt.setString(2, "ROLE_USER");
			
			}else if(i<9) {
				pstmt.setString(1, "manager"+i);
				pstmt.setString(2, "ROLE_MANAGER");
			}else {
				pstmt.setString(1, "admin"+i);
				pstmt.setString(2, "ROLE_ADMIN");
			}
			
			pstmt.executeUpdate();
			
		}
		
	}
	
	
	
	
	

}
