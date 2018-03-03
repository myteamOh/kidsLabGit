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
<meta http-equiv="refresh" content="1; url=/admin/teacher/join.do">
<title>Insert title here</title>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<c:if test="${msg == 'success'}">
		<h2>${sessionScope.adminLogin}님환영합니다.</h2>
	</c:if>
	<div class="contentContainer">
		<div class="well">
			<div class="tac">
				KidsLab 관리자 사이트에 접속해 주셔서 감사합니다.<br /> <span id="text">잠시후
					자동으로 관리자 결제관리 페이지로 이동합니다.</span>
			</div>
		</div>
	</div>
</body>
</body>
</html>