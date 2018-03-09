$(function() {

	/* 회원 정보 수정 button click event */
	$(".modifyParent").click(function() {

		alert("학부모 정보 수정하기");

		$("#modifyForm").attr({
			"method" : "post",
			"action" : "/mypage/modifyinfo.do"
		});

		$("#modifyForm").submit();

	});

	/* 자녀 추가 button click event */
	$("#studentJoin").click(function() {

		alert("자녀추가하기");

		location.href = "/member/studentjoin.do";

	});

});