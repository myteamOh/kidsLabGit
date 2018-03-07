/*
 * 함수명: chkSubmit(유효성 체크 대상, 메시지 내용) * 출력영역: alert으로. * 예시 :
 * if(!chkSubmit($('#keyword'),"검색어를")) return; *
 */

function chkSubmit(item, msg) {
	if (item.val().replace(/\s/g, "") == "") {
		alert(msg + " 입력해 주세요.");
		item.val("");
		item.focus();
		return false;
	} else {
		return true;
	}
}

/*
 * 함수명: checkForm(유효성 체크 대상, 메시지 내용) * 출력영역: placeholder 속성을 이용. * 예시 :
 * if(!chkSubmit($('#keyword'),"검색어를")) return; *
 */

function checkForm(item, msg) {
	var message = "";
	if (item.val().replace(/\s/g, "") == "") {
		message = msg + " 입력해 주세요.";
		item.attr("placeholder", message);
		return false;
	} else {
		return true;
	}
}

/*
 * 함수명: formCheck(유효성 체크 대상, 출력 영역, 메시지 내용) * 출력영역: 매개변수 두번째 출력영역에. * 예시 :
 * if(!formCheck($('#keyword'),$('#msg'),"검색어를")) return;
 */

function formCheck(main, item, msg) {
	if (main.val().replace(/\s/g, "") == "") {
		item.css("color", "#000099").html(msg + " 입력해 주세요");
		main.val("");
		return false;
	} else {
		return true;
	}
}

/*
 * 함수명: chkData(유효성 체크 대상, 메시지 내용) * 예시 : if (!chkData("#keyword","검색어를"))
 * return;
 */

function chkData(item, msg) {
	if ($(item).val().replace(/\s/g, "") == "") {
		alert(msg + " 입력해 주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	} else {
		return true;
	}
}

function chkFile(item) {
	/*
	 * 배열내의 값을 찾아서 인덱스를 반환(요소가 없을 경우 -1 반환) jQuery.inArray(찾을 값, 검색 대상의 배열)
	 */
	var ext = item.val().split('.').pop().toLowerCase();
	if (jQuery.inArray(ext, [ 'gif', 'png', 'jpg', 'jpeg' ]) == -1) {
		alert('gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.');
		return false;
	} else {
		return true;
	}
}

function chkPlanFile(item) {
	/*
	 * 배열내의 값을 찾아서 인덱스를 반환(요소가 없을 경우 -1 반환) jQuery.inArray(찾을 값, 검색 대상의 배열)
	 */
	var ext = item.val().split('.').pop().toLowerCase();
	if (jQuery.inArray(ext, [ 'txt', 'xlsx', 'pptx', 'hwp' ]) == -1) {
		alert('txt, xlsx, pptx, hwp 파일만 업로드 할 수 있습니다.');
		return false;
	} else {
		return true;
	}
}

/*
 * 배열: 유효성 체크 시 필요한 정규식으로 배열을 초기화. pattern = [아이디 , 비밀번호, 핸드폰번호] 함수명 :
 * inputVerify(배열 인덱스번호, 비교할 값, 출력영역)
 */
var pattern = [ "((?=.*[a-zA-z])(?=.*[0-9]).{6,10})",
	"((?=.*[a-zA-Z])(?=.*[0-9@#$%]).{8,12})", "^01([0|1|6|7|8|9]?([0-9]{3,4})?([0-9]{4}))" ];

function inputVerify(index, data, printarea) {
	var data_regExp = new RegExp(pattern[index]);
	var match = data_regExp.exec($(data).val());
	if (match == null) {
		$(printarea).html("입력값이 형식에 맞지 않습니다. 다시 입력해 주세요. ");
		$(data).val("");
		return false;
	} else {
		return true;
	}
}
/***********************************사용자(문현)common.js **************************************/
/*
 * 함수명 : getDateFormat(날짜 데이터) 설명 : dataValue의 값을 년-월-일 형식(예시:2018-01-01)으로 반환.
 */
function getDateFormat(dateValue) {
	var year = dateValue.getFullYear();

	var month = (month < 10) ? "0" + month : month;

	var day = dateValue.getDate();
	day = (day < 10) ? "0" + day : day;

	var result = year + "-" + month + "-" + day;

	return result;

}
/***************************** 정규식 문현이 껄로 **************************/
/*var regular = [
	/^[가-힣]{2,5}$/,
	/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/,
	/^[0-9a-zA-Z]\S{7,12}$/, /^[0-9]{11}$/,
	/^[0-9a-zA-Z가-힣]([@]*[0-9a-zA-Z가-힣\s]){5,50}$/ ];

function regularExpression(index, data, printarea, msg) {
	var select_regular = new RegExp(regular[index]); // 해당 index번호 정규식
	var match = select_regular.exec($(data).val()); // 정규식과 맞는지 체크

	if (match == null) {
		$(printarea).css("color", "#FF0000").html(msg + "형식에 맞게 입력해 주세요.");
		return false;
	} else {
		return true;
	}

}*/

/**********************************추가 ************************/
//함수명 : spaceDelete(this 해당하는 area)
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