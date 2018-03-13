<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 신청(결제 확인)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="/resources/include/js/courseApply.js"></script>

</head>
<body>

	<section>

		<h4>수강정보 및 결제 확인</h4>

		<table>
			<tr>
				<td>강의명</td>
				<td>${requestcoursedata.course_name}</td>
			</tr>

			<tr>
				<td>강의 기간</td>
				<td>${requestcoursedata.course_startdate}~
					${requestcoursedata.course_enddate}</td>
			</tr>

			<tr>
				<td>강의 요일 및 시간</td>
				<td>${requestcoursedata.course_time}</td>
			</tr>

			<tr>
				<td>학생이름</td>
				<td>${requestcoursedata.student_name}</td>
			</tr>

			<tr>
				<td>결제금액</td>
				<td>${requestcoursedata.requestcourse_payamount}</td>
			</tr>

			<tr>
				<td>결제방법</td>
				<td>${requestcoursedata.requestcourse_paymethod}</td>
			</tr>

			<tr>
				<td>입금 은행 및 계좌</td>
				<td>${requestcoursedata.bank_and_account}</td>
			</tr>
		</table>

		<input type="button" class="toMainBtn" value="확인">

	</section>

</body>
</html>