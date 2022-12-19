<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 버튼 클릭했을때, 정보생성 -> JSON형태 변경 전달
		
		$("#sendJson").click(function(){
			alert(" 버튼 클릭! ");
			
			//id:$("#id").val()
			var user = {
				id:"user01",
				pw:"1234",
				name:"학생1",
				email:"user01@naver.com"
			};
			//alert(user);
			//console.log(user);
			
			//ajax -> restController 호출
			$.ajax({
				url:"./rest/info",
				type:"post",
				contentType:"application/json",
				data:JSON.stringify(user),
				success:function(){
					alert(" 성공 ");
				},
				error:function(){
					alert(" 실패 ");
				}				
				
			});
			
			
			
			
			
			
		}); // click
		
		
	});// jquery



</script>
</head>
<body>
    <h1>info.jsp</h1>
    
    <input type="button" id="sendJson" value="정보전달(ajax)">
    

</body>
</html>