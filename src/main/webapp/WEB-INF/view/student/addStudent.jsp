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
	<h1>학생추가</h1>
	<div style="color : red">${errorMsg}</div>
	
	<div>
      <input type="text" id="id" name="id">
      
      <button type="button" id="idCheckBtn">중복검사</button>
   </div>
   
	<form method="post" id="addStudentForm" action="${pageContext.request.contextPath}/employee/student/addStudent">
		<table border="1">
			<tr>
				<td>studentId</td>
				<td><input type="text" id="studentId" name="studentId" readonly="readonly"></td>
			</tr>
			<tr>
				<td>studentPw</td>
				<td><input type="password" name="studentPw"></td>
			</tr>
			<tr>
				<td>studentName</td>
				<td><input type="text" name="studentName"></td>
			</tr>	
		</table>
		<button id="addStudentBtn" type="button">학생추가</button>
	</form>
	
<script>
	$("#idCheckBtn").click(function(){
		$.ajax({
			url:'/online-test/idCheck'
			, type:'get'
			, data:{id:$('#id').val()}
			, success:function(model){
				if(model=="YES") {
					$('#studentId').val($('#id').val());
				} else {
					alert($('#id').val() + '는(은) 사용중인 ID입니다');
				}
			}
		})
				
	});
	$("#addStudentBtn").click(function(){
		$("#addStudentForm").submit();
	});
</script>
</body>
</html>