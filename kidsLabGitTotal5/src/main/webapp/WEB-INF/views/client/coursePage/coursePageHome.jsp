<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/coursePageHome.css">

</head>
<body>

	<div>
		<table class="courseData">
			<tr>
				<td>강의명</td>
				<td>${course.course_name}</td>
			</tr>
			<tr>
				<td>강사</td>
				<td>${course.teacher_name}(${course.teacher_id})</td>
				<td>강사 연락처</td>
				<td>${course.teacher_phone}</td>
			</tr>
			<tr>
				<td>강의 과목</td>
				<td>${course.course_subject}</td>
			</tr>
			<tr>
				<td>강의 요일 및 시간</td>
				<td>${course.course_time}</td>
			</tr>
			<tr>
				<td>강의실</td>
				<td>${course.course_room}</td>
			</tr>
			<tr>
				<td>강의 기간</td>
				<td>${course.course_startdate}~${course.course_enddate}</td>
			</tr>
			<tr>
				<td>강의개요</td>
				<td>${course.course_summary}</td>
			</tr>
		</table>
	</div>

	<div class="noticeDiv">
		<table class="mainList" border="1">
			<caption>공지사항</caption>
			<colgroup>
				<col width="10%">
				<col width="50%">
				<col width="15%">
				<col width="30%">
			</colgroup>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:choose>
				<c:when test="${not empty cdNoticeList}">
					<c:forEach var="noticeList" items="${cdNoticeList}">
						<tr>
							<td>${noticeList.coursedata_no}</td>
							<td>${noticeList.coursedata_title}</td>
							<td>강사</td>
							<td>${noticeList.coursedata_registerdate}</td>
						</tr>
					</c:forEach>
				</c:when>

				<c:otherwise>
					<tr>
						<td colspan="4">최근 공지사항이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

	<div class="dataDiv">
		<table class="mainList" border="1">
			<caption>자료실</caption>
			<colgroup>
				<col width="10%">
				<col width="50%">
				<col width="15%">
				<col width="30%">
			</colgroup>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:choose>
				<c:when test="${not empty cdDataList}">
					<c:forEach var="dataList" items="${cdDataList}">
						<tr>
							<td>${dataList.coursedata_no}</td>
							<td>${dataList.coursedata_title}</td>
							<td>강사</td>
							<td>${dataList.coursedata_registerdate}</td>
						</tr>
					</c:forEach>
				</c:when>

				<c:otherwise>
					<tr>
						<td colspan="4">최근 자료가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

</body>
</html>