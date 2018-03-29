
// 비밀번호와 비밀번호 확인 일치 여부 확인
function passwordCheck() {
	if ($("#teacher_password").val() != $("#teacher_passwordCheck").val()) {
		document.getElementById("teacher_passwordCheck").innerHTML = "비밀번호가 일치하지 않습니다.";
		$("#teacher_passwordCheck").focus();
		$("#teacher_passwordCheck").val("");
		return false;
	} else {
		return true;
	}
}
// 비밀번호에 아이디 포함여부 확인
function idPwdCheck() {
	var teacher_id = $("#teacher_id").val();
	var teacher_password = $("#teacher_password").val();
	if (teacher_password.indexOf(teacher_id) > -1) {
		alert("비밀번호에 아이디를 포함 할 수 없습니다.");
		$("#teacher_password").val();
		$("#teacher_password").focus();
		return false;
	} else {
		return true;
	}
}


var idConfirm = 1;

$(function() {
	errCodeCheck();
	// 사용자에게 요구사항에 대한 문자열로 배열 초기화
	var message = [ "이메일 형식으로 작성해 주세요.",
		"영문,숫자,특수문자만 가능. 8 ~ 15자로 입력해주세요.",
		"비밀번호와 비밀번호 확인란은 값이 일치해야 합니다.",
		"한글로만 입력이 가능. 2 ~ 5자로 입력해주세요.",
		"-없이 입력해주세요. 예시)01012341234",
		"사진파일은 5Mb 이하로 등록해 주세요." ];
	$('.error').each(function(index) {
		$('.error').eq(index).html(message[index]);
	});

	$('#teacher_id, #teacher_password, #teacher_passwordCheck, #teacher_name, #teacher_phone').bind("focus", function() {
		var idx = $('#teacher_id, #teacher_password, #teacher_passwordCheck, #teacher_name, #teacher_phone').index(this);
		console.log("대상 : " + idx);
		$(this).parents(".form-group").find(".error").html(message[idx]);
		$(this).parents(".form-group").find(".error").css("color", "#000000");
	});


	$("#idConfirmBtn").click(function() {
		if (!formCheck($('#teacher_id'), $('.error:eq(0)'), "아이디를")) {
			return;
		} else if (!inputVerify(0, '#teacher_id', '.error:eq(0)')) {
			return;
		} else {
			console.log("teacher_id : " + $("#teacher_id").val());
			$.ajax({
				url : "/admin/teacher/userIdConfirm.do",
				type : "post",
				data : "teacher_id=" + $("#teacher_id").val(),
				error : function() {
					alert("사이트 접속에 문제로 정상 작동하지 못하였습니다. 잠시 후 다시 시도해 주세요");
				},
				success : function(resultData) {
					console.log("resultData : " + resultData);
					if (resultData == "1") {
						$("#teacher_id").parents(".form-group").find(".error").html("현재 사용 중인 아이디입니다.");
					} else if (resultData == "2") {
						$("#teacher_id").parents(".form-group").find(".error").html("사용 가능한 아이디 입니다.");
						idConfirm = 2;
					}
				}
			});
		}
	});

	// Id 포커스 이동시 처리 이벤트
	$("#teacher_id").bind("blur", function() {
		if (!formCheck($('#teacher_id'), $('.error:eq(0)'), "아이디를")) {
			return;
		} else if (!inputVerify(0, '#teacher_id', '.error:eq(0)')) {
			return;
		}
	});
	// password 포커스 이동시 처리 이벤트
	$("#teacher_password").bind("blur", function() {
		if (!formCheck($('#teacher_password'), $('.error:eq(1)'), "비밀번호를")) {
			return;
		} else if (!inputVerify(1, '#teacher_password', '.error:eq(1)')) {
			return;
		} else if (!idPwdCheck()) {
			return;
		}
	});
	// passwordCheck 포커스 이동시 처리 이벤트
	$("#teacher_passwordCheck").bind("blur", function() {
		if (!formCheck($('#teacher_passwordCheck'), $('.error:eq(2)'), "비밀번호 확인을")) {
			return;
		} else if (!inputVerify(1, '#teacher_passwordCheck', '.error:eq(2)')) {
			return;
		} else if (!passwordCheck()) {
			return;
		}
	});
	// 전화번호 포커스 이동시 처리 이벤트
	$("#teacher_phone").bind("blur", function() {
		if (!formCheck($('#teacher_phone'), $('.error:eq(4)'), "전화번호를")) {
			return;
		} else if (!inputVerify(2, '#teacher_phone', '.error:eq(4)')) {
			return;
		}
	});
	// 이름 포커스 이동시 처리 이벤트
	$("#teacher_name").bind("blur", function() {
		if (!formCheck($('#teacher_name'), $('.error:eq(3)'), "이름을")) {
			return;
		}
	});
	/** 확인 버튼 클릭 시 처리 이벤트**/
	$("#joinInsert").click(function() {
		if (idConfirm != 2) {
			alert("아이디 중복 체크를 진행해 주세요.");
			return;
		} else if (!formCheck($('#teacher_id'), $('.error:eq(0)'), "아이디를")) {
			return;
		} else if (!inputVerify(0, '#teacher_id', '.error:eq(0)')) {
			return;
		} else if (!formCheck($('#teacher_password'), $('.error:eq(1)'), "비밀번호를")) {
			return;
		} else if (!idPwdCheck()) {
			return;
		} else if (!formCheck($('#teacher_passwordCheck'), $('error:eq(2)'), "비밀번호 확인을")) {
			return;
		} else if (!inputVerify(1, '#teacher_passwordCheck', '.error:eq(2)')) {
			return;
		} else if (!passwordCheck()) {
			return;
		} else if (!formCheck($('#teacher_phone'), $('.error:eq(4)'), "전화번호를")) {
			return;
		} else if (!inputVerify(2, '#teacher_phone', '.error:eq(4)')) {
			return;
		} else if (!formCheck($('#teacher_name'), $('.error:eq(3)'), "이름을")) {
			return;
		} else {
			if ($('#file').val() != "") {
				if (!chkFile($('#file'))) {
					return;
				}
			} else {
				alert("사진을 등록해주세요.");
				return;
			}
			$("#teacherForm").attr({
				"method" : "POST",
				"action" : "/admin/teacher/join.do"
			});
			$("#teacherForm").submit();
			/*$("#teacherForm").ajaxFrom({
				url : "/teacher/join.do",
				type : "post",
				enctype : "multipart/form-data",
				dataType : "text",
				error : function() {
					alert('시스템 오류 입니다. 관리자에게 문의 하세요.');
				},
				success : function(data) {
					console.log(data);
					alert(data);
					if (data == "성공") {
						alert("성공");
					} else {
						alert("등록에 문제");
					}
				}
			});
			$("#teacherForm").submit();*/

		}
	});
	$("#joinCancel").click(function() {

		location.href = "/admin/member/teacherList";
	});

	$("#joinReset").click(function() {
		$("#teacherForm").each(function() {
			this.reset();
		})
	});
});