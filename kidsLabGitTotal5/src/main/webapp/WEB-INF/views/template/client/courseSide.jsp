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
<script type="text/javascript" src="/resources/include/js/coursePage.js"></script>

<link type="text/css" rel="stylesheet" href="/resources/include/css/courseSide.css">

<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>

</head>
<body>

	<div>
		<ul id="sidebar">
			<li><a class="coursePageHome">Home</a></li>
			<li><a class="coursePageBoardList">강의 게시판</a></li>
		</ul>

		<form id="courseNo">
			<input type="hidden" id="courseNo" name="course_no"
				value="${cNum}" readonly="readonly">
		</form>
	</div>

</body>
</html>