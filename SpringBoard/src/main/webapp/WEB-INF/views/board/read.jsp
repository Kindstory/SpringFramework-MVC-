<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>   <!-- 여기 ../ 이 안에 jQuery 라이브러리 들어가 있어서 바로 쓰면 됨. plugins 폴더안에 jQuery라이브러리 깔려 있음. -->

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판 본문</h3>
	</div>
	
		<!-- 수정, 삭제시 필요한 글 번호 저장 -->
		<form role="form" method="post">
			<input type="hidden" name="bno" value ="${vo.bno}">
		</form>


		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">제 목</label>
				
				<input type="text" class="form-control" id="exampleInputEmail1"
					value="${vo.title }" name="title" readonly>
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">작성자</label>
				
				<input type="text" class="form-control" id="exampleInputEmail1"
					value="${vo.writer }" name="writer" readonly>
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">내 용</label>
				<textarea class="form-control" rows="3" 
				    name="content" readonly>${vo.content }</textarea>
			</div>
		
			
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-danger">수정하기</button>
			<button type="submit" class="btn btn-warning">삭제하기</button>
			<button type="submit" class="btn btn-success">목록으로</button>
		</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
 		// alert('제이쿼리 실행!');
 		
 		// 글번호 정보를 포함하는 폼태그 
 		
 		var fr = $('form[role="form"]');
 		// alert(fr);
 		// console.log(fr);
 		
 		
 		$(".btn-danger").click(function(){
 			alert('수정버튼 클릭');
 			fr.attr("action","/board/modify");
 			fr.attr("method","get");                     			//수정과 삭제의 차이 수정은 get방식 삭제는 post방식 
 			fr.submit();
 		});
 		
 		
 		$(".btn-warning").click(function(){
 			alert("삭제버튼 클릭");
 			fr.attr("action", "/board/delete");
 			fr.attr("method","post");    
 			fr.submit();
 			
 		});
		
 		
 		
 		$(".btn-success").click(function() {
			location.href="/board/listAll";
		});
 		
	});
</script>




<%@ include file="../include/footer.jsp"%>

