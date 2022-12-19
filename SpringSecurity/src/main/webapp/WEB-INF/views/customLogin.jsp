<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>customLogin.jsp</h1>
	
	<fieldset>
	  
	  <legend> 로그인(커스텀페이지) </legend>
	  <form action="/login" method="post">
		  아이디 : <input type="text" name="username"> <br>
		  비밀번호 : <input type="password" name="password"> <br>
		  
		  <input type="hidden" name="${_csrf.parameterName }" 
		           value="${_csrf.token }"> 
		  
		  <input type="submit" value="로그인">
	  </form>
	</fieldset>
	
	
</body>
</html>