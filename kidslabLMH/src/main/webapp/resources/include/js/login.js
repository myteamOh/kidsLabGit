$(function() {

	/* 로그인 버튼 클릭시 이벤트 */
	$("#loginBtn").click(
			function() {
				console.log("로그인 시도!");

				if ($("#userId").val() == null || $("#userId").val() == ""
						|| $("#userPw").val() == null
						|| $("#userPw").val() == "") {
					$("#loginAlert")
							.html("ID 또는 PW가 입력되지 않았습니다. 다시한번 확인해 주세요.");
					return;
				}

				$("#loginAlert").html("");

				$("#loginForm").attr({
					"method" : "POST",
					"action" : "/login/login.do"
				});

				$("#loginForm").submit();

				alert("로그인 시도!");

			});

	/* 회원가입 버튼 클릭시 이벤트 */
	$("#parentJoinBtn").click(function() {
		console.log("회원가입폼 출력!");

		location.href = "/member/agree.do";
	});

	/* 아이디 / 비밀번호 찾기 버튼 클릭시 이벤트 */
	$("#findBtn").click(function() {
		console.log("서치폼 출력!");

		location.href = "/member/finduser.do";
	});

	/* 강사로그인 버튼 클릭시 이벤트 */
	$("#teacherLoginBtn").click(function() {
		console.log("강사로그인 출력!");

		location.href = "";
	});

});