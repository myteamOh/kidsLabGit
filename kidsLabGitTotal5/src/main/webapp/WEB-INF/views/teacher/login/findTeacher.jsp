<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher 아이디 / 비밀번호 찾기</title>
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/findUser.js"></script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>

	<div>
		<h2>Teacher 아이디 찾기</h2>
		<form id="teacherIdSearch">
			<table>
				<tr>
					<td colspan="2"><label>아이디 찾기</label></td>
				</tr>
				<tr>
					<td><label>이름</label></td>
					<td><input type="text" id="teacher_name" name="teacher_name"
						maxlength="5"></td>
				</tr>
				<tr>
					<td><label>연락처</label></td>
					<td><input type="text" id="teacher_phone" name="teacher_phone"
						maxlength="11"></td>
				</tr>
				<tr id="msg">
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="findIdBtn"
						value="아이디 찾기"></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<form id="teacherPwSearch">
			<table>
				<tr>
					<td colspan="2"><label>비밀번호 찾기</label></td>
				</tr>
				<tr>
					<td><label>아이디</label></td>
					<td><input type="email" id="teacher_idPw" name="teacher_id"></td>
				</tr>
				<tr>
					<td><label>이름</label></td>
					<td><input type="text" id="teacher_namePw" name="teacher_name"
						maxlength="5"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="requestPwBtn"
						value="임시 비밀번호 발급"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>