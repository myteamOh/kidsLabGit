<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의신청(상세보기)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="/resources/include/js/courseApply.js"></script>

</head>
<body>

	<section>
		<form class="applyForm">
			<input type="hidden" id="courseNum" name="course_no"
				value="${courseDetail.course_no}">
		</form>
		<input type="hidden" id="limitCount" value="${courseCount}">

		<div>
			<h4>${courseDetail.course_name}</h4>
		</div>

		<div>강사명 : ${courseDetail.teacher_name}</div>

		<div>대상학년 : ${courseDetail.course_level}</div>

		<div>강의 기간 : ${courseDetail.course_startdate}~
			${courseDetail.course_enddate}</div>

		<div>강의 요일 및 시간 : ${courseDetail.course_time}</div>

		<div>강의 과목 : ${courseDetail.course_subject}</div>
		
		<div>강의실 : ${courseDetail.course_room}</div>
		
		<div>강의료 : ${courseDetail.course_pay}</div>

		<div>
			<a href="#okBtn">수강신청 하러가기</a>
		</div>

		<div>${courseDetail.course_summary}과정소개</div>

		<form>
			<div>
				<input type="button" class="applyBtn"
					value="수강신청(${courseDetail.course_totalperson} / ${courseCount})">
				<input type="button" class="toListBtn" value="목록으로">
			</div>
		</form>
	</section>

</body>
</html>