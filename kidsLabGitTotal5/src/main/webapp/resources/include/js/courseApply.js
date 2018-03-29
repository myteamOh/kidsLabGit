$(function() {

	/* 강의신청 조회 select option change시 이벤트 */
	$(".courseLevel").change(function() {

		$.ajax({
			url : "/requestcourse/apply",
			type : "GET",
			data : "course_level=" + $(".courseLevel").val(),
			error : function() {
				alert("실패");
			},
			success : function(data) {
				alert("성공");
				$(".search").attr({
					"action" : "/requestcourse/apply",
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
			"action" : "/requestcourse/applyDetail"
		});

		$(".detailForm").submit();

	});

	/* 상세보기 페이지에서 수강신청버튼 클릭시 이벤트 */
	$(".applyBtn").click(function() {

		if ($("#limitCount").val() <= 0) {
			alert("정원이 초과되어 신청할 수 없습니다.");
			return;
		} else if($("#idCheck").val() == ""){
			alert("로그인후 이용해 주세요.");
			return;
		} else if($("#idCheck").val().indexOf("@") < 0) {
			alert("학부모 회원만 수강신청이 가능합니다.");
			return;
		}

		$(".applyForm").attr({
			"method" : "POST",
			"action" : "/requestcourse/applyNPayment"
		});

		$(".applyForm").submit();

	});
	
	/*결제버튼 클릭시 이벤트*/
	$(".payBtn").click(function() {
		
		if($(".selectStudent").val() == "") {
			alert("자녀를 추가해 주세요");
			return;
		} else {
			
			$.ajax({
				url : "/requestcourse/applyConfirmCheck",
				type : "POST",
				data : $(".confirmForm").serialize(),
				error : function() {
					alert("그냥오류");
				},
				success : function(result) {

					if (result == 1) {
						alert("성공!");
						$(".confirmForm").attr({
							"method" : "POST",
							"action" : "/requestcourse/applyConfirm"
						});

						$(".confirmForm").submit();
					} else {
						alert("해당학생은 해당강의를 신청하였습니다.");
						return;
					}
					
				}
			});
			
		}
		
	});

	/* 목록으로 돌아가는 버튼 이벤트 */
	$(".toListBtn").click(function() {
		location.href = "/requestcourse/apply";
	});
	
	$(".toMainBtn").click(function() {
		location.href = "/requestcourse/applyComplete";
	});

});