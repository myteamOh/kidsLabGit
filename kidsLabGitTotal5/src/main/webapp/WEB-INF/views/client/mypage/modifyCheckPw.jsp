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

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/modifyCheckPw.css">

</head>
<body>

	<section>
		<form id="modifyForm">
			<div>
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" class="userId" name="userId"
							readonly="readonly" value="${Login.userId}"></td>
					</tr>
					<tr>
						<td><label>비밀번호 확인</label></td>
						<td><input type="password" class="userPw" name="userPw"></td>
					</tr>
				</table>
				<div>
					<label id="loginAlert"> <c:if test="${msg == 'fail'}">비밀번호가 일치하지 않습니다.</c:if></label>
				</div>
				<div class="btnPart">
					<input type="button" class="okBtnPwCheck" value="비밀번호 확인"><input
						type="button" class="cancelBtn" value="취소">
				</div>
			</div>
		</form>
	</section>

</body>
</html>