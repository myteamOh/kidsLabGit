$(function() {

	/* 강의신청 조회 select option change시 이벤트 */
	$(".courseLevel").change(function() {

		$.ajax({
			url : "/requestcourse/apply.do",
			type : "GET",
			data : "course_level=" + $(".courseLevel").val(),
			error : function() {
				alert("실패");
			},
			success : function(data) {
				alert("성공");
				$(".search").attr({
					"action" : "/requestcourse/apply.do",
					"method" : "GET"
				});

				$(".search").submit();
			}
		});

	});

	/* 상세보기 버튼 클릭시 이벤트 */
	$(".detailBtn").click(function() {

		var course_no = $(this).parents("div").attr("data-num");

		$("#courseNum").val(course_no);

		console.log("강의번호 : " + course_no);

		$(".detailForm").attr({
			"method" : "GET",
			"action" : "/requestcourse/applyDetail.do"
		});

		$(".detailForm").submit();

	});

	/* 상세보기 페이지에서 수강신청버튼 클릭시 이벤트 */
	$(".applyBtn").click(function() {

		if ($("#limitCount").val() == 0) {
			alert("정원이 초과되어 신청할 수 없습니다.");
			return;
		}

		$(".applyForm").attr({
			"method" : "POST",
			"action" : "/requestcourse/applyNPayment.do"
		});

		$(".applyForm").submit();

	});
	
	/*결제버튼 클릭시 이벤트*/
	$(".payBtn").click(function() {
		
		if($(".selectStudent").val() == "") {
			alert("자녀를 추가해 주세요");
			return;
		} else {
			$(".confirmForm").attr({
				"method" : "POST",
				"action" : "/requestcourse/applyConfirm.do"
			});
			
			$(".confirmForm").submit();
		}
		
	});

	/* 목록으로 돌아가는 버튼 이벤트 */
	$(".toListBtn").click(function() {
		location.href = "/requestcourse/apply.do";
	});
	
	$(".toMainBtn").click(function() {
		location.href = "/requestcourse/applyComplete.do";
	});

});