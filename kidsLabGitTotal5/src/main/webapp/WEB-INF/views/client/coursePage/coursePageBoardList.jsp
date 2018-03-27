<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 페이지(게시판)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<!-- <script type="text/javascript" src="/resources/include/js/coursePage.js"></script> -->

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/coursePageBoardList.css">

</head>
<body>

	<form id="detailForm">
		<input type="hidden" name="coursedata_no" id="coursedataNo">
	</form>

	<div>
		<table border="1">
			<colgroup>
				<col width="5%">
				<col width="5%">
				<col width="50%">
				<col width="15%">
				<col width="20%">
			</colgroup>
			<tr>
				<th>글번호</th>
				<th>글종류</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일(또는 최근수정일)</th>
			</tr>
			<c:choose>
				<c:when test="${not empty courseboardList}">
					<c:forEach var="postList" items="${courseboardList}">
						<tr data-num="${postList.coursedata_no}">
							<td>${postList.coursedata_no}</td>
							<td>${postList.coursedata_status}</td>
							<td class="postsDetail">${postList.coursedata_title}</td>
							<td>${postList.coursedata_writer}</td>
							<td>${postList.coursedata_registerdate}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">작성된 글이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

	<div>
		<input type="button" class="courseBoardWrite" value="글쓰기">
	</div>

</body>
</html>