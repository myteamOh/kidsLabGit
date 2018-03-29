$(function() {

	var pwCheck = 2;

	/* 비밀번호textfield에 focusout시 이벤트 */
	$("#teacher_password").focusout(
		function() {
			if ($("#teacher_password").val() != "") {
				if (!regularExpression(2, '#teacher_password', '.check:eq(0)',
						"비밀번호를 ")) {
					return;
				} else {
					$(".check:eq(0)").css("color", "#47C83E").html(
						"사용가능한 비밀번호 입니다.");
				}
			} else if ($("#teacher_password").val() == "") {
				$(".check:eq(0)").html("");
			}
		});

	/* 비밀번호 Check */
	$("#userPwCheck").focusout(
		function() {
			if ($("#teacher_password").val() != "") {
				if ($("#teacher_password").val() === $("#userPwCheck").val()) {
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

	/* 연락처 확인 */
	$("#teacher_phone")
		.focusout(
			function() {
				if (!formCheck($('#teacher_phone'), $('.check:eq(2)'),
						"연락처를 ")) {
					return;
				} else if (!regularExpression(3, '#teacher_phone',
						'.check:eq(2)', "연락처를 ")) {
					return;
				} else {
					$(".check:eq(2)").html("");
				}
			});

	// 강사 수정 완료 버튼 클릭시 이벤트
	$("#teacherModifyBtn").click(
		function() {

			if ($("#teacher_password").val() != "") {
				if (!regularExpression(2, '#teacher_password', '.check:eq(0)',
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
			} else if ($("#teacher_password").val() == "") {
				$(".check:eq(0)").html("");
				if ($("#userPwCheck").val() != "") {
					alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
					return;
				}
				$(".check:eq(1)").html("");
			} else if (!formCheck($('#teacher_phone'), $('.check:eq(2)'),
					"연락처를 ")) {
				return;
			} else if (!regularExpression(3, '#teacher_phone',
					'.check:eq(2)', "연락처를 ")) {
				return;
			}
			if ($('#file').val() != "") {
				if (!chkFile($('#file'))) {
					return;
				}
			}
			alert("실행");
			$("#teacherModifyForm").attr({
				"method" : "POST",
				"action" : "/teacher/modifySuccess"
			});
			$("#teacherModifyForm").submit();

			alert("teacher 정보수정!");

			/*$.ajax({
				url : "/teacher/modifySuccess",
				type : "post",
				data : $("#teacherModify").serialize(),
				error : function(e) {
					console.log("에러");
					console.log(e);
					alert("오류");
				},
				success : function() {
					alert("실행");
					alert("파일명 : " + $("#file").val());
					$("#teacherModify").attr({
						"method" : "post",
						"action" : "/teacher/modifySuccess"
					});

					$("#teacherModify").submit();
				}
			});*/



		});

});