<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage(학생)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/mypage.js"></script>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/mypageStudent.css">

</head>
<body>

	<section>
		<div>
			<!-- pw 체크를 위한 form -->
			<form id="pwCheckForm">
				<input type="hidden" id="studentNum" name="student_no"
					value="${Login.student_no}">
			</form>

			<!-- 강의페이지를 위한 폼 -->
			<form id="coursePageForm">
				<input type="hidden" id="courseNum" name="course_no"> <input
					type="hidden" id="requestcourse_no" name="requestcourse_no">
				<input type="hidden" id="course_plan" name="course_plan">
			</form>

			<form>
				<div id="hello">
					<label>안녕하세요 ${Login.userName}(${Login.userId})님. 반갑습니다.</label>
				</div>
				<br>
				<div id="modifyBtnPart">
					<input type="button" class="modifyInfo" value="회원 정보 수정">
				</div>
			</form>

			<div class="courseList">
				<c:choose>
					<c:when test="${not empty rcList}">
						<div>
							<table>
								<caption>수강 대기중인 강의</caption>
								<c:forEach var="readyCourse" items="${rcList}">
									<!-- 결제상태는 어떻게 할까요? -->
									<c:choose>
										<c:when
											test="${readyCourse.course_status eq '모집중' && readyCourse.requestcourse_paymentstatus eq '결제완료'}">
											<tr data-num="${readyCourse.requestcourse_no}">
												<td>${readyCourse.course_name}</td>
												<td><a
													href="/mypage/download?course_plan=${readyCourse.course_plan }"><input
														type="button" class="coursePlan listBtn" value="강의계획서"></a></td>
											</tr>
										</c:when>
									</c:choose>
								</c:forEach>
							</table>
						</div>

						<br>

						<div>
							<table>
								<caption>수강중인 강의</caption>
								<c:forEach var="studying" items="${rcList}">
									<c:choose>
										<c:when
											test="${studying.course_status eq '진행중' && studying.requestcourse_paymentstatus eq '결제완료'}">
											<tr data-num="${studying.course_no}">
												<td>${studying.course_name}</td>
												<td><a class="courseBtn"><input type="button"
														class="coursePage listBtn" value="강의페이지"></a></td>
											</tr>
										</c:when>
									</c:choose>
								</c:forEach>
							</table>
						</div>
					</c:when>

					<c:otherwise>
						<div id="noneCourse">
							<div>수강중인 강의가 없습니다.</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>

</body>
</html>