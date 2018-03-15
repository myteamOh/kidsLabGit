<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/mypage.js"></script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<div>
		<form id="teacherPwCheckForm">
			<input type="hidden" id="teacher_no" name="teacher_no"
				value="${teacherLogin.teacher_no }">
		</form>

		<form>
			<div>
				<label>안녕하세요 ${teacherLogin.teacher_name }님. 반갑습니다.</label>
			</div>
			<div>
				<input type="button" id="modifyTeacherBtn" class="modifyTeacherBtn"
					value="회원 정보 수정">
			</div>
		</form>
		<table id="list">
			<c:choose>
				<c:when test="${not empty teacherCourseList }">
					<tr>
						<th>내 강의</th>
					</tr>
					<tr>
						<td><select><c:forEach var="course"
									items="${teacherCourseList }" varStatus="status">
									<option value="${course.course_no }">${course.course_name }</option>
								</c:forEach></select></td>
						<td><input type="button" value="강의페이지" id="coursePageBtn"
							class="coursePageBtn"> <input type="button" value="학생리스트"
							id="studentListBtn" class="studentListBtn"></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<th>내 강의</th>
					</tr>
					<tr>
						<td colspan="2">강의가 없습니다.</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><a href="#">강의 등록하러 가기</a></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</body>
</html>