<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/mypage.js"></script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<form id="teacherModifyForm">
		<div>
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" class="teacher_id" name="teacher_id"
						readonly="readonly" value="${teacherLogin.teacher_id}"></td>
				</tr>
				<tr>
					<td><label>비밀번호 확인</label></td>
					<td><input type="password" class="teacher_password"
						name="teacher_password"></td>
				</tr>
			</table>
			<div>
				<label id="loginAlert"> <c:if test="${msg == 'fail'}">비밀번호가 일치하지 않습니다.</c:if></label>
			</div>
			<input type="button" class="teacherOkBtnPwCheck" value="비밀번호 확인"><input
				type="button" class="cancelBtn" value="취소">
		</div>
	</form>
</body>
</html>