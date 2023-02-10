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
	<h1>선생추가</h1>
	
	<div>
      <input type="text" id="id" name="id">
      
      <button type="button" id="idCheckBtn">중복검사</button>
   </div>
   
	<form method="post" id="addTeacherForm" action="${pageContext.request.contextPath}/employee/teacher/addTeacher">
		<table border="1">
			<tr>
				<td>teacherId</td>
				<td><input type="text" id="teacherId" name="teacherId" readonly="readonly"></td>
			</tr>
			<tr>
				<td>teacherPw</td>
				<td><input type="password" name="teacherPw"></td>
			</tr>
			<tr>
				<td>teacherName</td>
				<td><input type="text" name="teacherName"></td>
			</tr>	
		</table>
		<button id="addTeacherBtn" type="button">선생추가</button>
	</form>
	
<script>
	$("#idCheckBtn").click(function(){
		$.ajax({
			url:'/online-test/idCheck'
			, type:'get'
			, data:{id:$('#id').val()}
			, success:function(model){
				if(model=="YES") {
					$('#teacherId').val($('#id').val());
				} else {
					alert($('#id').val() + '는(은) 사용중인 ID입니다');
				}
			}
		})
				
	});
	$("#addTeacherBtn").click(function(){
		$("#addTeacherForm").submit();
	});
</script>
</body>
</html>