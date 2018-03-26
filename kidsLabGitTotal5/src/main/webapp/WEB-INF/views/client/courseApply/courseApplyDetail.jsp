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

<link type="text/css" rel="stylesheet" href="/resources/include/css/courseApplyDetail.css">

</head>
<body>

	<section>
		<form class="applyForm">
			<input type="hidden" id="courseNum" name="course_no"
				value="${courseDetail.course_no}">
		</form>
		<input type="hidden" id="limitCount" value="${courseCount}"> <input
			type="hidden" id="idCheck" value="${Login.userId}">

		<table>
			<caption>${courseDetail.course_name}</caption>
			<tr>
				<td>강사명 </td>
				<td>${courseDetail.teacher_name}</td>
			</tr>
			<tr>
				<td>대상학년 </td>
				<td>${courseDetail.course_level}</td>
			</tr>
			<tr>
				<td>강의 기간 </td>
				<td>${courseDetail.course_startdate}~
					${courseDetail.course_enddate}</td>
			</tr>
			<tr>
				<td>강의 요일 및 시간 </td>
				<td>${courseDetail.course_time}</td>
			</tr>
			<tr>
				<td>강의 과목 </td>
				<td>${courseDetail.course_subject}</td>
			</tr>
			<tr>
				<td>강의실 </td>
				<td>${courseDetail.course_room}</td>
			</tr>
			<tr>
				<td>강의료 </td>
				<td>${courseDetail.course_pay}</td>
				<td><a href="#bottom">수강신청 하러가기</a></td>
			</tr>
		</table>

		<div class="summaryPart">${courseDetail.course_summary}</div>

		<form>
			<div class="btnPart">
				<input type="button"  class="applyBtn" id="bottom"
					value="수강신청(정원 : ${courseDetail.course_totalperson} / 신청가능 인원 : ${courseCount})">
				<input type="button" class="toListBtn" value="목록으로">
			</div>
		</form>
	</section>

</body>
</html>