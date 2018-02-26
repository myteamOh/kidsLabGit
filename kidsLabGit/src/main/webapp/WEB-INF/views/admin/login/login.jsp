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
<!-- 모바일 웹페이지설정 -->
<link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png">
<!-- 모바일 웹 페이시 설정끝 -->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnLogin").click(function() {
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
				alert("아이디를 입력하세요.");
				$("#userPw").focus();
				return;
			}
			// 폼 내부의 데이터를 전송할 주소
			console.log(userId);
			console.log(userPw);
			document.form1.action = "${path}/admin/loginCheck.do"
			// 제출
			document.form1.submit();
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
	<h2>로그인</h2>
	<form name="form1" method="post">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<td><input name="userId" id="userId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw" id="userPw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnLogin">로그인</button> <c:if
						test="${msg == 'failure'}">
						<div style="color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div>
					</c:if> <c:if test="${msg == 'logout'}">
						<div style="color: red">로그아웃되었습니다.</div>
					</c:if>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>