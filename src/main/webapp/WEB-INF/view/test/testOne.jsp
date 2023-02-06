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
		
		let testForm = $("testForm");
		let index = 1;
		$("#addExampleBtn").on("click",function(){
			if(index == 5) {
				alert("보기는 최대 5개까지 입력 가능합니다.");
				return false;
			}
			
			let html = '<input type="radio" class="exampleIdx" name="exampleIdx">'
				+ ' <input type="text" class="questionTitle" name="questionTitle" placeholder="보기">'
				+ ' <input type="hidden" class="exampleOx" name="exampleOx">'
				+ '<br>';
			$('#exampleList').append(html);
			index++;
		});
		
		$("#addQuestionBtn").on("click",function(){
			let exampleIdxList = document.querySelectorAll('.exampleIdx');
			let exampleOxList = document.querySelectorAll('.exampleOxList');
			let exampleIdxListLength = exampleIdxList.length();
			
			for(let i=0; i<exampleIdxListLength; i++){
				$("exampleIdxList[i]").val = index;
				if($("exampleOxList[i]").is(':checked')) {
					$("$(exampleOxList[i]").val = '정답';
				}
			}
			
		});	
	});
</script>
</head>
<body>
	<h1>시험 추가</h1>
	
	<c:forEach var="questionList" items="${list}">
		<table border="1">
			<tr>
				<th>${questionList.questionList.questionTitle}</th>
			</tr>
			<c:forEach var="exampleList" items="${questionList.exampleList}">
				<tr>
					<td>
						<input type="radio" name="exampleTitle" value="${exampleList.exampleIdx}">&nbsp;${exampleList.exampleTitle}
					</td>
				</tr>
			</c:forEach>
		</table>
		<br>
	</c:forEach>
	
	<form method="post" action="" id="testForm">
		<input type="hidden" name="questionIdx" value="${list.size()+1}">
		<div>
			<input type="text" name="questionTitle" placeholder="문제">
			
			<br> 
			
			<div id="exampleList">
				<input type="radio" class="exampleIdx" name="exampleIdx">
				<input type="text" class="exampleTitle" name="exampleTitle" placeholder="보기">
				<input type="hidden" class="exampleOx" name="exampleOx" value="오답">
				<br>
			</div>
			
			<br>
			
			<!-- 보기 추가 버튼 -->
			<div>
				<button type="button" id="addExampleBtn">보기 추가</button>
				
				<button type="button" id="addQuestionBtn">문제 등록</button>
			</div>
		</div>
	</form>
</body>
</html>