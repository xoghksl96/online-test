<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
</head>
<body>
	<h1>사원추가</h1>
	
	<div>
      <input type="text" id="id" name="id">
      
      <button type="button" id="idCheckBtn">중복검사</button>
   </div>
   
	<form method="post" id="addEmpForm" action="${pageContext.request.contextPath}/employee/addEmp">
		<table border="1">
			<tr>
				<td>empId</td>
				<td><input type="text" id="empId" name="empId" readonly="readonly"></td>
			</tr>
			<tr>
				<td>empPw</td>
				<td><input type="password" name="empPw"></td>
			</tr>
			<tr>
				<td>empName</td>
				<td><input type="text" name="empName"></td>
			</tr>	
		</table>
		<button id="addEmpBtn" type="button">사원 추가</button>
	</form>
	
<script>
	$("#idCheckBtn").click(function(){
		$.ajax({
			url:'/online-test/idCheck'
			, type:'get'
			, data:{id:$('#id').val()}
			, success:function(model){
				if(model=="YES") {
					$('#empId').val($('#id').val());
				} else {
					alert($('#id').val() + '는(은) 사용중인 ID입니다');
				}
			}
		})
				
	});
	$("#addEmpBtn").click(function(){
		$("#addEmpForm").submit();
	});
</script>
</body>
</html>