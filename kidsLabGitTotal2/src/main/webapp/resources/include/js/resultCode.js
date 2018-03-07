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