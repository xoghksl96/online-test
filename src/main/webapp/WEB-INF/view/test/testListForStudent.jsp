<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Tables / General - NiceAdmin Bootstrap Template</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="/online-test/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/online-test/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="/online-test/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="/online-test/assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="/online-test/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="/online-test/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="/online-test/assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="/online-test/assets/css/style.css" rel="stylesheet">

<!-- =======================================================
* Template Name: NiceAdmin - v2.5.0
* Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
======================================================== -->
</head>
<body>
  
  	<c:import url="/WEB-INF/view/student/inc/studentMenuHeader.jsp"></c:import>
  	<c:import url="/WEB-INF/view/student/inc/studentMenuSideBar.jsp"></c:import>
  	
  	<main id="main" class="main">

    <div class="pagetitle">
      <h1>Test List</h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Table with stripped rows</h5>

              <!-- Table with stripped rows -->
              <table class="table table-striped">
                <thead>
                  <tr>
                    
                    <th scope="col">teacherName</th>
					<th scope="col">testTitle</th>
					<th scope="col">testDate</th>
					<th scope="col">응시</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
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
										<c:if test="${l.score != null}">
											${l.score}
										</c:if>
										<c:if test="${l.score == null}">
											<a href="${pageContext.request.contextPath}/student/test/testOne?studentNo=${loginStudent.studentNo}&testNo=${l.testNo}">응시</a>
										</c:if>
									</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
                  </tr>
                </tbody>
              </table>
              
              <br>
              
              <!-- pagging -->
              <div>
              	<ul class="pagination justify-content-center">
					<!-- 처음으로 -->
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/student/testList?currentPage=1&searchWord=${searchWord}"><i class="bi bi-chevron-bar-left"></i></a></li>
					
					<!-- 이전(-10) -->
					<c:choose>
						<c:when test="${currentPage - 10 >= 1}">
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/student/testList?currentPage=${map.startPage-10}&searchWord=${searchWord}"><i class="bi bi-chevron-left"></i></a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="#"><i class="bi bi-chevron-left"></i></a></li>
						</c:otherwise>
					</c:choose>
					
					<!-- startPage <= currentPage <= endPage -->
					<c:forEach var="i" begin="${map.startPage}" end="${map.endPage}" step="1">
						<c:if test="${currentPage == i}">
							<li class="page-item"><a class="page-link" href="#" style="color : red">${i}</a></li>
						</c:if>
						<c:if test="${currentPage != i}">
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/student/testList?currentPage=${i}&searchWord=${searchWord}">${i}</a></li>
						</c:if>
					</c:forEach>
					
					<!-- 다음(+10) -->
					<c:choose>
						<c:when test="${currentPage + 10 < map.lastPage}">
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/student/testList?currentPage=${map.startPage+10}&searchWord=${searchWord}"><i class="bi bi-chevron-right"></i></a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="#"><i class="bi bi-chevron-right"></i></a></li>
						</c:otherwise>
					</c:choose>
					
					<!-- 끝으로 -->
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/student/testList?currentPage=${map.lastPage}&searchWord=${searchWord}"><i class="bi bi-chevron-bar-right"></i></a>
				</ul>
			</div>
			
            </div>
          </div>
        </div>
      </div>
    </section>
  </main><!-- End #main -->
  	
	
	<!-- Vendor JS Files -->
	<script src="/online-test/assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="/online-test/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/online-test/assets/vendor/chart.js/chart.umd.js"></script>
	<script src="/online-test/assets/vendor/echarts/echarts.min.js"></script>
	<script src="/online-test/assets/vendor/quill/quill.min.js"></script>
	<script src="/online-test/assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="/online-test/assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="/online-test/assets/vendor/php-email-form/validate.js"></script>
	
	<!-- Template Main JS File -->
	<script src="/online-test/assets/js/main.js"></script>
</body>
</html>