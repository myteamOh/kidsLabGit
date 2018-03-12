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

</head>
<body>

	<section>
		<div>
			<h4>${courseDetail.course_name}강의명</h4>
		</div>

		<div>${courseDetail.teacher_name}강사명</div>

		<div>${courseDetail.course_level}대상학년</div>

		<div>${courseDetail.course_startdate} ~ ${courseDetail.course_enddate}강의기간</div>

		<div>${courseDetail.course_time}강의요일및시간</div>

		<div>
			<label for="okBtn">수강신청 하러가기</label>
		</div>

		<div>${courseDetail.course_summary}과정소개</div>

		<form>
			<div>
				<input type="button" id="okBtn" value="수강신청${courseDetail.course_totalperson}">
				<input type="button" id="cancelBtn" value="목록으로">
			</div>
		</form>
	</section>

</body>
</html>