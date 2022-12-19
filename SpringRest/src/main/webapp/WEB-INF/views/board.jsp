<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script type="text/javascript">
//    $(document).ready(function(){
//    });

    $(function(){
		
    	$("#btnPOST").click(function(){
    		// 글쓰기    /boards + 데이터(JSON)	
    		if( $("#title").val() == ''){
    			alert("제목을 입력하시오");
    			return;
    		}
    		
    		var txt = {
    			title:$("#title").val(),
    			content:$("#content").val(),
    			writer:$("#writer").val()
    		};
    		console.log(txt);
    		
    		// ajax -> BoardController 정보 전달/처리
    		// /boards + 데이터(JSON)	
    		$.ajax({
    			url:"./boards",
    			type:"POST",
    			contentType:"application/json",
    			data : JSON.stringify(txt),
    			success:function(data){
    				alert("성공");
    				//alert(data);
    				$("#post").append(data);
    			},error:function(data){
    				alert("실패");
    				console.log(data);
    			}
    		});
    	});//btnPOST click
    	
    	
    	
    	$("#btnGET").click(function(){
    		
    		$.ajax({
    			url : "./boards",
    			type : "GET",
    			success:function(data){
    				alert("성공");
    				console.log(data);
    				$(data).each(function(idx,item){
    					$("#get").append(item.bno+"/"+item.title+"<br>");
    				});
    			}
    			
    		});
    		
    		
    	}); // btnGET click
    	
    	
    	
    	
	});//jquery

</script>
</head>
<body>
   <h1>board.jsp</h1>
   
   제목 : <input type="text" id="title" name="title"> <br>
   내용 : <input type="text" id="content" name="content"> <br>
   작성자 : <input type="text" id="writer" name="writer"> <br>
   
   <hr>
   
   <input type="button" id="btnPOST" value="글 작성하기">
    <div id="post"></div>
   
   <hr>
   
   <input type="button" id="btnGET" value="글전체 리스트">
    <div id="get"></div>
  
   <hr>
   
   <input type="button" id="btnGetBno" value=" 특정 글 조회하기">
    <div id="getBno"></div>
    
    <hr>
    
    <hr>
   
   <input type="button" id="btnPUT" value="글 수정하기">
    <div id="put"></div>
    
    <hr>
   
   <input type="button" id="btnDEL" value="글 삭제하기">
    <div id="del"></div>
    
   
   
   
   

</body>
</html>