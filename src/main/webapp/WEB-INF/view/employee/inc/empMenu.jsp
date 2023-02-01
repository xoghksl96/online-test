<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath}/employee/empList">사원관라</a>
	<!-- 사원목록, 사원삭제, 등록시 ID체크(employee + teacher + student) -->
	
	<a href="${pageContext.request.contextPath}/teacher/teacherList">강사관리</a>
	<!-- 강사목록, 강사삭제, 시험등록(회차) -->
	
	<a href="${pageContext.request.contextPath}/student/studentList">학생관리</a>
	<!-- 학생목록, 학생삭제, 시험응시, 시험점수 확인 -->
	
	<a href="${pageContext.request.contextPath}/employee/modifyEmpPw">비밀번호 수정</a>
	<!-- 비밀번호 수정 -->
	<a href="${pageContext.request.contextPath}/employee/logout">로그아웃</a>
</div>