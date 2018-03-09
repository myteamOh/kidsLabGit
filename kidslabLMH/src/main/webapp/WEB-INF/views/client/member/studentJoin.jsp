<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자녀추가</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript"
	src="/resources/include/js/studentJoin.js"></script>

</head>
<body>

	<section>
		<div>
			<form id="studentForm">

				<h2>자녀 추가(자녀 회원가입)</h2>

				<br>

				<h5 class="necessary">* 는 필수입력 사항입니다.</h5>

				<input type="hidden" id="parent_no" name="parent_no"
					value="${parentLogin.parent_no}">

				<table>
					<tr>
						<td><label class="necessary">*</label> <label>아이디</label></td>
						<td><input type="text" id="userId" name="userId"
							required="required" placeholder="ID(6~15자리 영문+숫자조합)"
							maxlength="15" onkeyup="spaceDelete(this);"
							onchange="spaceDelete(this);"> <!-- <input type="button"
						id="userIdCheck" value="아이디 중복확인"> -->
							<br> <label class="check">중복확인라벨</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>비밀번호 입력</label><br>
							<br></td>
						<td><input type="password" id="userPw" name="userPw"
							required="required" placeholder="Password" maxlength="13"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);">
							<label class="check">사용가능여부라벨</label><br> <label>*
								비밀번호는 문자, 숫자, 특수문자를 사용하여<br>8 ~ 13자리로 만들어 주세요.
						</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>비밀번호 확인</label></td>
						<td><input type="password" id="pwCheck" name="pwCheck"
							required="required" placeholder="Password Check"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);">
							<label class="check">비밀번호체크라벨</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>학생 이름</label></td>
						<td><input type="text" id="student_name" name="student_name"
							required="required" placeholder="2~5자리 한글이름" maxlength="5"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);"><label
							class="check">이름체크라벨</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>성 별</label></td>
						<td><input type="radio" id="man" name="student_gender"
							value="남" checked="checked"><label for="man">남</label> <input
							type="radio" id="woman" name="student_gender" value="여"><label
							for="woman">여</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>학 교</label></td>
						<td><input type="text" id="student_school"
							name="student_school" required="required"
							placeholder="학교 최대 20자 한글이름" maxlength="20"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);"><label
							class="check">학교체크라벨</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>생년월일</label></td>
						<td><input type="date" id="student_birthday"
							name="student_birthday" required="required"></td>
					</tr>

					<tr>
						<td><label>참고사항</label></td>
						<td><textarea rows="8" cols="35" id="student_reference"
								name="student_reference">참고사항</textarea></td>
					</tr>

				</table>
				<div>
					<input type="button" id="okBtn" value="자녀추가"> <input
						type="button" id="cancelBtn" value="취소">
				</div>
			</form>
		</div>
	</section>

</body>
</html>