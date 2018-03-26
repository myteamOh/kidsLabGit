<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="/resources/images/common/icon.png">

<title><tiles:getAsString name="title"></tiles:getAsString></title>
<style type="text/css">
#cl-dashboard {
	display: none;
}

.row {
	width: auto;
	align-items: center;
}

.recent_notice_con {
	width: 1100px;
	margin: 0 auto;
	text-align: center;
	align-items: center;
	background-color: red;
}

.recent_notice table, th, tr, td {
	border: 1px solid #bcbcbc;
	height: 30px;
	text-align: center;
}

.recent_notice table {
	width: 700px;
}
</style>
<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/resources/include/dist/css/sticky-footer-navbar.css"
	rel="stylesheet">
<link rel="stylesheet" href="/resources/include/css/jquery.bxslider.css" />
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="/resources/include/dist/assets/js/ie-emulation-modes-warning.js"></script>
<script src=""></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<!-- Custom styles for this template -->
<link href="/resources/include/css/carousel.css" rel="stylesheet">
</head>
<script type="text/javascript">
	$(function() {
		/* 제목 클릭시 상세 페이지 이동을 위한 처리 이벤트 */
		$(".goNoticeDetail").click(function() {
			var notice_no = $(this).parents("tr").attr("data-num");
			$("#notice_no").val(notice_no);
			// 상세 페이지로 이동하기 위해 form 추가(id : detailForm)
			$("#detailForm").attr({
				"method" : "get",
				"action" : "/notice/noticeDetail"
			});
			$("#detailForm").submit();
		});
	});
</script>
<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</nav>

	<!-- Begin page content -->
	<div class="container">
		<div class="page-header">

			<!-- Carousel
   				 ================================================== -->
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img class="first-slide"
							src="/resources/images/image/kidslabmain1.jpg" alt="First slide">
						<div class="container">
							<div class="carousel-caption">
								<h1>KidsLab 강의신청</h1>
								<p>소프트웨어 교육을 통한 논리 향상, 지금 시작하세요.</p>
								<p>
									<a class="btn btn-lg btn-primary"
										href="/requestcourse/apply.do" role="button">강의신청</a>
								</p>
							</div>
						</div>
					</div>
					<div class="item">
						<img class="second-slide"
							src="/resources/images/image/kidslabmain2.jpg" alt="Second slide">
						<div class="container">
							<div class="carousel-caption">
								<h1></h1>
								<p>회원 가입을 통하여 더 많은 기능을 활용하실 수 있습니다.</p>
								<p>
									<a class="btn btn-lg btn-primary" href="/member/agree.do"
										role="button">회원가입</a>
								</p>
							</div>
						</div>
					</div>
					<div class="item">
						<img class="third-slide"
							src="/resources/images/image/kidslabmain3.jpg" alt="Third slide">
						<div class="container">
							<div class="carousel-caption">
								<h1>프로젝트 갤러리</h1>
								<p>학생들이 어떤 결과물들을 만들었을까요? 갤러리를 통해 확인하세요.</p>
								<p>
									<a class="btn btn-lg btn-primary"
										href="/client/gallery/galleryList" role="button">Browse
										gallery</a>
								</p>
							</div>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
			<!-- /.carousel -->
		</div>

		<!-- /.row -->
		<div class="row">
			<div class="col-xs-6 col-lg-4">

				<h1 align="center">공지사항</h1>
				<table width=100% cellpadding=0 cellspacing=0
					class="recent_notice table table-hover">
					<thead>
						<tr>
							<th class="order" data-value="notice_no">글번호</th>
							<th>글제목</th>
							<th data-value="notice_registerdate">작성일</th>
						</tr>
					</thead>
					<tbody id="list">
						<!-- 데이터 출력 -->
						<c:choose>
							<c:when test="${not empty mainNoticeList}">
								<c:forEach var="notice" items="${mainNoticeList}"
									varStatus="status">
									<tr class="tac" data-num="${notice.notice_no}">
										<td>${notice.notice_no }</td>
										<td class="goNoticeDetail tal">${notice.notice_title}</td>
										<td>${notice.notice_registerdate}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="4" class="tac">등록된 게시 물이 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>



			</div>
			<!-- /.col-xs-6.col-lg-4 -->
			<div class="col-xs-6 col-lg-4">
				<img src="/resources/images/image/kidslabpromotion.jpg"
					class="img-responsive" alt="Responsive image" />

			</div>
			<!-- /.col-xs-6.col-lg-4 -->
			<div class="col-xs-6 col-lg-4">
				<!-- 				<img src="/resources/images/image/ClientRequirements.jpg"
					class="img-responsive" alt="Responsive image" /> -->
			</div>
			<!-- /.col-xs-6.col-lg-4 -->
		</div>
		<!-- /row -->
		<hr>

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="col-lg-4">
				<img class="img-circle" src="/resources/images/image/row1.gif"
					alt="Generic placeholder image" width="140" height="140">
				<h2>회사소개</h2>
				<p>KidsLab에 대하여 궁금하신 분께 알려드립니다.</p>
				<p>
					<a class="btn btn-default" href="/client/introduce/lab"
						role="button">View details &raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img class="img-circle" src="/resources/images/image/row2.gif"
					alt="Generic placeholder image" width="140" height="140">
				<h2>강의소개</h2>
				<p>KidsLab에서 진행되고 있는 강의들.</p>
				<p>
					<a class="btn btn-default" href="/client/introduce/cource"
						role="button">View details &raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img class="img-circle" src="/resources/images/image/row3.gif"
					alt="Generic placeholder image" width="140" height="140">
				<h2>오시는 길</h2>
				<p>KidsLab에 오시는 길</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
		</div>

	</div>
	<!-- /.container -->

	<footer class="footer">
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</footer>

	<!-- Bootstrap core JavaScript ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
	<script src="/resources/include/dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="/resources/include/dist/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>