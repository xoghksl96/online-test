<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 로그인 전 -->
	<c:if test="${loginStudent == null}">
		<h1>학생 로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/loginStudent">
			<table border="1">
				<tr>
					<td>studentId</td>
					<td><input type="text" name="studentId"></td>
				</tr>
				<tr>
					<td>studentPw</td>
					<td><input type="password" name="studentPw"></td>
				</tr>	
			</table>
			<button type="submit">로그인</button>
		</form>
		
		<a href="${pageContext.request.contextPath}/loginEmp">사원 로그인</a>
		<a href="${pageContext.request.contextPath}/loginTeacher">강사 로그인</a>
	</c:if>
	<!-- 로그인 상태 -->
	<c:if test="${loginStudent != null}">
		<!-- studnetMenu -->
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"></c:import>
		<h1>${loginStudent.studentName}님 반갑습니다.</h1>
	</c:if>
</body>
</html>