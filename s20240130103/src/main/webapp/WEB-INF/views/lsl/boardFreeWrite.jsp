<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  
  	<title>Blueberry</title>
	<meta content="" name="description">
	<meta content="" name="keywords">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery CDN -->

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
  <link href="assets/css/lsl/lslboardFreeWrite.css" rel="stylesheet"> 
  <link href="assets/css/style.css" rel="stylesheet"> <!-- 헤더, 푸터, 사이드바 css -->
  
  
   <!-- File Js -->
   <script defer src="assets/js/lsl/boardFile.js"></script>
   
   <!-- Write Form Js  -->
   <script defer src="assets/js/lsl/writeForm.js"></script>

   
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

    <!-- ======= Main ======= -->
 <main id="main" class="main">
    <div class="pagetitle">
        <h1>게시판</h1>
        <nav style="--bs-breadcrumb-divider: '/';">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="main">정보 게시판</a></li>
                <li class="breadcrumb-item">자유 게시판</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <section class="section">
        <div class="row justify-content-center">
            <div class="col-lg-13">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">글쓰기</h5>

                        <!-- General Form Elements -->
                        <form id="freeWrite" method="post" action="/freeWrite" enctype="multipart/form-data">
                       
                            <div class="mb-3">
                                <input type="text" class="form-control" id="inputText" name="cboard_title" placeholder="제목을 입력하세요.">
                            </div>
                            <!-- 파일 첨부 -->
									<div class="upload-files">
										<label for="files" class="form-label">파일 첨부</label>
										<input class="form-control" name="files" type="file" id="formFile"  multiple />
										<div class="upload-title" id="fileList" >
										<div class="preview">파일을 첨부할 수 있습니다.</div>
										</div>
									</div>
                            <div class="mb-3">
                                <textarea class="form-control" id="cboard_content" name="cboard_content" placeholder="내용을 입력하세요." ></textarea>
                            </div>
                            <div class="mb-3">
                                <button type="button" class="btn bwCancle" onclick="goBack()">취소</button>
                                <button type="submit" class="btn bwComple">완료</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

    <!-- ======= End Main ======= -->
    
    
    <!-- ======= Footer ======= -->
    <%@ include file="../footer.jsp" %>
    <!-- End Footer -->
   
   
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
   
   
    <!-- Vendor JS Files -->
    <script defer src="assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script defer src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script defer src="assets/vendor/chart.js/chart.umd.js"></script>
    <script defer src="assets/vendor/echarts/echarts.min.js"></script>
    <script defer src="assets/vendor/quill/quill.min.js"></script>
    <script defer src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script defer src="assets/vendor/tinymce/tinymce.min.js"></script>
    <script defer src="assets/vendor/php-email-form/validate.js"></script>
    
    
    <!-- Template Main JS File -->
    <script defer src="assets/js/main.js"></script>
</body>
</html>