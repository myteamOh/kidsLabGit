<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>이용약관</title>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/agree.css">

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/agree.js"></script>

</head>

<body>
	<section>
		<div id="main_div">

			<h3>이용약관 및 개인정보 취급방침</h3>
			<br>

			<div>
				<h4>개인정보 취급방침</h4>

				<div class="terms_box">개인정보 취급방침약관 입니다.</div>

				<br> <input type="radio" id="information"><label
					for="information">개인정보 취급방침에 동의합니다.(필수)</label>
			</div>

			<br>

			<div>
				<h4>초상권 활용약관</h4>

				<div class="terms_box">초상권 활용약관 입니다.</div>

				<br> <input type="radio" id="portrait"><label
					for="portrait">초상권 활용약관에 동의합니다.(필수)</label>
			</div>

			<br>

			<div class="btn">
				<input type="button" value="취소" id="cancelBtn"
					onclick="location.href='#'"> <input type="button"
					value="동의" id="okBtn">
			</div>

		</div>
	</section>
</body>
</html>