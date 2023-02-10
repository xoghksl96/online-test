<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	
	<!-- testList 출력
		지나간 시험(table : test) 리스트 + 점수, 점수확인버튼 : 제출답안지 확인(table : paper O/X)
		오늘날짜 시험리스트는 응시버튼 - 시험지출력(table : question(문제) + example(보기)) - 답안지 제출(table : paper)
	 -->
	<a href="${pageContext.request.contextPath}/student/testList">시험응시</a>
	
	<!-- 학생이 응시한 시험의 결과만 보여주는 페이지 -->
	
	<a href="${pageContext.request.contextPath}/student/modifyStudentPw">비밀번호 수정</a>
	<!-- 비밀번호 수정 -->
	
	<a href="${pageContext.request.contextPath}/student/logout">로그아웃</a>
	<!-- 로그아웃 -->
</div>