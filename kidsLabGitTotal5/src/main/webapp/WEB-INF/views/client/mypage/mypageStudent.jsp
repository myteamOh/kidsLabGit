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

</head>
<body>

	<section>
		<div>
			<form id="pwCheckForm">
				<input type="hidden" id="studentNum" name="student_no"
					value="${Login.student_no}">
			</form>

			<form>
				<div>
					<label>안녕하세요 ${Login.userName}님. 반갑습니다. <br>
						${Login.userId}
					</label>
				</div>
				<br>
				<div>
					<input type="button" class="modifyInfo" value="회원 정보 수정">
				</div>
			</form>

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
											<td><input type="button" value="강의계획서"></td>
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
							<c:forEach var="studing" items="${rcList}">
								<c:choose>
									<c:when
										test="${studing.course_status eq '진행중' && studing.requestcourse_paymentstatus eq '결제완료'}">
										<tr data-num="${studing.requestcourse_no}">
											<td>${studing.course_name}</td>
											<td><input type="button" value="강의페이지"></td>
										</tr>
									</c:when>
								</c:choose>
							</c:forEach>
						</table>
					</div>
				</c:when>

				<c:otherwise>
					<div>
						<div>수강중인 강의가 없습니다.</div>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</section>

</body>
</html>