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

<title><tiles:getAsString name="teacherTitle"></tiles:getAsString></title>
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
<script type="text/javascript"
	src="/resources/include/js/jquery.bxslider.js"></script>
<script type="text/javascript">
	//<![CDATA[
	var jQ182 = $.noConflict();
	jQ182(document).ready(function() {
		jQ182('.bxslider').bxSlider({
			auto : true,
			speed : 500,
			pause : 4000,
			mode : 'horizontal',
			autoControls : false,
			pager : true,
		});
	});
	//]]>
</script>

</head>
<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<tiles:insertAttribute name="teacherHeader"></tiles:insertAttribute>
	</nav>

	<!-- Begin page content -->
	<div class="container">
		<div class="page-header">
			<div class="jumbotron">
				<div class="bxslider_con">
					<ul class="bxslider">
						<li><img src="/resources/images/image/kidslabmain1.jpg" /></li>
						<li><img src="/resources/images/image/kidslabmain2.jpg" /></li>
						<li><img src="/resources/images/image/kidslabmain3.jpg" /></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-6 col-lg-4">

				<h1 align="center">공지사항</h1>
				<table width=100% cellpadding=0 cellspacing=0 class="recent_notice">
					<tr>
						<td width="50px">30</td>
						<td><a href='#'>2018년 3월 4일자 공지사항</a></td>
						<td width="100px">2018-03-04</td>
					</tr>
					<tr>
						<td>29</td>
						<td><a href='#'>2018년 3월 3일자 공지사항</a></td>
						<td>2018-03-03</td>
					</tr>
					<tr>
						<td>28</td>
						<td><a href='#'>2018년 3월 2일자 공지사항</a></td>
						<td>2018-03-02</td>
					</tr>
					<tr>
						<td>27</td>
						<td><a href='#'>2018년 2월 28일자 공지사항</a></td>
						<td>2018-02-28</td>
					</tr>
					<tr>
						<td>26</td>
						<td><a href='#'>2018년 2월 26일자 공지사항</a></td>
						<td>2018-02-26</td>
					</tr>
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


	</div>
	<!-- /.container -->

	<footer class="footer">
		<tiles:insertAttribute name="teacherFooter"></tiles:insertAttribute>
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