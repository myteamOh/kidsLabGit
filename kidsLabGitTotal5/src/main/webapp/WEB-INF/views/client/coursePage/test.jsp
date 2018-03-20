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

</head>
<body>

	<div>
		<label>${course.course_name}</label><br> <label>${course.teacher_name}(${course.teacher_id})</label><br>
		<label>${course.teacher_phone}</label><br> <label>${course.course_subject}</label><br>
		<label>${course.course_time}</label><br> <label>${course.course_room}강의실</label><br>
		<label>${course.course_startdate} ~ ${course.course_enddate}</label><br>
		<label>${course.course_summary}</label><br>
	</div>

	<div class="noticeDiv">
		<label>공지사항</label>
		<table border="1">
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
		<label>자료실</label>
		<table border="1">
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