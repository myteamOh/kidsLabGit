// 함수명 : spaceDelete(this 해당하는 area)
function spaceDelete(obj) { // 공백사용못하게
	var str_space = /\s/; // 공백체크
	if (str_space.exec(obj.value)) { // 공백 체크
		alert("해당 항목에는 공백을 사용할수 없습니다.");
		obj.focus();
		obj.value = obj.value.replace(' ', ''); // 공백제거
		return false;
	}
	// onkeyup="spaceDelete(this);" onchange="spaceDelete(this);"
}

/*
 * 함수명: formCheck(유효성 체크 대상, 출력 영역, 메시지 내용) * 출력영역: 매개변수 출력영역에. * 메시지 내용: 해당하는
 * 메시지 내용을 출력 예시 :if(!formCheck($('#keyword'),$('#msg'),"검색어를")) return;
 */

function formCheck(main, item, msg) {
	if (main.val().replace(/\s/g, "") == "") {
		item.css("color", "#FF0000").html(msg + " 입력해 주세요");
		main.val("");
		return false;
	} else {
		return true;
	}
}

/*
 * 변수 : 필요한 정규식 배열. [이름, 아이디(E-mail), 비밀번호, 연락처, 주소] 함수명 : regularExpression(체크할
 * 정규식 인덱스 번호, 입력받아서 비교할 값, 출력영역)
 */

/* 내 정규식 */
/*
 * var nameCheck = /^[가-힣]{4,10}$/; var idCheck =
 * /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
 * var CertifiNumCheck = /^[0-9]{6}$/; // var pwCheck = //
 * /^[0-9a-zA-Z][`~!@#$%^&*\(\)\-_\=+|\\\[\]\{\};:\'\",<\.>\/?0-9a-zA-Z]{7,12}$/;
 * var pwCheck = /^[0-9a-zA-Z]\S{7,12}$/; var phoneNumCheck = /^[0-9]{11}$/; var
 * addressCheck = /^[0-9a-zA-Z가-힣]([@]*[0-9a-zA-Z가-힣\s]){5,50}$/;
 */

/* exec 메서드가 일치하는 부분을 찾지 못하면 null을 반환합니다. exec 메서드가 일치하는 부분을 찾으면 배열을 반환 */

/* index (0:한글이름, 1:email(학부모 아이디), 2:비밀번호, 3:연락처, 4:주소, 5:학생 아이디, 6:학교이름 */
var regular = [
		/^[가-힣]{2,5}$/,
		/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/,
		/^[0-9a-zA-Z]\S{7,12}$/, /^\d{11}$/,
		/^[0-9a-zA-Z가-힣]([@]*[0-9a-zA-Z가-힣\s]){5,50}$/, /^[0-9a-zA-Z]{6,15}$/,
		/^[가-힣]{5,20}$/ ];

function regularExpression(index, data, printarea, msg) {

	var select_regular = new RegExp(regular[index]); // 해당 index번호 정규식
	var match = select_regular.exec($(data).val()); // 정규식과 맞는지 체크

	if (match == null) {
		$(printarea).css("color", "#FF0000").html(msg + "형식에 맞게 입력해 주세요.");
		return false;
	} else {
		return true;
	}

}