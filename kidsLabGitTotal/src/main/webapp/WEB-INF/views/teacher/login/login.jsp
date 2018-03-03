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
<!-- <link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png"> -->
<!-- 모바일 웹 페이시 설정끝 -->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/teacherLogin.js"></script>
<script type="text/javascript">
	function errCodeCheck() {
		var errCode = '<c:out value="${errCode}" />';
		if (errCode != '') {
			// 명확한 자료형 명시를 위해 errCode의 타입을 정수형으로 변환
			switch (parseInt(errCode)) {
			case 1:
				alert("아이디 또는 비밀번호가 일치 하지 않거나 존재하지 않는\n회원입니다. 다시 로그인 해 주세요.");
				break;
			case 6:
				alert("5번이상 로그인 시도로 30초 동안 로그인 할 수 없습니다. \n잠시 후 다시 시도해 주세요.");
				break;
			}
		}
	}
</script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<div class="contentContainer">
		<div class="well">
			<c:if
				test="${teacherLogin.teacher_id == null or teacherLogin.teacher_id == '' }">
				<form id="loginForm" class="form-horizontal">
					<div class="form-group">
						<label for="teacher_id" class="col-sm-2 control-label">아 이
							디</label>
						<div class="col-sm-4">
							<input type="text" id="teacher_id" name="teacher_id"
								class="form-control" placeholder="ID">
						</div>
						<p class="form-control-static error"></p>
					</div>
					<div class="form-group">
						<label for="teacher_password" class="col-sm-2 control-label">
							비밀번호 </label>
						<div class="col-sm-4">
							<input type="password" id="teacher_password"
								name="teacher_password" class="form-control"
								placeholder="Password">
						</div>
						<p class="form-control-static error"></p>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-6">
							<input type="button" value="로그인" id="loginBtn"
								class="btn btn-default" /> <input type="button" value="회원가입"
								id="joinBtn" class="btn btn-default" />
						</div>
					</div>
				</form>
			</c:if>
			<c:if
				test="${teacherLogin.teacher_id != null and teacherLogin.teacher_id != '' }">
				<fieldset id="loginAfter">
					<legend>
						<strong>[${teacherLogin.teacher_name }] 님 반갑습니다.</strong>
					</legend>
					<span id="memberMenu" class="tac"> <a
						href="/member/logout.do">로그아웃</a> &nbsp;&nbsp;&nbsp; <a
						href="/member/modify.do">정보수정(비밀번호 변경)</a>&nbsp;&nbsp;&nbsp; <a
						href="/member/delete.do">회원탈퇴</a>
					</span>
				</fieldset>
			</c:if>
		</div>
	</div>
</body>
</html>