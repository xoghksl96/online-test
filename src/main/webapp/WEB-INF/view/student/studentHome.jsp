<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	
	<title>Dashboard - NiceAdmin Bootstrap Template</title>
	<meta content="" name="description">
	<meta content="" name="keywords">
	
	<!-- Favicons -->
	<link href="/online-test/assets/img/favicon.png" rel="icon">
	<link href="/online-test/assets/img/apple-touch-icon.png" rel="apple-touch-icon">
	
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
      <h1>Dashboard</h1>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">

        <!-- Left side columns -->
        <div class="col-lg-8">
          <div class="row">

            <!-- Sales Card -->
            <div class="col-xxl-4 col-md-6">
              <div class="card info-card sales-card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">Total Test</h5>

                  <div class="d-flex align-items-center">
                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                      <i class="bi bi-cart"></i>
                    </div>
                    <div class="ps-3">
                      <h6>${map.testCount}</h6>
                      <span class="text-success small pt-1 fw-bold"></span> <span class="text-muted small pt-2 ps-1"></span>

                    </div>
                  </div>
                </div>

              </div>
            </div><!-- End Sales Card -->

            <!-- Revenue Card -->
            <div class="col-xxl-4 col-md-6">
              <div class="card info-card revenue-card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">Completed test</h5>

                  <div class="d-flex align-items-center">
                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                      <i class="bi bi-currency-dollar"></i>
                    </div>
                    <div class="ps-3">
                      <h6>${map.completeTestCount}</h6>
                      <span class="text-success small pt-1 fw-bold">${map.completeTestCount / map.testCount * 100}%</span> <span class="text-muted small pt-2 ps-1">increase</span>

                    </div>
                  </div>
                </div>

              </div>
            </div><!-- End Revenue Card -->

            <!-- Customers Card -->
            <div class="col-xxl-4 col-xl-12">

              <div class="card info-card customers-card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">Today's Visitor</h5>

                  <div class="d-flex align-items-center">
                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                      <i class="bi bi-people"></i>
                    </div>
                    <div class="ps-3">
                      <h6>1244</h6>
                      <span class="text-danger small pt-1 fw-bold">12%</span> <span class="text-muted small pt-2 ps-1">decrease</span>

                    </div>
                  </div>

                </div>
              </div>

            </div><!-- End Customers Card -->

			<!-- 최근 5개 시험 리스트 -->
            <div class="col-12">
              <div class="card top-selling overflow-auto">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">최근 5개</a></li>
                    <li><a class="dropdown-item" href="#">최근 10개</a></li>
                    <li><a class="dropdown-item" href="#">최근 20개</a></li>
                  </ul>
                </div>

                <div class="card-body pb-0">
                  <h5 class="card-title">Recently Test</h5>

                  <table class="table table-borderless" style="font-size:12pt">
                    <thead>
                      <tr>
                        <th scope="col" style="text-align:center">강사 명</th>
                        <th scope="col" style="text-align:center">시험 명</th>
                        <th scope="col" style="text-align:center">응시여부</th>
                        <th scope="col" style="text-align:center">시험 출제일</th>
                      </tr>
                    </thead>
                    <tbody>
	                    <c:forEach var="l" items="${list}">
							<tr style="height:30">
								<c:if test="${l.score != null}">
									<td style="text-align:center">${l.teacherName}</td>
									<td style="text-align:center" class="text-primary fw-bold"><a href="javascript:alert('응시한 시험입니다');">${l.testTitle}</a></td>
									<td style="text-align:center"><span class="badge bg-success"><i class="bi bi-check-circle me-1"></i>응시완료</span></td>	
									<td style="text-align:center" class="fw-bold">${l.testDate}</td>
								</c:if>
								<c:if test="${l.score == null}">
									<td style="text-align:center">${l.teacherName}</td>
									<td style="text-align:center" class="text-primary fw-bold"><a href="${pageContext.request.contextPath}/student/test/testOne?studentNo=${loginStudent.studentNo}&testNo=${l.testNo}">${l.testTitle}</a></td>
									<td style="text-align:center"><span class="badge bg-primary"><i class="bi bi-check-circle me-1"></i>미 응시</span></td>							
									<td style="text-align:center" class="fw-bold">${l.testDate}</td>
								</c:if>
							</tr>
						</c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div><!-- End Top Selling -->
            
            <!-- Reports -->
            <div class="col-12">
              <div class="card">

                <div class="filter">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                    <li class="dropdown-header text-start">
                      <h6>Filter</h6>
                    </li>

                    <li><a class="dropdown-item" href="#">Today</a></li>
                    <li><a class="dropdown-item" href="#">This Month</a></li>
                    <li><a class="dropdown-item" href="#">This Year</a></li>
                  </ul>
                </div>

                <div class="card-body">
                  <h5 class="card-title">Recently Test Result</h5>

                  <!-- Line Chart -->
                  <div id="reportsChart"></div>

                  <script>
                    document.addEventListener("DOMContentLoaded", () => {
                      new ApexCharts(document.querySelector("#reportsChart"), {
                        series: [{
                          name: 'Sales',
                          data: [31, 40, 28, 51, 42, 82, 56],
                        }, {
                          name: 'Revenue',
                          data: [11, 32, 45, 32, 34, 52, 41]
                        }, {
                          name: 'Customers',
                          data: [15, 11, 32, 18, 9, 24, 11]
                        }],
                        chart: {
                          height: 350,
                          type: 'area',
                          toolbar: {
                            show: false
                          },
                        },
                        markers: {
                          size: 4
                        },
                        colors: ['#4154f1', '#2eca6a', '#ff771d'],
                        fill: {
                          type: "gradient",
                          gradient: {
                            shadeIntensity: 1,
                            opacityFrom: 0.3,
                            opacityTo: 0.4,
                            stops: [0, 90, 100]
                          }
                        },
                        dataLabels: {
                          enabled: false
                        },
                        stroke: {
                          curve: 'smooth',
                          width: 2
                        },
                        xaxis: {
                          type: 'datetime',
                          categories: ["2018-09-19T00:00:00.000Z", "2018-09-19T01:30:00.000Z", "2018-09-19T02:30:00.000Z", "2018-09-19T03:30:00.000Z", "2018-09-19T04:30:00.000Z", "2018-09-19T05:30:00.000Z", "2018-09-19T06:30:00.000Z"]
                        },
                        tooltip: {
                          x: {
                            format: 'dd/MM/yy HH:mm'
                          },
                        }
                      }).render();
                    });
                  </script>
                  <!-- End Line Chart -->
                </div>
              </div>
            </div><!-- End Reports -->

            

          </div>
        </div><!-- End Left side columns -->

        <!-- Right side columns -->
        <div class="col-lg-4">

          <!-- Recent Activity -->
          <div class="card">
            <div class="filter">
              <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
              <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                <li class="dropdown-header text-start">
                  <h6>Filter</h6>
                </li>

                <li><a class="dropdown-item" href="#">Today</a></li>
                <li><a class="dropdown-item" href="#">This Month</a></li>
                <li><a class="dropdown-item" href="#">This Year</a></li>
              </ul>
            </div>

            <div class="card-body">
              <h5 class="card-title">Recent Activity <span>| Today</span></h5>

              <div class="activity">

                <div class="activity-item d-flex">
                  <div class="activite-label">32 min</div>
                  <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                  <div class="activity-content">
                    Quia quae rerum <a href="#" class="fw-bold text-dark">explicabo officiis</a> beatae
                  </div>
                </div><!-- End activity item-->

                <div class="activity-item d-flex">
                  <div class="activite-label">56 min</div>
                  <i class='bi bi-circle-fill activity-badge text-danger align-self-start'></i>
                  <div class="activity-content">
                    Voluptatem blanditiis blanditiis eveniet
                  </div>
                </div><!-- End activity item-->

                <div class="activity-item d-flex">
                  <div class="activite-label">2 hrs</div>
                  <i class='bi bi-circle-fill activity-badge text-primary align-self-start'></i>
                  <div class="activity-content">
                    Voluptates corrupti molestias voluptatem
                  </div>
                </div><!-- End activity item-->

                <div class="activity-item d-flex">
                  <div class="activite-label">1 day</div>
                  <i class='bi bi-circle-fill activity-badge text-info align-self-start'></i>
                  <div class="activity-content">
                    Tempore autem saepe <a href="#" class="fw-bold text-dark">occaecati voluptatem</a> tempore
                  </div>
                </div><!-- End activity item-->

                <div class="activity-item d-flex">
                  <div class="activite-label">2 days</div>
                  <i class='bi bi-circle-fill activity-badge text-warning align-self-start'></i>
                  <div class="activity-content">
                    Est sit eum reiciendis exercitationem
                  </div>
                </div><!-- End activity item-->

                <div class="activity-item d-flex">
                  <div class="activite-label">4 weeks</div>
                  <i class='bi bi-circle-fill activity-badge text-muted align-self-start'></i>
                  <div class="activity-content">
                    Dicta dolorem harum nulla eius. Ut quidem quidem sit quas
                  </div>
                </div><!-- End activity item-->

              </div>

            </div>
          </div><!-- End Recent Activity -->

          <!-- News & Updates Traffic -->
          <div class="card">
            <div class="card-body pb-0">
              <h5 class="card-title">Test schedule <span>| 2023</span></h5>

              <div class="news">
                <div class="post-item clearfix">
                  <img src="/online-test/assets/img/news-1.jpg" alt="">
                  <h4><a href="https://www.q-net.or.kr/crf005.do?id=crf00505&jmCd=1320" target="_blank">2023년 제 1회 정보처리기사 시험일정</a></h4>
                  <p>
                  	필기시험 : 2023.02.13 ~ 2023.03.15
                  	<br>
                  	실기시험 : 2023.04.22 ~ 2023.05.07
                  </p>
                </div>

                <div class="post-item clearfix">
                  <img src="/online-test/assets/img/news-2.jpg" alt="">
                  <h4><a href="https://www.q-net.or.kr/crf005.do?id=crf00505&jmCd=1320" target="_blank">2023년 제 2회 정보처리기사 시험일정</a></h4>
                  <p>
                  	필기시험 : 2023.05.13 ~ 2023.06.04
                  	<br>
                  	실기시험 : 2023.07.22 ~ 2023.08.06	
                  </p>
                </div>

                <div class="post-item clearfix">
                  <img src="/online-test/assets/img/news-3.jpg" alt="">
                  <h4><a href="https://www.q-net.or.kr/crf005.do?id=crf00505&jmCd=1320" target="_blank">2023년 제 3회 정보처리기사 시험일정</a></h4>
                  <p>
                  	필기시험 : 2023.07.08 ~ 2023.07.23	
                  	<br>
                  	실기시험 : 2023.10.07~ ~ 2023.10.20
                  </p>
                </div>

                <div class="post-item clearfix">
                  <img src="/online-test/assets/img/news-4.jpg" alt="">
                  <h4><a href="https://www.dataq.or.kr/www/accept/schedule.do" target="_blank">2023년 제 48회 SQL전문가 (SQLP)</a></h4>
                  <p>
                  	시험일 : 2023.03.19	
                  </p>
                </div>

                <div class="post-item clearfix">
                  <img src="/online-test/assets/img/news-5.jpg" alt="">
                  <h4><a href="https://www.dataq.or.kr/www/accept/schedule.do" target="_blank">2023년 제 48회 SQL개발자 (SQLD)</a></h4>
                  <p>
                  	시험일 : 2023.03.19	
                  </p>
                </div>

              </div><!-- End sidebar recent posts-->

            </div>
          </div><!-- End News & Updates -->

        </div><!-- End Right side columns -->

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