<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<!--     <link rel="icon" href="../../favicon.ico"> -->

<title>회사소개</title>

<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- Custom styles for this template -->
<link href="/resources/include/css/carousel.css" rel="stylesheet">

<style type="text/css">
.iu-box {
	width: 50%;
	border: 1px solid olivedrab;
	text-align: center;
	font-weight: bold;
}
</style>


</head>
<!-- NAVBAR
================================================== -->
<body>



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
				<img class="first-slide" src="/resources/images/image/lab1.jpg"
					alt="First slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>5층 전경</h1>

					</div>
				</div>
			</div>
			<div class="item">
				<img class="second-slide" src="/resources/images/image/lab2.jpg"
					alt="Second slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>프론트 데스크와 4층 전경</h1>

					</div>
				</div>
			</div>
			<div class="item">
				<img class="third-slide" src="/resources/images/image/lab3.jpg"
					alt="Third slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>One more for good measure.</h1>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#" role="button">Browse
								gallery</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<!-- /.carousel -->


	<!-- Marketing messaging and featurettes
    ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->
	<div class="container marketing">

		<!-- START THE FEATURETTES -->

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					인사말 <span class="text-muted">한국 소프트웨어 교육의 중심</span>
				</h2>
				<p class="lead">뉴욕 타임즈 컬럼니스트 토머스 프리드먼(Thomas L. Friedman)은 "우리는
					취직하는 시대를 살았지만, 우리 아이들은 직업을 발명해야하는 시대를 살아가야 합니다"라고 이야기하며, "아이들을 입시
					준비생이 아닌 혁신 준비생으로 키워야하고, 아이들을 혁신 준비생으로 키우기 위해서는 검색하면 찾을 수 있는 지식을
					암기하기보다는, 비판적 사고와 협업 능력, 소통 능력을 키워야 합니다"라고 강조하였습니다. KIDSLAB에서 아이들은
					협업과 소통을 통해 삶(생활)의 구체적인 문제를 발견하고, 해결방안을 찾는 교육을 받습니다.</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block"
					src="/resources/images/image/insa.jpg"
					alt="Generic placeholder image"
					style="width: 500px; height: 350px;">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7 col-md-push-5">
				<h2 class="featurette-heading">
					회사연혁 <span class="text-muted">since 2017.05~</span>
				</h2>
				<p class="lead">2017년 05월 응용소프트웨어개발 04회 8강의실에서 시작했습니다. 처음에 JAVA
					부터 시작하여 회사연혁 설명문입니다. 이걸 그렇게 심각하게 읽으실 필요는 없어요.</p>
			</div>
			<div class="col-md-5 col-md-pull-7">
				<img class="featurette-image img-responsive center-block"
					src="/resources/images/image/companyyh.jpg"
					alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">

		<article>
			<div class="iu-text text-142">
				<h1 align="center">PRESS RELEASES</h1>
			</div>
			<div align="center">
				<div class="iu-box box-503">
					<a class="iux-inner-link"
						href="http://news.joins.com/article/21092593" target="_blank">
						<div>
							<p>대입에 밀려난 '메이커'의 꿈 &hellip; 10대 '괴짜' 조기발굴로 진로 터주자</p>
						</div>
						<div class="iu-text text-507">
							<p>2017. 01. 08. &nbsp;중앙일보</p>
						</div>
					</a>
				</div>
				<br>
				<div class="iu-box box-504">
					<a class="iux-inner-link"
						href="http://www.mt.co.kr/view/mtview.php?type=1&no=2016122617211654540&outlink=1"
						target="_blank">
						<div>
							<p>혁신은 청년 전유물? &hellip; 스타트업 '아재'를 주목하라</p>
						</div>
						<div class="iu-text text-508">
							<p>2017. 01. 05. &nbsp;머니투데이</p>
						</div>
					</a>
				</div>
				<br>
				<div class="iu-box box-495">
					<a class="iux-inner-link"
						href="http://techm.kr/bbs/board.php?bo_table=article&wr_id=2393"
						target="_blank">
						<div class="iu-text text-497">
							<p>'창업가 경험' 제공하는 SW교육 입소문</p>
						</div>
						<div class="iu-text text-499">
							<p>2016. 07. 28. &nbsp;테크엠</p>
						</div>
					</a>
				</div>
				<br>
				<div class="iu-box box-496">
					<a class="iux-inner-link"
						href="http://m.chosun.com/svc/article.html?sname=news&contid=2016013101190"
						target="_blank">
						<div class="iu-text text-498">
							<p>아이 스스로 만들어 본 경험이 창직의 밑거름</p>
						</div>
						<div class="iu-text text-500">
							<p>20156. 02. 01. &nbsp;조선일보</p>
						</div>
					</a>
				</div>
				<br>
				<div class="iu-box box-158">
					<a class="iux-inner-link"
						href="http://www.bloter.net/archives/231281" target="_blank">
						<div>
							<p>"한글로 시간 알려주는 시계, 3D프린터로 만들었어요"</p>
						</div>
						<div class="iu-text text-164">
							<p>2015. 06. 29. &nbsp;블로터</p>
						</div>
					</a>
				</div>
			</div>
		</article>
		<hr class="featurette-divider">





	</div>


	<!-- /END THE FEATURETTES -->


	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/resources/include/js/jquery-1.12.4.min.js"></script>
	<script src="/resources/include/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="/resources/include/js/holder.min.js"></script>

</body>
</html>
