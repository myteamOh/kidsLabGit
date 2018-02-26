<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정(학부모)</title>
</head>
<body>

	<label>이름</label>
	<input type="text" readonly="readonly">

	<label>아이디</label>
	<input type="email" readonly="readonly">

	<label>현재 비밀번호</label>
	<input type="password">

	<label>새 비밀번호</label>
	<input type="password">
	<label>*비밀번호는 문자+숫자+특수문자를 모두 사용하여 8 ~ 13자리로 만들어 주세요.</label>

	<label>새 비밀번호 확인</label>
	<input type="password">
	<label>비밀번호 일치여부 확인 라벨</label>

	<label>*</label>
	<label>연락처</label>
	<input type="number">

	<input type="checkbox" value="SMS 수신 동의(선택)">
	<input type="checkbox" value="E-mail 수신 동의(선택)">
	<input type="checkbox" value="KakaoTalk 수신 동의(선택)">

	<label>*</label>
	<label>주소</label>
	<input type="text">

	<input type="button" value="수정 완료">
	<input type="button" value="취소">
	<input type="button" value="탈퇴">

</body>
</html>