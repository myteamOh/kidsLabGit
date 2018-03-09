<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(학부모)</title>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/parentJoin.css">

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/parentJoin.js"></script>
<script type="text/javascript">
	function errCodeCheck() {

		var resultCode = '<c:out value = "${resultCode}" />';

		if (resultCode != '') {

			switch (parseInt(resultCode)) {
			case 1:
				alert("회원가입 성공!");
				break;
			case 2:
				alert("이미 가입된 회원입니다!");
				break;
			case 3:
				alert("회원가입 처리가 실패하였습니다.  잠시 후 다시 시도해 주십시오.");
				break;
			}
		}
	}
</script>
</head>
<body>

	<section>
		<div id="mainContainer">
			<form id="parentForm">

				<h2>학부모 회원가입</h2>

				<br>

				<h5 class="necessary">* 는 필수입력 사항입니다.</h5>

				<table id="joinForm">

					<!-- onkeyup event - keypress 후 keyup 시 event 발생 -->
					<!-- onchange event - 요소의 값이 변경될때 발생 -->

					<tr>
						<td><label class="necessary">*</label> <label>아이디(E-mail)</label></td>
						<td><input type="email" id="userId" name="userId"
							required="required" placeholder="I.D (E-mail)"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);">
							<!-- <input type="button"
						id="idOverlapCheck" value="아이디 중복확인"> --> <br> <label
							class="check">id</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>인증번호</label></td>
						<td><input type="text" id="certification" required="required"
							maxlength="6" placeholder="인증번호 6자리" onkeyup="spaceDelete(this);"
							onchange="spaceDelete(this);"> <input type="button"
							id="CertifiBtn" value="인증" disabled="disabled"> <input
							type="button" id="CertifiNumBtn" value="인증번호 받기"> <br>
							<label class="check"></label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>비밀번호 입력</label><br>
							<br> <br></td>
						<td><input type="password" id="userPw" name="userPw"
							maxlength="13" required="required" placeholder="P.W"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);">
							<label class="check"></label> <br> <label>* 비밀번호는
								문자, 숫자, 특수문자를 사용하여 <br> 8 ~ 13자리로 만들어 주세요.
						</label></td>
					</tr>

					<tr>
						<td></td>
						<td></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>비밀번호 확인</label></td>
						<td><input type="password" id="parentPwCheck"
							required="required" maxlength="13" placeholder="P.W Check">
							<label class="check"></label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>이름</label></td>
						<td><input type="text" id="parent_name" name="parent_name"
							required="required" maxlength="5" placeholder="2~5 자리 한글이름"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);"><label
							class="check"></label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>연락처</label></td>
						<td><input type="text" id="parent_phone" name="parent_phone"
							required="required" maxlength="11" placeholder="'-' 제외 11자리"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);"><label
							class="check"></label></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="checkbox" id="parent_smsagree"
							name="parent_smsagree" value="Y"> <label
							for="parent_smsagree">SMS 수신 동의(선택)</label> <br> <input
							type="checkbox" id="parent_emailagree" name="parent_emailagree"
							value="Y"> <label for="parent_emailagree">E-mail
								수신 동의(선택)</label> <br> <input type="checkbox"
							id="parent_kakaoagree" name="parent_kakaoagree" value="Y">
							<label for="parent_kakaoagree">KakaoTalk 수신 동의(선택)</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>알게된 경로</label></td>
						<td><input type="radio" id="search" name="parent_knowroute"
							value="internetSearch" checked="checked"><label
							for="search">인터넷 검색</label> <input type="radio" id="introduction"
							name="parent_knowroute" value="acquaintanceIntroduction"><label
							for="introduction">지인소개</label> <input type="radio" id="banner"
							name="parent_knowroute" value="banner"><label
							for="banner">현수막</label> <input type="radio" id="leaflet"
							name="parent_knowroute" value="leaflet"><label
							for="leaflet">전단지</label> <input type="radio" id="etcetera"
							name="parent_knowroute" value="etcetera"><label
							for="etcetera">기타</label></td>
					</tr>

					<tr>
						<td><label class="necessary">*</label> <label>주소</label></td>
						<td><input type="text" id="parent_address"
							name="parent_address" required="required" maxlength="50"
							placeholder="Address 최대 50자"><br> <label
							class="check"></label></td>
					</tr>
				</table>

				<br>

				<h5>자녀 등록은 회원가입 완료 후 Mypage에서 하실수 있습니다.</h5>

				<br>
				<div align="center">
					<input type="button" id="okBtn" value="회원가입"> <input
						type="button" id="cancelBtn" value="취소">
				</div>

			</form>
		</div>
	</section>
</body>
</html>