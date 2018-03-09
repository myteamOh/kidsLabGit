<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">KidsLab</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<%-- dropdown 메뉴 추가시 다시 활성화  --%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">소개 <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">학원 소개</a></li>
						<li><a href="#">강사 소개</a></li>
						<li><a href="#">오시는 길</a></li>
						<li><a href="#">교육 과정</a></li>
					</ul></li>
				<li><a href="#">강의신청</a></li>
				<li><a href="#">갤러리</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">게시판
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">공지사항</a></li>
						<li><a href="#">강의 평가</a></li>
					</ul></li>
				<li><a href="#">FAQ</a></li>
				<c:if test="${sessionScope.Login == null }">
					<li><a href="/login/login.do">로그인</a></li>
				</c:if>
				<c:if test="${sessionScope.Login != null }">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">${Login.userId }님(${Login.userName })
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="/login/logout.do">로그아웃</a></li>
							<li><c:choose>
									<c:when test="${Login.userId.contains('@') }">
										<a href="/mypage/parentMypage.do">마이페이지</a>
									</c:when>
									<c:otherwise>
										<a href="/mypage/studentMypage.do">마이페이지</a>
									</c:otherwise>
								</c:choose></li>
							<li><a href="#">1:1 문의</a></li>
						</ul></li>
				</c:if>
			</ul>
		</div>
		<!-- /.nav-dollapse -->
	</div>
</body>
</html>