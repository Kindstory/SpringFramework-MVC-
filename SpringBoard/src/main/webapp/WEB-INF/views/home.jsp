<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<!-- 페이지 include  -->
<%@ include file= "include/header.jsp" %>
  
 
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>


<div class="form-group">
<label for="exampleInputEmail1">Email address</label>
<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
</div>

<!-- 이런 식으로 쓰고 싶은 양식 adminLTE에서 코드 복사해서 쓰면 됨. -->



<%@ include file= "include/footer.jsp" %>




