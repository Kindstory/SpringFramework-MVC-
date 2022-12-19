<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 	나머지 요소 다 지움 include가 나머지 요소 다 들고 있기 때문에, -->


<%@ include file="../include/header.jsp"%>



<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ITWILL 게시판 수정 </h3>
	</div>



	<!-- action ="" / 속성 생략 : 다시 자신의 주소를 호출 -->
	<form role="form"  method="post">    														<!-- 일반적인 form에 들어가는 action이나 method가 템플릿에 없으니까 직접 넣어줘야 함 -->
														  										<!-- form태그에 액션태그가 없거나 비어 있으면, 다시 자신의 주소(/board/regist)를 호출함. method(방식)만 바껴서 -->
		<div class="box-body">
		
			<div class="form-group">
				<label for="exampleInputEmail1">글번호</label> 
				  				   																		<!-- label for = " " 안에 이름 메일 안써도 메일이라고 해도 상관없음 상용화 안할 꺼라서 상용화해도 우리가 건드릴게 아님 디자이너들이 바꿈 -->
				<input type="text" class="form-control" id="exampleInputEmail1"
					   value="${vo.bno }" name="bno" readonly>      						  				 <!-- input type이랑 placeholder 정도만 바꿔주면 됨 기능이랑 바로 연관있으니까. -->
			</div> 
			
			<div class="form-group">
				<label for="exampleInputEmail1">제 목</label> 
				  				   																
				<input type="text" class="form-control" id="exampleInputEmail1"
					value="${vo.title }" name="title">      						  
			</div> 
			
			
			<div class="form-group">
				<label for="exampleInputEmail1">작성자</label>   				
				<input type="text" class="form-control" id="exampleInputEmail1"
					value="${vo.writer }" name="writer">      						
			</div> 
			
			<div class="form-group">
				<label for="exampleInputEmail1">내 용</label>   		
				<div class="form-group"> 
				<textarea class="form-control" rows="3" 
				    		name="content">${vo.content }</textarea>        							<!-- 기존의 템플릿에 어울리지 않고 그냥 따로 썻기 때문에, 기존 템플릿에 비슷한 양식 코드 넣어주면 됨. -->	
				</div>
			</div> 
			
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-primary">글 수정하기 </button>
		</div>
			
	</form>
	
</div>




<%@ include file="../include/footer.jsp"%>