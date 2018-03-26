$(function() {

	var pwCheck = 2;

	/* 비밀번호textfield에 focusout시 이벤트 */
	$("#userPw").focusout(
			function() {
				if ($("#userPw").val() != "") {
					if (!regularExpression(2, '#userPw', '.check:eq(0)',
							"비밀번호를 ")) {
						return;
					} else {
						$(".check:eq(0)").css("color", "#47C83E").html(
								"사용가능한 비밀번호 입니다.");
					}
				} else if ($("#userPw").val() == "") {
					$(".check:eq(0)").html("");
				}
			});

	/* 비밀번호 Check */
	$("#userPwCheck").focusout(
			function() {
				if ($("#userPw").val() != "") {

					if ($("#userPw").val() === $("#userPwCheck").val()) {
						$(".check:eq(1)").css("color", "#47C83E").html(
								"비밀번호 확인 완료!");
						pwCheck = 1;
					} else {
						$(".check:eq(1)").css("color", "#FF0000").html(
								"입력한 비밀번호와 다릅니다.");
						pwCheck = 2;
					}
				}
				if ($("#userPwCheck").val() == "") {
					$(".check:eq(1)").html("");
				}
			});

	/* 학교 확인 */
	$("#student_school")
			.focusout(
					function() {
						if (!formCheck($('#student_school'), $('.check:eq(2)'),
								"학교명을")) {
							return;
						} else if (!regularExpression(6, '#student_school',
								'.check:eq(2)', "학교명을 ")) {
							return;
						} else {
							$(".check:eq(2)").html("");
						}
					});

	// 학생 수정 완료 버튼 클릭시 이벤트
	$("#studentModifyBtn").click(
			function() {

				if ($("#userPw").val() != "") {
					if (!regularExpression(2, '#userPw', '.check:eq(0)',
							"비밀번호를 ")) {
						return;
					} else {
						$(".check:eq(0)").css("color", "#47C83E").html(
								"사용가능한 비밀번호 입니다.");
					}
					if (pwCheck != 1) {
						alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
						return;
					}
				} else if ($("#userPw").val() == "") {
					$(".check:eq(0)").html("");
					if ($("#userPwCheck").val() != "") {
						alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
						return;
					}
					$(".check:eq(1)").html("");
				} else if (!formCheck($('#student_school'), $('.check:eq(2)'),
						"학교명을")) {
					return;
				} else if (!regularExpression(6, '#student_school',
						'.check:eq(2)', "학교명을 ")) {
					return;
				}

				$("#studentModifying").attr({
					"method" : "POST",
					"action" : "/mypage/studentModifyInfo"
				});

				$("#studentModifying").submit();

				alert("학생 정보 수정 시도!");

			});

});