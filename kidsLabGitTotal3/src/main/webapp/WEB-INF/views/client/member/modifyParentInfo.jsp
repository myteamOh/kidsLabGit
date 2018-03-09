<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정(학부모)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#cancelBtn").click(function() {
			location.href = "/mypage/parentMypage.do";
		});
	});
</script>
</head>
<body>

	<section>
		<div>
			<form>
				<input type="hidden" id="" value="${parent.parent_no}">

				<table>
					<tr>
						<td><label>이름</label></td>
						<td><input type="text" readonly="readonly"
							value="${parent.parent_name}"></td>
					</tr>

					<tr>
						<td><label>아이디</label></td>
						<td><input type="email" readonly="readonly"
							value="${parent.userId}"></td>
					</tr>

					<tr>
						<td><label>현재 비밀번호</label></td>
						<td><input type="password" maxlength="13"><label>확인
								라벨</label></td>
					</tr>

					<tr>
						<td><label>새 비밀번호</label></td>
						<td><input type="password" maxlength="13"><label>확인
								라벨</label></td>
					</tr>

					<tr>
						<td></td>
						<td><label>*비밀번호는 문자, 숫자, 특수문자를 <br> 사용하여 8 ~
								13자리로 만들어 주세요.
						</label></td>
					</tr>

					<tr>
						<td><label>새 비밀번호 확인</label></td>
						<td><input type="password" maxlength="13"> <label>확인
								라벨</label></td>
					</tr>

					<tr>
						<td><label>*</label> <label>연락처</label></td>
						<td><input type="text" value="${parent.parent_phone}"
							maxlength="11"> <label>확인 라벨</label></td>
					</tr>

					<tr>
						<td></td>
						<td><c:choose>
								<c:when test="${parent.parent_smsagree == 'Y'}">
									<input type="checkbox" id="parent_smsagree"
										name="parent_smsagree" value="Y" checked="checked">
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="parent_smsagree"
										name="parent_smsagree" value="Y">
								</c:otherwise>
							</c:choose><label for="parent_smsagree">SMS 수신 동의(선택)</label> <br> <c:choose>
								<c:when test="${parent.parent_emailagree == 'Y'}">
									<input type="checkbox" id="parent_emailagree"
										name="parent_emailagree" value="Y" checked="checked">
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="parent_emailagree"
										name="parent_emailagree" value="Y">
								</c:otherwise>
							</c:choose><label for="parent_emailagree">E-mail 수신 동의(선택)</label> <br>

							<c:choose>
								<c:when test="${parent.parent_kakaoagree == 'Y'}">
									<input type="checkbox" id="parent_kakaoagree"
										name="parent_kakaoagree" value="Y" checked="checked">
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="parent_kakaoagree"
										name="parent_kakaoagree" value="Y">
								</c:otherwise>
							</c:choose> <label for="parent_kakaoagree">KakaoTalk 수신 동의(선택)</label></td>
					</tr>

					<tr>
						<td><label>*</label> <label>주소</label></td>
						<td><input type="text" value="${parent.parent_address}"><br>
							<label>확인 라벨</label></td>
					</tr>
				</table>

				<div>
					<input type="button" id="okBtn" value="수정 완료"> <input
						type="button" id="cancelBtn" value="취소"> <input
						type="button" id="secessionBtn" value="탈퇴">
				</div>

			</form>
		</div>
	</section>

</body>
</html>