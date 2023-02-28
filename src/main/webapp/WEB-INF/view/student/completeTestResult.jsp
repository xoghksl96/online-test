<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function (){
	
	
});
</script>
</head>
<body>
	<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"></c:import>
	
	<h1>시험 보기</h1>
	<form id="addPaperForm" method="post" action="${pageContext.request.contextPath}/paper/addPaper">
		<input type="hidden" class="testNo" name="testNo" value="${testNo}">
		<c:forEach var="questionList" items="${list}" varStatus="i">
			<input type="hidden" class="questionNo" name="questionNo" value="${questionList.questionList.questionNo}">
			<input type="hidden" class="answer" name="answer" value="">
			<div>
				<table border="1" style="width : 200px">
					<tr>
						<th>${questionList.question.questionTitle}</th>
					</tr>
					<c:forEach var="exampleList" items="${questionList.exampleList}">
						<tr>
							<c:if test="${exampleList.exampleOx eq '정답'}">
								<c:choose>
									<c:when test="${exampleList.exampleIdx == questionList.paper.answer}">
										<td style="background-color : yellow">
											<input type="radio" id="answer${i.count}" name="answer${i.count}" value="${exampleList.exampleIdx}" checked="checked">&nbsp;${exampleList.exampleTitle}
										</td>
									</c:when>
									
									<c:otherwise>
										<td style="background-color : red">
											<input type="radio" id="answer${i.count}" name="answer${i.count}" value="${exampleList.exampleIdx}" disabled="disabled">&nbsp;${exampleList.exampleTitle}
										</td>
									</c:otherwise>
								</c:choose>
							</c:if>
							
							<c:if test="${exampleList.exampleOx == '오답'}">
								<c:choose>
									<c:when test="${exampleList.exampleIdx == questionList.paper.answer}">
										<td>
											<input type="radio" id="answer${i.count}" name="answer${i.count}" value="${exampleList.exampleIdx}" checked="checked">&nbsp;${exampleList.exampleTitle}
										</td>
									</c:when>
									
									<c:otherwise>
										<td>
											<input type="radio" id="answer${i.count}" name="answer${i.count}" value="${exampleList.exampleIdx}" disabled="disabled">&nbsp;${exampleList.exampleTitle}
										</td>
									</c:otherwise>
								</c:choose>
							</c:if>
						</tr>
					</c:forEach>				
				</table>
			</div>
			<br>
		</c:forEach>
		
		<button type="button" id="addPaperBtn">제출하기</button>
	</form>
</body>
</html>