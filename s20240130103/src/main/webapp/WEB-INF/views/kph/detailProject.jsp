<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Blueberry</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="assets/img/blueberry-favicon.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    
    <!-- Google Fonts -->
  	<link href="https://fonts.gstatic.com" rel="preconnect">
  	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    
    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet"  type="text/css">
    <link href="assets/css/kph/detailProject.css"  rel="stylesheet"  type="text/css"> <!-- 이건 복사해서 사용하지 마세요 헤더 푸터가 아닙니다.-->
     
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script defer src="/assets/js/kph/detailProject.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
    
</head>

<body>

    <!-- ======= Header ======= -->
    <%@ include file="../header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>
    
    <!-- End Sidebar-->

    <main id="main" class="main">

		<div class="pagetitle">
			<h1>워크 스페이스</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.html">프로젝트</a></li>
					<li class="breadcrumb-item active">프로젝트 명</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="top">
				<div class="top-btn">
					<button type="button" class="project-home-btn btn btn-primary">프로젝트
						홈</button>
					<form action="boardProject" method="get">
						<input class="project_no" type="hidden" name="project_no" value="${taskList.get(0).project_no }" />
						<button type="button" class="project-board-btn btn btn-secondary">공유
						게시판</button>			
					</form>
				</div>
				<div class="top-list">
					<div class="team-list">
						<div id="team-list-title" class="team-list-title dropdown-toggle">팀원
							목록</div>
						<ul id="team-list-content" class="team-list-content">
							<c:forEach var="projectMember" items="${projectMemberList }">
								<li><img
									src="${pageContext.request.contextPath}/upload/userImg/${projectMember.user_profile}"
									alt="Profile" class="rounded-circle">${projectMember.user_name }</li>
							</c:forEach>
						</ul>
					</div>
					<div class="setting">
						<i id="setting-btn" class="bi bi-gear-fill"></i>
						<ul id="setting-content" class="setting-content team-list-content">
							<li>팀원 추가</li>
							<li>프로젝트 수정</li>
							<li>프로젝트 삭제</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="project-home">
				<div class="card task-card">
					<div class="task-card-head">
						<h5 class="card-title">과업 목록</h5>
						<a href="#"><button type="submit" class="plus-btn">+</button></a>
					</div>
					<div class="task-list">
						<c:forEach var="task" items="${taskList}">
							<div class="task-list-detail">
								<div class="task-head">
									<div class="task-title">${task.task_title}</div>
									<i class="bi bi-three-dots-vertical"></i>
									<ul class="task-setting">
										<li>과업 수정</li>
										<li>과업 삭제</li>
									</ul>
								</div>
								<div class="task-detail">
									<div class="task-comp-chk">
										<i class="bi bi-check-circle"></i>
										<c:choose>
											<c:when test="${task.task_comp_chk == 0 }">
												<button class="comp-chk-btn uncomp-btn">미완료</button>	
											</c:when>
											<c:otherwise>
												<button class="comp-chk-btn comp-btn">완료</button>	
											</c:otherwise>
										</c:choose>
									</div>
									<div class="member">
										<i class="bi bi-people"></i>
										<div class="task-member-toggle">
											<div class="task-member-title dropdown-toggle">참여자</div>
											<ul class="task-member-list">
												<c:forEach var="user" items="${task.users }">
													<li><img src="${pageContext.request.contextPath}/upload/userImg/${user.user_profile}" alt="Profile"
													class="rounded-circle">${user.user_name }</li>
												</c:forEach>
											</ul>
										</div>
									</div>
									<div class="due-date">
										<i class="bi bi-calendar3"></i>
										<fmt:parseDate value = "${task.task_end }" pattern = "yyyy-MM-dd HH:mm" var = "date"/>
										<div><fmt:formatDate value="${date}" pattern="yyyy-MM-dd (E) HH:mm" />까지</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="card task-callender-card">
					<div class="project-report">
						<div class="project-report-head">
							<h5 class="card-title">프로젝트 리포트</h5>
						</div>
						<div class="project-report-content">
							<div class="project-report-card total-task">
								<div>
									<p>전체 과업</p>
									<p id="totalTaskFilter">${unCompTaskListCount + compTaskListCount}</p>
								</div>
								<div class="compPercent">8%</div>
							</div>
							<div class="project-report-card comp-task">
								<div>
									<p>완료 과업</p>
									<p id="compTaskFilter">${compTaskListCount}</p>
								</div>
							</div>
							<div class="project-report-card uncomp-task">
								<div>
									<p>미완료 과업</p>
									<p id="unCompTaskFilter">${unCompTaskListCount }</p>
								</div>
							</div>
						</div>
					</div>
					<div class="callender-card">
						<div class="callender-head">
							<h5 class="card-title">프로젝트 일정</h5>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
    <!-- End #main -->

    <!-- ======= Footer ======= -->
    <%@ include file="../footer.jsp" %>
    <!-- End Footer -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
            class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
    <!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
    <!-- 
    <script src="assets/vendor/quill/quill.min.js"></script>
 	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
  	<script src="assets/vendor/php-email-form/validate.js"></script> -->

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

</body>

</html>