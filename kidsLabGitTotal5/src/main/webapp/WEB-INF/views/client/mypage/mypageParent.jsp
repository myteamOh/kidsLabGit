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
<script type="text/javascript" src="/resources/include/js/mypage.js"></script>

</head>
<body>

	<section>
		<div>
			<form id="pwCheckForm">
				<input type="hidden" id="parentNum" name="parent_no"
					value="${Login.parent_no}">
			</form>

			<form id="parentMypage">
				<div>
					<label>안녕하세요 ${Login.userName}님. 반갑습니다. <br>
						${Login.userId}
					</label>
				</div>
				<br>

				<div>
					<input type="button" class="modifyInfo" value="회원 정보 수정">
				</div>
				<br>

				<!-- 여기서부터 시작 -->
				<c:choose>
					<c:when test="${not empty stuList}">
						자녀있을때
						<div>
							<input type="button" id="studentJoin" value="자녀 추가"
								src="/resources/include/img/plus.png">
						</div>
						
						<c:forEach var="child" items="${stuList}">
							자녀리스트
							<div>${child.student_name}(${child.userId})</div>

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
					</c:when>

					<c:otherwise>
						자녀 없을떄
						<div>
							<input type="button" id="studentJoin" value="자녀 추가"
								src="/resources/include/img/plus.png">
						</div>
					</c:otherwise>
				</c:choose>
			</form>
		</div>
	</section>
</body>
</html>