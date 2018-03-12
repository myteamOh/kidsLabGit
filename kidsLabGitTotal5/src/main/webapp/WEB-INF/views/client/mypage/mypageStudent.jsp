<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage(학생)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/mypage.js"></script>

</head>
<body>

	<section>
		<div>
			<form id="pwCheckForm">
				<input type="hidden" id="studentNum" name="student_no" value="${Login.student_no}">
			</form>

			<form>
				<div>
					<label>안녕하세요 ${Login.userName}님. 반갑습니다. <br>
						${Login.userId}
					</label>
				</div>
				<br>
				<div>
					<input type="button" class="modifyInfo" value="회원 정보 수정">
				</div>
			</form>

			<c:choose>
				<c:when test="">
					<div>
						<p>수강중인 강의</p>
						<div>수강중인 강의가 없습니다.</div>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<p>수강중인 강의</p>
						<c:forEach items="">
				수강중인 강의 리스트.
				</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</section>

</body>
</html>