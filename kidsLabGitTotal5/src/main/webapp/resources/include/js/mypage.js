$(function() {

	/* 회원 정보 수정 button click event */
	$(".modifyInfo").click(function() {

		alert("정보 수정하기 전 비번확인");

		$("#pwCheckForm").attr({
			"method" : "POST",
			"action" : "/mypage/modifyCheckPw.do"
		});

		$("#pwCheckForm").submit();

	});

	/* 자녀 추가 button click event */
	$("#studentJoin").click(function() {

		alert("자녀추가하기");

		location.href = "/member/studentjoin.do";

	});

	// 비번 확인 버튼 클릭시 이벤트
	$(".okBtnPwCheck").click(
		function() {

			if ($(".userId").val() == null || $(".userId").val() == ""
				|| $(".userPw").val() == null
				|| $(".userPw").val() == "") {
				$("#loginAlert").css("color", "#FF0000").html(
					"PW가 입력되지 않았습니다. 다시한번 확인해 주세요.");
				return;
			}

			$("#loginAlert").html("");

			$("#modifyForm").attr({
				"method" : "post",
				"action" : "/mypage/modifyinfo.do"
			});

			$("#modifyForm").submit();

			alert("수정page 진입 시도!");
		});

	// 비번 확인 취소 버튼 클릭시 이벤트
	$(".cancelBtn").click(function() {

		history.back();

	});

	/**************************
	 * 강사 회원 수정 버튼 클릭 시
	 **************************/
	/* 회원 정보 수정 button click event */
	$("#modifyTeacherBtn").click(function() {

		alert("정보 수정하기 전 비번확인");

		$("#teacherPwCheckForm").attr({
			"method" : "POST",
			"action" : "/teacher/modifyCheckPw"
		});

		$("#teacherPwCheckForm").submit();

	});

	// 비번 확인 버튼 클릭시 이벤트
	$(".teacherOkBtnPwCheck").click(
		function() {

			if ($(".teacher_id").val() == null || $(".teacher_id").val() == ""
				|| $(".teacher_password").val() == null
				|| $(".teacher_password").val() == "") {
				$("#loginAlert").css("color", "#FF0000").html(
					"PW가 입력되지 않았습니다. 다시한번 확인해 주세요.");
				return;
			}

			$("#loginAlert").html("");

			$("#teacherModifyForm").attr({
				"method" : "post",
				"action" : "/teacher/modifyinfo"
			});

			$("#teacherModifyForm").submit();

			alert("수정page 진입 시도!");
		});

});