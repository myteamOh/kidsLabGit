$(function() {

	/* 비밀번호textfield에 focusout시 이벤트 */
	$("#newPassword").focusout(
			function() {
				if (!formCheck($('#newPassword'), $('.check:eq(0)'), "비밀번호를")) {
					return;
				} else if (!regularExpression(2, '#newPassword',
						'.check:eq(0)', "비밀번호를 ")) {
					return;
				} else {
					$(".check:eq(0)").css("color", "#47C83E").html(
							"사용가능한 비밀번호 입니다.");
				}
			});

	var pwCheck = 2;

	/* 새 비밀번호 체크 키업 이벤트 */
	$("#newPasswordCheck").keyup(function() {
		if ($("#newPassword").val() === $("#newPasswordCheck").val()) {
			$(".check:eq(1)").css("color", "#47C83E").html("비밀번호 확인 완료!");
			pwCheck = 1;
		} else {
			$(".check:eq(1)").css("color", "#FF0000").html("입력한 비밀번호와 다릅니다.");
			pwCheck = 2;
		}
	});

	/* 변경버튼 클릭시 이벤트 */
	$("#okBtn").click(
			function() {

				if (!formCheck($('#newPassword'), $('.check:eq(0)'), "비밀번호를")) {
					return;
				} else if (!regularExpression(2, '#newPassword',
						'.check:eq(0)', "비밀번호를 ")) {
					return;
				} else if (pwCheck != 1) {
					alert("비밀번호와 비밀번호 재입력값이 다릅니다.");
					return;
				}

				$.ajax({
					url : "/member/newstudentpw.do",
					type : "POST",
					data : "userPw=" + $("#newPassword").val(),
					error : function() {
						alert("오류");
					},
					success : function(result) {

						if (result == 1) {
							alert("새비밀번호 설정 성공!");
							location.href = "/login/login.do";
							return;
						} else if (result == 2) {
							alert("실패!");
							return;
						}

					}
				});

			});

	/* 취소버튼 클릭시 이벤트 */
	$("#cancelBtn").click(function() {
		alert("새비밀번호 설정 취소!");

		location.href = "/member/finduser.do";
	});

});