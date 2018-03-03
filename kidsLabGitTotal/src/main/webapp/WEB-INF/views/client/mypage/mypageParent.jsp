<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage(학부모)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="/resources/include/js/mypageParent.js"></script>
</head>
<body>
	<form>
		<div>
			<label>안녕하세요 ${parentLogin.parent_name}님. 반갑습니다. <br>
				${parentId}
			</label>
		</div>
		<br>
		<div>
			<input type="button" id="modifyParent" value="회원 정보 수정">
		</div>
		<br>
		<div>
			<input type="button" id="studentJoin" value="자녀 추가">
		</div>
	</form>

	<br> --------------------------------------------------
	<br>
	<a href="#">자녀 추가 <img src="/resources/include/img/plus.png"
		width="12" height="12"></a> core foreach



</body>
</html>