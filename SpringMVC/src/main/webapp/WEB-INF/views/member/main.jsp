<%@page import="com.itwillbs.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>member/main.jsp</h1>
    <h2> 메인페이지 </h2>
    
    <c:if test="${loginVO == null }">
       <!-- 사용자 정보없음 -->
       <c:redirect url="/member/login" />	    
    </c:if>
 <%--  
    <%
       MemberVO vo = (MemberVO) session.getAttribute("loginVO");
    %>
    <%=vo.getUserid() %>님 환영합니다!(JSP) <br>
     --%>
    ${loginVO.userid }님 환영합니다!(EL) <br>
    ${sessionScope.loginVO.userid }님 환영합니다!(EL) 
    
    <!-- 로그아웃 -->
    
    <input type="button" value="로그아웃" 
           onclick="location.href='/member/logout';"> 
    <a href="javascript:location.href='/member/logout';">로그아웃</a>
    
    
    <hr>
    
    <a href="/member/info"> 회원정보 조회 </a>
    
    
    
    
    
    
    
    
</body>
</html>