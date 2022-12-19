<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<h1>/member/insert.jsp</h1>
	
	<fieldset>
	
		<legend>회원 가입</legend>
		
		<!--// member/member/insertPro 라고 뜸 상위폴더 ./member 안 적어줘도 됨. 그렇지만 적어주는걸 권장 -->
		<!--  action="/insertPRo" 
			  action="/컨트롤러 주소/이동 페이지주소(권장)"-->		
			  
		<form action="/member/insert" method="post"> 
			 아이디 : <input type = "text" name="userid"><br>
			 비밀번호 : <input type = "password" name="userpw"><br>
			 이름 : <input type = "text" name="username"><br>
			 이메일 : <input type = "text" name="useremail"><br>
			 <input type="submit" value="회원가입">
		</form>
	
	</fieldset>

</body>
</html>