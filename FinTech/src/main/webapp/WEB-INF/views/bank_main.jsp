<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>bank_main.jsp</h1>
	<h3>인증완료</h3>
	<h2>액세스 토큰 : ${responseToken.access_token }</h2>
	<h2>사용자 번호 : ${responseToken.user_seq_no }</h2>
	<h2>token_type : ${responseToken.token_type }</h2>
	<h2>expires_in : ${responseToken.expires_in }</h2>
	<h2>refresh_token : ${responseToken.refresh_token }</h2>
	<h2>scope : ${responseToken.scope }</h2>
	
	

</body>
</html>