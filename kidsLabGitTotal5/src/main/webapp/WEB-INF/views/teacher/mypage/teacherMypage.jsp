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
<script type="text/javascript">
	$(function() {
		$(".studentListBtn").click(function() {
			var course_no = $("#course_no").val();
			window.open("/teacher/student/courseStudentList.do?course_no=" + course_no, "list", "width=1000, height=700");
		});
		$("#coursePageBtn").click(function() {
			var course_no = $("#course_no").val();
			$("#t_form").attr({
				"method" : "post",
				"action" : "/coursepage/coursemain"
			});
			$("#t_form").submit();
		})
	})
</script>
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
		<form id="t_form">
			<table id="list">
				<c:choose>
					<c:when test="${not empty teacherCourseList }">
						<tr>
							<th>내 강의</th>
						</tr>
						<tr>
							<td><select id="course_no" name="course_no"><c:forEach
										var="course" items="${teacherCourseList }" varStatus="status">
										<option value="${course.course_no }">${course.course_name }</option>
									</c:forEach></select></td>
							<td><input type="button" value="강의페이지" id="coursePageBtn"
								class="coursePageBtn"> <input type="button"
								value="학생리스트" id="studentListBtn" class="studentListBtn"></td>
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
		</form>
	</div>
</body>
</html>