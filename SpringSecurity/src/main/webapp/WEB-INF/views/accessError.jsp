<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>views/accessError.jsp  접근권한이 없음.</h1>
		
		<h2>관리자에게 문의하세요.</h2>
		
		
		${msg }
		<hr>
		${auth }
		<hr>
		${SPRING_SECURITY_403_EXCEPTION.getMessage() }
		
		
		
		
		
		
		
</body>
</html>