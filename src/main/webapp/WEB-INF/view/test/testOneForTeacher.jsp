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
		let html = '<input type="radio" class="exampleRadio" name="exampleRadio">'
			+ ' <input type="hidden" class="exampleIdx" name="exampleIdx">'
			+ ' <input type="text" class="exampleTitle" name="exampleTitle" placeholder="보기">'
			+ ' <input type="hidden" class="exampleOx" name="exampleOx" value="오답">'
			+ '&nbsp;&nbsp;'
			+ ' <button type="button" class="exampleRemoveBtn">보기 삭제</button>'
			+ '<br>';
		
		
		for(let i=0;i<index;i++){
			let exampleRemoveBtnList = document.querySelectorAll('.exampleRemoveBtn');
			let exampleRemoveBtnListLength = exampleRemoveBtnList.length;
			$(exampleRemoveBtnList[i]).on("click",function(){
				console.log("클릭");
				console.log(index);
				exampleRemoveBtnList = document.querySelectorAll('.exampleRemoveBtn');
				exampleRemoveBtnListLength = exampleRemoveBtnList.length;
				
				$('#exampleList').append(null);
				for(let j=0;j<i;j++){
					$('#exampleList').append(html);
				}
				index--;
			});
		}
		
		$("#addExampleBtn").on("click",function(){
			console.log(index);
			if(index == 5) {
				alert("보기는 최대 5개까지 입력 가능합니다.");
				return false;
			}
			
			$('#exampleList').append(html);
			index++;
		});
	
		
		$("#addQuestionBtn").on("click",function(){	
			let exampleRadioList = document.querySelectorAll('.exampleRadio');
			let exampleIdxList = document.querySelectorAll('.exampleIdx');
			let exampleOxList = document.querySelectorAll('.exampleOx');
			let exampleIdxListLength = exampleIdxList.length;
			
			for(let i=0; i<exampleIdxListLength; i++){
				$(exampleIdxList[i]).val(i+1);
				if($(exampleRadioList[i]).is(':checked')) {
					$(exampleOxList[i]).val("정답");
				}
			}
			
			$("#addQuestionAndExampleForm").submit();
		});	
	});
</script>
</head>
<body>
	<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp"></c:import>
	
	<h1>시험 추가</h1>
	
	<c:forEach var="questionList" items="${list}">
		<div>
			<a href="${pageContext.request.contextPath}/test/modifyTest?testNo=${testNo}&questionNo=${questionList.questionList.questionNo}">수정</a>
			&nbsp;
			<a href="${pageContext.request.contextPath}/test/removeTest?testNo=${testNo}&questionNo=${questionList.questionList.questionNo}">삭제</a>
			<table border="1" style="width : 200px">
				<tr>
					<th>${questionList.questionList.questionTitle}</th>
				</tr>
				<c:forEach var="exampleList" items="${questionList.exampleList}">
					<tr>
						<td>
							<c:choose>
								<c:when test="${exampleList.exampleOx eq '정답'}">
									<input type="radio" value="${exampleList.exampleIdx}" checked="checked">&nbsp;${exampleList.exampleTitle}
								</c:when>
								
								<c:otherwise>
									<input type="radio" value="${exampleList.exampleIdx}" disabled="disabled">&nbsp;${exampleList.exampleTitle}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<br>
	</c:forEach>
	
	<form method="post" action="${pageContext.request.contextPath}/test/addQuestionAndExample" id="addQuestionAndExampleForm">
		<input type="hidden" id="testNo" name="testNo" value="${testNo}">
		<input type="hidden" name="questionIdx" value="${list.size()+1}">
		<div>
			<input type="text" name="questionTitle" placeholder="문제">
			
			<br> 
			
			<div id="exampleList">
				<input type="radio" class="exampleRadio" name="exampleRadio">
				<input type="hidden" class="exampleIdx" name="exampleIdx">
				<input type="text" class="exampleTitle" name="exampleTitle" placeholder="보기">
				<input type="hidden" class="exampleOx" name="exampleOx" value="오답">
				&nbsp;&nbsp;
				<button type="button" class="exampleRemoveBtn">보기 삭제</button>
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