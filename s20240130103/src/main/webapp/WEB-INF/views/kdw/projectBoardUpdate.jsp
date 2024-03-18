<%@page import="com.blackberry.s20240130103.kdw.model.BoardProjectFile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>공유게시판 글 수정 : 블루베리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/blueberry-favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- KDW Main JS File -->
<script src="/assets/js/kdw/projectBoardUpdate.js"></script>
<!-- KDW Main CSS File -->
<link href="assets/css/kdw/projectBoardUpdate.css" rel="stylesheet">

</head>

<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<main id="main" class="main">
	    <div class="pagetitle">
	        <h1>공유게시판 글 수정</h1>
	        <nav>
	            <ol class="breadcrumb">
	                <li class="breadcrumb-item"><a href="main">Home</a></li>
	                <li class="breadcrumb-item"><a href="boardProject?project_no=${projectNo}">공유게시판</a></li>
	                <li class="breadcrumb-item active">글 수정</li>
	            </ol>
	        </nav>
	    </div>
		<!-- End Page Title -->
		<div class="card">
			<!-- 글쓰기 세션 부분 -->
			<section class="update-section">
				<div class="form-container">
					<div class="title-group-prepend">
						<span class="title-group-text">글쓰기</span>
					</div>
					<!-- 파일을 보내려면 form에서 encType = "multipart/form-data" 를 이용해서 보내야 한다 -->
					<!-- 그리고 하단에 <input type="file" name="fileName">로 파일을 보낼 수 있게 넣어준다 -->
	                <form id="update-form" action="/updateSave?project_no=${projectNo}&pboard_no=${pboardNo}" method="post" enctype="multipart/form-data">
	                    <!-- 수정 버튼 -->
	                    <div class="form-group">
	                        <button type="submit" class="update-save-btn">수정</button>
	                    </div>
	                    <!-- 취소 Button : 이전 페이지로 돌아가기 -->
	                    <div class="form-group">
	                        <a href="<%=request.getHeader("Referer")%>" class="update-cancel-btn">취소</a>
	                    </div>
						<!-- 제목 -->
	                    <div class="form-group">
	                        <div class="subject-group">
	                            <!-- 제목 텍스트 -->
	                            <div class="subject-group-prepend">
	                                <span class="subject-group-text">제목</span>
	                            </div>
	                            <!-- 인풋 -->
	                            <input type="text" id="pboard_subject" name="pboard_title" class="form-control" value="${board.pboard_title}" required>
	                        </div>
	                    </div>
						<!-- 첨부파일 -->
						<div class="form-group">
						    <div class="mb-3">
						        <!-- 사용자 정의 파일 선택 버튼과 파일 개수 표시 -->
						        <div class="file-form-control">
						            <button type="button" id="customFileBtn" class="btn fileBtn">파일 선택</button>
						            <span id="fileCount">선택된 파일 없음</span>
						        </div>
						        <input type="file" id="files" name="files" multiple style="display: none;" onchange="updateFileList(this.files)">
						        <!-- 드래그 앤 드롭 영역 -->
						        <div id="drop_zone" class="file_drag">
								    <!-- 초기 안내 문구 -->
								    <div id="initial_message" style="display:none; margin-top:60px; color:#6c757d; font-weight: 700;">여기에 파일을 드래그하세요.</div>
								    <!-- 파일 목록 상단 바, 파일이 드래그 되면 표시됩니다. -->
								    <div id="file_list_bar" class="file-list-bar">
								         <span id="delete_all" style="cursor: pointer;">X</span>
								         <span>파일명</span>
								         <span>용량</span>
								    </div>
								    <!-- 업로드할 파일 목록 -->
								    <ul id="fileList" class="file-list"></ul>
									<!-- 업로드된 파일 목록 -->
									<ul id="uploadFileList" class="file-list">
									</ul>
						        </div>
						    </div>
						</div>
						<!-- 내용 -->
	                    <div class="form-group">
	                        <div class="content-group">
	                            <span class="content-group-text">내용</span>
	                            <textarea id="pboard_content" name="pboard_content" rows="5" required>${board.pboard_content}</textarea>
	                        </div>
	                    </div>
					</form>
				</div>
			</section>
		</div>
	</main>
	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<!-- 스크롤하면 우측 아래 생기는 버튼 : 클릭하면 페이지의 맨 위로 이동 -->
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/chart.js/chart.umd.js"></script>
	<script src="assets/vendor/echarts/echarts.min.js"></script>
	<script src="assets/vendor/quill/quill.min.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
</body>
</html>