<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/login.css">

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/login.js"></script>

</head>
<body>
	<section>
		<div class="mainContainer">


			<form id="loginForm">

				<h4>학생 / 학부모 회원 로그인</h4>

				<div id="inputText">
					<div>
						<label for="userId">I.D</label> <input type="text" id="userId"
							name="userId" required="required">
					</div>

					<div>
						<label for="userPw">Password</label> <input type="password"
							id="userPw" name="userPw" required="required">
					</div>

					<div>
						<label id="loginAlert"> <c:if test="${msg == 'fail'}">아이디 또는 비밀번호가 일치하지 않거나 존재하지 않는 회원입니다.</c:if>
						</label>
					</div>

				</div>

				<br>

				<div class="btn" align="center">
					<input type="button" id="loginBtn" value="로그인"> <input
						type="button" id="parentJoinBtn" value="회원가입"> <input
						type="button" id="findBtn" value="아이디 / 비밀번호 찾기"> <input
						type="button" id="teacherLoginBtn" value="강사 로그인">
				</div>

			</form>

		</div>
	</section>
</body>
</html>