<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의신청(강의조회)</title>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/courseApply.css">

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		var course_level = "<c:out value = '${courseData.course_level}' />";

		if (course_level != "") {
			$(".courseLevel").val(
					"<c:out value='${courseData.course_level}' />");
		}
	});
</script>
<script type="text/javascript"
	src="/resources/include/js/courseApply.js"></script>

</head>
<body>
	<section>
		<div>
			<h4>수강신청</h4>

			<form class="detailForm">
				<input type="hidden" id="courseNum" name="course_no">
			</form>

			<form class="search">
				<div>
					<div>
						<select class="courseLevel" name="course_level">
							<option value="all">전체</option>
							<option value="eleJunior">1~2학년</option>
							<option value="eleMiddle">3~4학년</option>
							<option value="eleSenior">5~6학년</option>
						</select>
					</div>
				</div>
			</form>

			<div>
				<table>
					<c:choose>
						<c:when test="${not empty requestCourseList}">
							<c:forEach var="requestCourse" items="${requestCourseList}">
								<tr>
									<td>
										<div data-num="${requestCourse.course_no}">
											<div id="courseImg">
												<input type="image" src="/resources/include/img/gear.jpg"
													width="100" height="100">
											</div>
											<div id="courseName">
												<span>강의명 : ${requestCourse.course_name}</span>
											</div>
											<div id="courseLevel">
												<span>강의 대상학년 : ${requestCourse.course_level}</span>
											</div>
											<div id="courseSummary">
												<span>강의 개요 : ${requestCourse.course_summary}</span>
											</div>
											<div id="courseDate">
												<span>강의 기간 : ${requestCourse.course_startdate}</span> ~ <span>${requestCourse.course_enddate}</span>
											</div>
											<input type="button" class="detailBtn" value="상세보기 및 수강신청">
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:when>

						<c:otherwise>
							<tr>
								<td>등록된 강의가 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>

		</div>
	</section>
</body>
</html>