<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의신청(수강신청 및 결제)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
	
</head>
<body>
	<section>
		<div>
			<form>
				<div>강의명</div>
				<div>대상학년</div>
				<div>강의기간</div>
				<div>강의요일 및 시간</div>
				<div>
					<label for="courseApply">수강신청 하러가기▼</label>
				</div>
				<div>과정 상세소개</div>
				<div>
					<input type="button" id="courseApply" value="수강신청"> <input
						type="button" value="목록으로">
				</div>
			</form>
		</div>
	</section>
</body>
</html>