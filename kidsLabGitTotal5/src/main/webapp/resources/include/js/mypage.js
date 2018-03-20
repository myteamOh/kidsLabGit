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

	/* 수강신청 취소 버튼 이벤트(결제대기상태) */
	$(".applyCancel").click(function() {

		var result = confirm('정말정말 진심으로 수강을 취소하시겠습니까?');

		if (result) {

			var ac_rc_no = $(this).parents("tr").attr("data-num");

			$("#requestcourse_no").val(ac_rc_no);

			console.log("강의신청 번호 : " + ac_rc_no);

			$("#courseForm").attr({
				"method" : "POST",
				"action" : "/mypage/applyCancel.do"
			});

			$("#courseForm").submit();

		} else {
			return;
		}

	});

	/* 수강취소, 수강포기버튼 클릭시 이벤트(결제완료상태) */
	$(".courseCancel").click(function() {

		alert("환불페이지로 이동합니다.");

		var cc_rc_no = $(this).parents("tr").attr("data-num");

		$("#requestcourse_no").val(cc_rc_no);

		console.log("강의신청 번호 : " + cc_rc_no);

		$("#courseForm").attr({
			"method" : "POST",
			"action" : "/mypage/courseCancel.do"
		});

		$("#courseForm").submit();

	});
	
	$(".refund_account").focusout(function() {
		if (!formCheck($(".refund_account"), $('.check:eq(0)'), "계좌번호를")) {
			return;
		} else if (!regularExpression(7, '.refund_account', '.check:eq(0)', "계좌번호를")) {
			return;
		} else {
			$(".check:eq(0)").html("");
		}
	});
	
	$(".refund_name").focusout(function() {
		if(!formCheck($(".refund_name"), $('.check:eq(1)'), "이름을")) {
			return;
		} else if (!regularExpression(0, '.refund_name', '.check:eq(1)', "이름을")) {
			return;
		} else {
			$(".check:eq(1)").html("");
		}
	});

	/* 환불신청 버튼 클릭시 이벤트 */
	$(".refundOk").click(function() {

		var refundResult = confirm('환불신청은 취소하실수 없습니다. 정말로 환불신청을 하시겠습니까?');

		if (refundResult) {
			
			if (!formCheck($(".refund_account"), $('.check:eq(0)'), "계좌번호를")) {
				return;
			} else if (!regularExpression(7, '.refund_account', '.check:eq(0)', "계좌번호를")) {
				return;
			} else if(!formCheck($(".refund_name"), $('.check:eq(1)'), "이름을")) {
				return;
			} else if (!regularExpression(0, '.refund_name', '.check:eq(1)', "이름을")) {
				return;
			}

			$("#refundForm").attr({
				"method" : "POST",
				"action" : "/mypage/refundApply.do"
			});

			$("#refundForm").submit();

		} else {
			return;
		}
	});
	
	/* 환불취소버튼 클릭시 이벤트 */
	$(".refundCancel").click(function() {
		location.href = "/mypage/parentMypage.do";
	});
	
	/*강의 페이지 클릭시 이벤트*/
	$(".coursePage").click(function() {
		var coursePage = $(this).parents("tr").attr("data-num");
		
		$("#courseNum").val(coursePage);
		
		$("#coursePageForm").attr({
			"method" : "POST",
			"action" : "/coursepage/coursemain"
		});
		
		$("#coursePageForm").submit();
	});

});