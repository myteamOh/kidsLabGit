<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/login.css">

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="/resources/include/js/parentLogin.js"></script>
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
	<form action="" class="form-horizontal">
		<h4>학생 / 학부모 회원 로그인</h4>
		<div class="container aligncenter">

			<div id="inputText" class="input-group">
				<label for="identity">I.D</label> <br /> <input type="text"
					class="form-control" id="identity" required="required"> <label
					for="password">Password</label> <br />
				<input type="password" class="form-control" id="password"
					required="required">
			</div>
			<br />
			<div class=" form-horizontal aligncenter2">
				<input type="button" id="loginBtn" class="btn btn-primary col-xs-4"
					value="로그인"><br> <br>
				<input type="button" id="parentJoinBtn"
					class="btn btn-primary col-xs-4" value="회원가입"> <br>
				<br> <input type="button" id="findBtn"
					class="btn btn-primary col-xs-4" value="아이디 / 비밀번호 찾기"> <br>
				<br> <input type="button" id="teacherLoginBtn"
					class="btn btn-success col-xs-4" value="강사 로그인">
			</div>
		</div>
	</form>
</body>
</html>