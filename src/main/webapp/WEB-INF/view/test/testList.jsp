<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
a {
  text-decoration: none;
}
a:link {color : blue;}
a:visited{color : blue;}
a:hover {color : red;}
</style>
</head>
<body>
	
	<h1>Test List</h1>
	
	<a href="${pageContext.request.contextPath}/test/addTest">시험등록</a>
	<br>
	
	<form method="get" action="${pageContext.request.contextPath}/test/testList">
		<c:if test="${rowPerPage == 5}">
			<select name="rowPerPage">
				<option value="5" selected="selected">5</option>
				<option value="10">10</option>
				<option value="20">20</option>
			</select>
		</c:if>
		
		<c:if test="${rowPerPage == 10}">
			<select name="rowPerPage">
				<option value="5">5</option>
				<option value="10" selected="selected">10</option>
				<option value="20">20</option>
			</select>
		</c:if>
		
		<c:if test="${rowPerPage == 20}">
			<select name="rowPerPage">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="20" selected="selected">20</option>
			</select>
		</c:if>
			
		<input type="text" name="searchWord" value="${searchWord}">
		<button type="submit">검색</button>
	</form>
	
	<table border="1">
		<tr>
			<th>teacherName</th>
			<th>testTitle</th>
			<th>testDate</th>
			<th>
				<c:choose>
					<c:when test="${loginTeacher != null}">
						수정
					</c:when>
					
					<c:when test="${loginStudent != null}">
						응시
					</c:when>
				</c:choose>		
			</th>
		</tr>
		
		<c:forEach var="l" items="${list}">
			<tr>
				<td>${l.teacherName}</td>
				<td><a href="${pageContext.request.contextPath}/teacher/test/testOne?testNo=${l.testNo}">${l.testTitle}</a></td>
				<td>${l.testDate}</td>
				<td>
					<c:choose>
						<c:when test="${loginTeacher != null}">
							<a href="${pageContext.request.contextPath}/test/modifyTest?teacherNo=${loginTeacher.teacherNo}&testNo=${l.testNo}">수정</a>
						</c:when>
						
						<c:when test="${loginStudent != null}">
							<a href="${pageContext.request.contextPath}/test/takeTest?studentNo=${loginStudent.studentNo}&testNo=${l.testNo}">응시</a>
						</c:when>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>