<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자녀추가</title>
</head>
<body>

<label>* 는 필수입력 사항입니다.</label>
	
	<label>*</label>
	<label>아이디</label>
	<input type="email">
	<input type="button" value="아이디 중복확인">
	<label>중복확인라벨</label>
	
	<label>*</label>
	<label>비밀번호 입력</label>
	<input type="password">
	<label>사용가능여부라벨</label>
	<label>*비밀번호는 문자+숫자+특수문자를 모두 사용하여 8 ~ 13자리로 만들어 주세요.</label>

	<label>*</label>
	<label>비밀번호 확인</label>
	<input type="password">
	<label>비밀번호체크라벨</label>
	
	<label>*</label>
	<label>학생 이름</label>
	<input type="text">
	
	<label>*</label>
	<label>성 별</label>
	<input type="radio" value="남">
	<input type="radio" value="여">
	
	<label>*</label>
	<label>학 교</label>
	<input type="text">
	
	<label>*</label>
	<label>생년월일</label>
	<input type="date">
	
	<label>참고사항</label>
	<textarea rows="" cols="">참고사항</textarea>
	
	<input type="button" value="자녀추가">
	<input type="button" value="취소">

</body>
</html>