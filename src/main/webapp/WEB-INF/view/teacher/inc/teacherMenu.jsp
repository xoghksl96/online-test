<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<!-- 시험회차(table : test) 관리(CRUD)
		 단, 시험을 응시한 학생이 없을 때 UPDATE, DELETE 가능 
		 
		 개별시험회차 클릭하면 그회차의 문제 리스트를 출력(문제 CRUD)
		 개별문제 클릭하면 그문제의 보기 리스트 출력(보기 CRUD)
	 -->	
	<a href="${pageContext.request.contextPath}/teacher/testList">시험관리</a>
	
	
	<a href="${pageContext.request.contextPath}/teacher/modifyTeacherPw">비밀번호 수정</a>
	<!-- 비밀번호 수정 -->
	
	<a href="${pageContext.request.contextPath}/teacher/logout">로그아웃</a>
	<!-- 로그아웃 -->
</div>