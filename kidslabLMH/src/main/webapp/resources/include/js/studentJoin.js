$(function() {

	var idCheck = 2;

	/* 아이디 형식 확인 및 중복확인 */
	$("#userId").focusout(
			function() {
				if (!formCheck($('#userId'), $('.check:eq(0)'), "아이디를")) {
					return;
				} else if (!regularExpression(5, '#userId', '.check:eq(0)',
						"아이디를 ")) {
					return;
				} else {
					$.ajax({
						url : "/member/studentIdConfirm.do",
						type : "post",
						data : "userId=" + $("#userId").val(),
						error : function() {
							alert('접속오류.');
						},
						success : function(result) {
							console.log("result : " + result);
							if (result == 2) {
								$(".check:eq(0)").css("color", "#FF0000").html(
										"현재 사용 중이거나 탈퇴된 아이디 입니다.");
								idCheck = 2;
							} else if (result == 1) {
								$(".check:eq(0)").css("color", "#47C83E").html(
										"사용 가능한 아이디 입니다.");
								idCheck = 1;
							}
						}
					});
				}
			});

	/* 비밀번호textfield에 focusout시 이벤트 */
	$("#userPw").focusout(function() {
		if (!formCheck($('#userPw'), $('.check:eq(1)'), "비밀번호를")) {
			return;
		} else if (!regularExpression(2, '#userPw', '.check:eq(1)', "비밀번호를 ")) {
			return;
		} else {
			$(".check:eq(1)").css("color", "#47C83E").html("사용가능한 비밀번호 입니다.");
		}
	});

	var pwCheck = 2;

	/* 비밀번호 Check */
	$("#pwCheck").focusout(function() {
		if ($("#userPw").val() === $("#pwCheck").val()) {
			$(".check:eq(2)").css("color", "#47C83E").html("비밀번호 확인 완료!");
			pwCheck = 1;
		} else {
			$(".check:eq(2)").css("color", "#FF0000").html("입력한 비밀번호와 다릅니다.");
			pwCheck = 2;
		}
	});

	/* 이름 형식 확인 */
	$("#student_name").focusout(
			function() {
				if (!formCheck($('#student_name'), $('.check:eq(3)'), "이름을")) {
					return;
				} else if (!regularExpression(0, '#student_name',
						'.check:eq(3)', "이름을 ")) {
					return;
				} else {
					$(".check:eq(3)").html("");
				}
			});

	/* 학교명 형식 확인 */
	$("#student_school")
			.focusout(
					function() {
						if (!formCheck($('#student_school'), $('.check:eq(4)'),
								"학교명을")) {
							return;
						} else if (!regularExpression(6, '#student_school',
								'.check:eq(4)', "학교명을 ")) {
							return;
						} else {
							$(".check:eq(4)").html("");
						}
					});

	/* 회원가입 버튼 클릭시 이벤트 */
	$("#okBtn")
			.click(
					function() {

						if (idCheck != 1) {
							alert("아이디 중복확인을 진행해 주세요.");
							return;
						} else if (!formCheck($('#userPw'), $('.check:eq(1)'),
								"비밀번호를")) {
							return;
						} else if (!regularExpression(2, '#userPw',
								'.check:eq(1)', "비밀번호를 ")) {
							return;
						} else if (pwCheck != 1) {
							alert("비밀번호 확인과 비밀번호가 다릅니다.");
							return;
						} else if (!formCheck($('#student_name'),
								$('.check:eq(3)'), "이름을")) {
							return;
						} else if (!regularExpression(0, '#student_name',
								'.check:eq(3)', "이름을 ")) {
							return;
						} else if (!formCheck($('#student_school'),
								$('.check:eq(4)'), "학교명을")) {
							return;
						} else if (!regularExpression(6, '#student_school',
								'.check:eq(4)', "학교명을 ")) {
							return;
						}

						$("#studentForm").attr({
							"method" : "post",
							"action" : "/member/studentjoin.do"
						});

						$("#studentForm").submit();

						alert("자녀등록 시도!");

					});

	/* 자녀등록 취소 버튼 클릭시 이벤트 */
	$("#cancelBtn").click(function() {
		console.log("자녀등록 취소!");

		location.href = "/mypage/parentMypage.do";
	});

});