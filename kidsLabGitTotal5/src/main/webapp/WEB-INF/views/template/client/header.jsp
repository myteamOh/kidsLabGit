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
			<a href="/"><img class="navbar-brand"
				src="/resources/images/image/kidslablogo3.png" onclick="clickBtn();"
				border="0" /></a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<%-- dropdown 메뉴 추가시 다시 활성화  --%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">소개 <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/client/introduce/lab">학원 소개</a></li>
						<li><a href="/client/introduce/teacher">강사 소개</a></li>
						<li><a href="/client/introduce/waytocome">오시는 길</a></li>
						<li><a href="/client/introduce/cource">교육 과정</a></li>
					</ul></li>
				<c:choose>
					<c:when test="${sessionScope.teacherLogin != null }">
						<li><a href="/teacher/course/registForm">강의등록</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/requestcourse/apply.do">강의신청</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sessionScope.teacherLogin != null }">
						<li><a href="/teacher/gallery/galleryList">갤러리</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/client/gallery/galleryList">갤러리</a></li>
					</c:otherwise>
				</c:choose>

				<li><a href="/notice/noticeList">공지사항</a></li>
				<li><a href="/client/faq/faqList">FAQ</a></li>
				<c:if
					test="${sessionScope.Login == null and sessionScope.teacherLogin == null}">
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
							<c:if test="${Login.userId.contains('@')}">
								<li><a href="/inquiry/inquiryList.do">1:1 문의</a></li>
							</c:if>
						</ul></li>
				</c:if>
				<c:if test="${sessionScope.teacherLogin != null }">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">${teacherLogin.teacher_id }
							님<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="/teacher/logout.do">로그아웃</a></li>
							<li><a href="/teacher/mypage">마이페이지</a></li>
						</ul></li>
				</c:if>
			</ul>
		</div>
		<!-- /.nav-dollapse -->
	</div>
</body>
</html>