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
	<form id="modifyForm">
		<input type="hidden" id="parentNum" value="${Login.parent_no}">
	</form>
	<form id="parentMypage">
		<div>
			<label>안녕하세요 ${Login.userName}님. 반갑습니다. <br>
				${Login.userId}
			</label>
		</div>
		<br>
		<div>
			<input type="button" class="modifyParent" value="회원 정보 수정">
		</div>
		<br>
		<c:choose>
			<c:when test="">
			자녀 없을떄
				<div>
					<input type="button" id="studentJoin" value="자녀 추가"
						src="/resources/include/img/plus.png">
				</div>
			</c:when>
			<c:otherwise>
			자녀있을때
				<div>
					<input type="button" id="studentJoin" value="자녀 추가"
						src="/resources/include/img/plus.png">
				</div>
				<c:forEach items="">
				자녀리스트
					<div>자녀이름(아이디)</div>
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

				</c:forEach>
			</c:otherwise>
		</c:choose>
	</form>

</body>
</html>