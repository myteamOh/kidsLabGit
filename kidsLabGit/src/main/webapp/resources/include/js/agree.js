$(function() {

	/* 약관동의 페이지 동의버튼 클릭 이벤트 */
	$("#okBtn").click(function() {
		
		var information = $("input:radio[id='information']").is(":checked");
		var portrait = $("input:radio[id='portrait']").is(":checked");
		
		if (information == false || portrait == false) {

			alert("필수항목을 모두 체크해 주세요.");

		} else {

			location.href = "/member/parentjoin.do";

		}

	});
	
	/* 약관동의 페이지 취소버튼 클릭 이벤트 */
	$("#cancelBtn").click(function() {
		
		location.href = "/login/login.do";
		
	});

});