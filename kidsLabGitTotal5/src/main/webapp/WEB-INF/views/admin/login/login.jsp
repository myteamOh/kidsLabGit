<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0 , 
				minimum-scale=1.0 , user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>AdminLogin</title>
<!-- Google Fonts -->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="/resources/include/css/animate.css">
<!-- Custom Stylesheet -->
<link rel="stylesheet" href="/resources/include/css/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<!-- 모바일 웹페이지설정 -->
<link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png">
<!-- 모바일 웹 페이시 설정끝 -->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginBtn").click(function() {
			// 태크.val() : 태그에 입력된 값
			// 태크.val("값") : 태그의 값을 변경 
			var userId = $("#userId").val();
			var userPw = $("#userPw").val();
			if (userId == "") {
				alert("아이디를 입력하세요.");
				$("#userId").focus(); // 입력포커스 이동
				return; // 함수 종료
			}
			if (userPw == "") {
				alert("비밀번호를를 입력하세요.");
				$("#userPw").focus();
				return;
			}
			// 폼 내부의 데이터를 전송할 주소
			console.log(userId);
			$.ajax({

			})
			$("#loginForm").attr({
				"method" : "POST",
				"action" : "/admin/loginCheck.do"
			});

			$("#loginForm").submit();

		});
	});
</script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<form name="loginForm" id="loginForm">
		<div class="container">
			<div class="top">
				<h1 id="title" class="hidden">
					<span id="logo">관리자</span>
				</h1>
			</div>
			<div class="login-box animated fadeInUp">
				<div class="box-header">
					<h2>Log In</h2>
				</div>
				<label for="userId">Username</label> <br /> <input type="text"
					id="userId" name="userId"> <br /> <label for="userPw">Password</label>
				<br /> <input type="password" id="userPw" name="userPw"> <br />
				<label id="loginAlert"> <c:if test="${msg == 'fail'}">아이디 또는 비밀번호가 일치하지 않습니다.</c:if>
				</label><br />
				<button type="button" id="loginBtn">Sign In</button>
				<br />
			</div>
		</div>
	</form>
</body>
<script>
	$(document).ready(function() {
		$('#logo').addClass('animated fadeInDown');
		$("input:text:visible:first").focus();
	});
	$('#userId').focus(function() {
		$('label[for="userId"]').addClass('selected');
	});
	$('#userId').blur(function() {
		$('label[for="userId"]').removeClass('selected');
	});
	$('#userPw').focus(function() {
		$('label[for="userPw"]').addClass('selected');
	});
	$('#userPw').blur(function() {
		$('label[for="userPw"]').removeClass('selected');
	});
</script>
</html>