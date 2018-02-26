$(function() {

	var idCheck = 2;

	/*
	 * idCheck 아이디 체크 여부 1이면 체크함. 2이면 체크 안함. resultData 1이면 사용 가능 아이디. 2이면 사용 불가
	 * 아이디.
	 */

	/* 아이디 중복확인textfield에서 focusout시 이벤트 */
	$("#parentId").focusout(
			function() {

				if (!formCheck($('#parentId'), $('.check:eq(0)'), "아이디를")) {
					return;
				} else if (!regularExpression(1, '#parentId', '.check:eq(0)',
						"아이디를 ")) {
					return;
				} else {
					$.ajax({
						url : "/member/parentIdConfirm.do",
						type : "post",
						data : "parentId=" + $("#parentId").val(),
						error : function() {
							alert('접속오류.');
						},
						success : function(resultData) {
							console.log("resultData : " + resultData);
							if (resultData == 2) {
								$(".check:eq(0)").css("color", "#FF0000").html(
										"현재 사용 중이거나 탈퇴된 아이디 입니다.");
							} else if (resultData == 1) {
								$(".check:eq(0)").css("color", "#47C83E").html(
										"사용 가능한 아이디 입니다.");
							}
						}
					});
				}

			});

	/* 인증번호 받기button 클릭시 이벤트 */
	$("#CertifiNumBtn").click(function() {

		$.ajax({
			url : "/member/sendMail.do",
			type : "post",
			data : "parentId=" + $("#parentId").val(),
			error : function() {
				alert('그냥오류');
			},
			success : function(ranNum) {
				alert("입력하신 이메일주소로 인증번호가 발송되었습니다.");
				
				console.log(ranNum);

				$("#CertifiBtn").prop("disabled", false);

			}
		});

	});

	/* 인증번호 인증button 클릭시 이벤트 */
	var confirmNumberCheck = 2;

	$("#CertifiBtn").click(function() {

		$.ajax({
			url : "/member/confirmCheck.do",
			type : "post",
			data : "parentId=" + $("#parentId").val() & "certification=" + $("#certification").val(),
			error : function() {
				alert("인증오류");
			},
			success : function(check) {

				if (check == 1) {
					alert("인증이 완료되었습니다.");

					$(".check:eq(1)").css("color", "#47C83E").html("인증 성공!");

					$("#parentId").prop("disabled", true);
					$("#CertifiNumBtn").prop("disabled", true);
					$("#certification").prop("disabled", true);

					
					
					idCheck = 1; // id 체크 완료
					confirmNumberCheck = 1; // 인증번호 체크 완료
				} else if (check == 2) {
					alert("인증번호를 받은 email 또는 인증번호가 틀렸습니다. 다시한번 확인해 주세요.");
					$(".check:eq(1)").css("color", "#FF0000").html("인증 실패!");
				}
			}
		});

	});

	/* 비밀번호textfield에 blur시 이벤트 */
	$("#parentPw").focusout(
			function() {
				if (!formCheck($('#parentPw'), $('.check:eq(2)'), "비밀번호를")) {
					return;
				} else if (!regularExpression(2, '#parentPw', '.check:eq(2)',
						"비밀번호를 ")) {
					return;
				} else {
					$(".check:eq(2)").css("color", "#47C83E").html(
							"사용가능한 비밀번호 입니다.");
				}
			});

	var pwCheck = 2;

	/* 비밀번호 Check */
	$("#parentPwCheck").focusout(function() {
		if ($("#parentPw").val() === $("#parentPwCheck").val()) {
			$(".check:eq(3)").css("color", "#47C83E").html("비밀번호 확인 완료!");
			pwCheck = 1;
		} else {
			alert("비밀번호 확인이 공란이거나 비밀번호와 일치하지 않습니다.");
			$(".check:eq(3)").css("color", "#FF0000").html("입력한 비밀번호와 다릅니다.");
			$("#parentPwCheck").val("");
			pwCheck = 2;
		}
	});

	/* 비밀번호확인textfield에서 focusin시 이벤트 */
	/*
	 * $("#parentPwCheck").focusin(function() { $(".check:eq(3)").html(""); });
	 */

	/* 이름 형식 확인 */
	$("#parentName").focusout(
			function() {
				if (!formCheck($('#parentName'), $('.check:eq(4)'), "이름을")) {
					return;
				} else if (!regularExpression(0, '#parentName', '.check:eq(4)',
						"이름을 ")) {
					return;
				} else {
					$(".check:eq(4)").html("");
				}
			});

	/* 연락처 확인 */
	$("#parentPhoneNum")
			.focusout(
					function() {
						if (!formCheck($('#parentPhoneNum'), $('.check:eq(5)'),
								"연락처를 ")) {
							return;
						} else if (!regularExpression(3, '#parentPhoneNum',
								'.check:eq(5)', "연락처를 ")) {
							return;
						} else {
							$(".check:eq(5)").html("");
						}
					});

	/* 주소 확인 */
	$("#address").focusout(function() {
		if (!formCheck($('#address'), $('.check:eq(6)'), "주소를 ")) {
			return;
		} else if (!regularExpression(4, '#address', '.check:eq(6)', "주소를 ")) {
			return;
		} else {
			$(".check:eq(6)").html("");
		}
	})

	/* 회원가입 버튼 클릭시 이벤트 */
	$("#okBtn")
			.click(
					function() {

						if (idCheck != 1 && confirmNumberCheck != 1) {
							alert("아이디 이메일 인증을 해주세요.");
							return;
						} else if (!formCheck($('#parentPw'),
								$('.check:eq(2)'), "비밀번호를")) {
							return;
						} else if (!regularExpression(2, '#parentPw',
								'.check:eq(2)', "비밀번호를 ")) {
							return;
						} else if (pwCheck != 1) {
							alert("비밀번호 확인을 진행하지 않았거나 비밀번호와 일치하지 않습니다.");
							return;
						} else if (!formCheck($('#parentName'),
								$('.check:eq(4)'), "이름을")) {
							return;
						} else if (!regularExpression(0, '#parentName',
								'.check:eq(4)', "이름을 ")) {
							return;
						} else if (!formCheck($('#parentPhoneNum'),
								$('.check:eq(5)'), "연락처를 ")) {
							return;
						} else if (!regularExpression(3, '#parentPhoneNum',
								'.check:eq(5)', "연락처를 ")) {
							return;
						} else if (!formCheck($('#address'), $('.check:eq(6)'),
								"주소를 ")) {
							return;
						} else if (!regularExpression(4, '#address',
								'.check:eq(6)', "주소를 ")) {
							return;
						}

					});

	/* 회원가입 취소 버튼 클릭시 이벤트 */
	$("#cancelBtn").click(function() {
		console.log("회원가입 취소!");

		location.href = "/login/login.do";
	});

});