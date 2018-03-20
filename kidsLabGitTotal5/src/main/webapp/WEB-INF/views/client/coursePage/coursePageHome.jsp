<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 페이지(Home)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/coursePageHome.css">

</head>
<body>

	<aside>
		<ul>
			<li class="home">Home</li>
			<li class="board">강의 게시판</li>
		</ul>

		<form>
			<input type="text" id="courseNo" name="course_no"
				value="${course.course_no}" readonly="readonly">
		</form>
	</aside>

	<section>
		<jsp:include page="test.jsp"></jsp:include>
	</section>

</body>
</html>