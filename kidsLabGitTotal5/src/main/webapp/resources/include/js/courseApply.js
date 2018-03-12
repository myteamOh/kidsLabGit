$(function() {

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

});