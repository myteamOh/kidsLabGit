<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 (학생)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript"
	src="/resources/include/js/modifyStudentInfo.js"></script>
<script type="text/javascript">
	$(function() {
		$("#modifyCancelBtn").click(function() {
			location.href = "/mypage/studentMypage.do";
		});
	});
</script>

<link type="text/css" rel="stylesheet" href="/resources/include/css/modifyStudentInfo.css">

</head>
<body>

	<section>

		<div>
			<form id="studentModifying">
				<table>
				<caption>학생정보 수정</caption>
					<tr>
						<td><label>이름</label></td>
						<td><input type="text" name="student_name"
							value="${student.student_name}" readonly="readonly"></td>
					</tr>

					<tr>
						<td><label>아이디</label></td>
						<td><input type="text" name="userId"
							value="${student.userId}" readonly="readonly"></td>
					</tr>

					<tr>
						<td><label>새 비밀번호</label></td>
						<td><input type="password" id="userPw" name="userPw"
							maxlength="13"><label class="check"></label></td>
					</tr>

					<tr>
						<td></td>
						<td><label>* 비밀번호는 문자, 숫자, 특수문자를 <br> 사용하여 8 ~
								13자리로 만들어 주세요.
						</label></td>
					</tr>

					<tr>
						<td><label>새 비밀번호 확인</label></td>
						<td><input type="password" id="userPwCheck" maxlength="13">
							<label class="check"></label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>학교</label></td>
						<td><input type="text" id="student_school"
							name="student_school" value="${student.student_school}"><label
							class="check"></label></td>
					</tr>
				</table>

				<div class="btnPart">
					<input type="button" id="studentModifyBtn" value="수정 완료"> <input
						type="button" id="modifyCancelBtn" value="취소">
				</div>
			</form>
		</div>

	</section>

</body>
</html>