<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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

	
	$("#modifyQuestionAndExampleBtn").on("click",function(){	
		let exampleRadioList = document.querySelectorAll('.exampleRadio');
		let exampleIdxList = document.querySelectorAll('.exampleIdx');
		let exampleOxList = document.querySelectorAll('.exampleOx');
		let exampleIdxListLength = exampleIdxList.length;
		
		for(let i=0; i<exampleIdxListLength; i++){
			$(exampleIdxList[i]).val(i);
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
	<h1>${map.question.questionIdx}번 문제</h1>
	<div>
		<form method="post" action="">
			<input type="hidden" name="testNo" value="${testNo}">
			<input type="hidden" name="questionNo" value="${map.question.questionNo}">
			<table border="1" style="width : 200px">
				<tr>
					<th>
						<textarea style="width:500px;height:75px">${map.question.questionTitle}</textarea>
				</tr>
				<c:forEach var="exampleList" items="${map.exampleList}">
					<input type="hidden" name="exampleNo" value="${exampleList.exampleNo}">
					<input type="hidden" name="exampleIdx" value="${exampleList.exampleIdx}">
					<input type="hidden" name="exampleOx" value="${exampleList.exampleOx}">
					<tr>
						<td style="height:50px">
							<div style="width:100%">
								<c:choose>
									<c:when test="${exampleList.exampleOx eq '정답'}">
										<input type="radio" name="exampleRadio" checked="checked">
									</c:when>
									
									<c:otherwise>
										<input type="radio" name="exampleRadio">
									</c:otherwise>
								</c:choose>
								<input type="text" name="exampleTitle" value="${exampleList.exampleTitle}">
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<button id="modifyQuestionAndExampleBtn">수정 완료</button>
			</div>
		</form>
	</div>
</body>
</html>