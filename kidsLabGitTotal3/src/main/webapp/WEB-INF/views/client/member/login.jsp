<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,  initial-scale=1.0,  maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Login</title>
<!-- 모바일 웹 페이지 설정 -->
<!-- <link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png" /> -->
<!-- 모바일 웹 페이지 설정 끝 -->
<!--[if lt IE 9]> <script src="/resources/include/js/html5shiv.js"></script> <![endif]-->
<link type="text/css" rel="stylesheet"
	href="/resources/include/css/login.css">
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/login.js"></script>
<style type="text/css">
.aligncenter {
	padding-left: 38%;
}

.aligncenter2 {
	padding-left: 3.5%;
}
</style>
</head>
<body>
	<form id="loginForm" class="form-horizontal">
		<h4>학생 / 학부모 회원 로그인</h4>
		<div class="container aligncenter">

			<div id="inputText" class="input-group">
				<label for="userId">I.D</label> <br /> <input type="text"
					class="form-control" id="userId" required="required" name="userId">
				<label for="userPw">Password</label> <br /> <input type="password"
					class="form-control" id="userPw" required="required" name="userPw">
			</div>
			<div>
				<label id="loginAlert"> <c:if test="${msg == 'fail'}">아이디 또는 비밀번호가 일치하지 않거나 존재하지 않는 회원입니다.</c:if>
				</label>
			</div>
			<br />
			<div class=" form-horizontal aligncenter2">
				<input type="button" id="loginBtn" class="btn btn-primary col-xs-4"
					value="로그인"><br> <br> <input type="button"
					id="parentJoinBtn" class="btn btn-primary col-xs-4" value="회원가입">
				<br> <br> <input type="button" id="findBtn"
					class="btn btn-primary col-xs-4" value="아이디 / 비밀번호 찾기"> <br>
				<br> <input type="button" id="teacherLoginBtn"
					class="btn btn-success col-xs-4" value="강사 로그인">
			</div>
		</div>
	</form>
</body>
</html>