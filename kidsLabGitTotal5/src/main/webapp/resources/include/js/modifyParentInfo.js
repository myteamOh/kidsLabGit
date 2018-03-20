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

	/* 연락처 확인 */
	$("#parentPhone").focusout(
			function() {
				if (!formCheck($('#parentPhone'), $('.check:eq(2)'), "연락처를 ")) {
					return;
				} else if (!regularExpression(3, '#parentPhone',
						'.check:eq(2)', "연락처를 ")) {
					return;
				} else {
					$(".check:eq(2)").html("");
				}
			});

	/* 주소 확인 */
	$("#parentAddress")
			.focusout(
					function() {
						if (!formCheck($('#parentAddress'), $('.check:eq(3)'),
								"주소를 ")) {
							return;
						} else if (!regularExpression(4, '#parentAddress',
								'.check:eq(3)', "주소를 ")) {
							return;
						} else {
							$(".check:eq(3)").html("");
						}
					});

	// 학부모 수정 완료 버튼 클릭시 이벤트
	$("#parentModifyBtn").click(
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
				} else if (!formCheck($('#parentPhone'), $('.check:eq(2)'),
						"연락처를 ")) {
					return;
				} else if (!regularExpression(3, '#parentPhone',
						'.check:eq(2)', "연락처를 ")) {
					return;
				} else if (!formCheck($('#parentAddress'), $('.check:eq(3)'),
						"주소를 ")) {
					return;
				} else if (!regularExpression(4, '#parentAddress',
						'.check:eq(3)', "주소를 ")) {
					return;
				}

				$("#parentModifying").attr({
					"method" : "POST",
					"action" : "/mypage/parentModifyInfo.do"
				});

				$("#parentModifying").submit();

				alert("학부모 정보 수정 시도!");

			});

	/* 취소버튼 클릭시 이벤트 */
	$("#modifyCancelBtn").click(function() {
		location.href = "/mypage/parentMypage.do";
	});

	/* 탈퇴버튼 클릭시 이벤트 */
	$("#withdrawBtn")
			.click(
					function() {

						$
								.ajax({
									url : "/mypage/parentWithdrawCheck",
									type : "POST",
									data : "parent_no=" + $("#parentNum").val(),
									error : function() {
										alert("오류");
										return;
									},
									success : function(result) {

										if (result == 1) { // 탈퇴가능계정

											var confirmCheck = confirm("탈퇴가 가능합니다.\n\n주의!!\n탈퇴시 해당아이디는 재사용이 불가능 합니다.\n탈퇴시 해당학생들의 계정도 탈퇴가 퇴며 사용이 불가능 합니다.\n\n정말로 탈퇴 하시겠습니까?");

											if (confirmCheck) {
												$("#parentModifying")
														.attr(
																{
																	"method" : "POST",
																	"action" : "/mypage/parentWithdraw"
																});
												$("#parentModifying").submit();

												alert("탈퇴처리.");
											} else {
												alert("취소되었습니다.");
												return;
											}

										} else if (result == 2) { // 탈퇴불가능계정

											alert("강의를 수강중인 학생이 있거나 환불이 완료되지 않은 강의가 있어서 탈퇴가 불가능합니다.");
											return;

										} else {
											alert("이도저도아니네");
											return;
										}

									}
								});

					});

});