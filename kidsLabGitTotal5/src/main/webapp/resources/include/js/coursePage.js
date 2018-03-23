$(function() {

	/* 사이드탭에서 home클릭시 이벤트 */
	$(".coursePageHome").click(function() {
		$("#courseNo").attr({
			"method" : "POST",
			"action" : "/coursepage/courseboardhome"
		});

		$("#courseNo").submit();
	});

	/* 사이드탭에서 목록클릭시 이벤트 */
	$(".coursePageBoardList").click(function() {
		$("#courseNo").attr({
			"method" : "GET",
			"action" : "/coursepage/courseboardList"
		});

		$("#courseNo").submit();
	});

	/* 목록에서 글쓰기버튼 클릭시 이벤트 */
	$(".courseBoardWrite").click(function() {

		location.href = "/coursepage/courseboardWriteForm";

	});

	/* 글쓰기폼에서 확인버튼 이벤트 */
	$(".insertBtn").click(function() {
		if ($("#title").val() == null || $("#title").val() == '') {
			alert("제목을 입력해 주세요.");
			return;
		} else if ($("#content").val() == null || $("#content").val() == '') {
			alert("내용을 입력해 주세요.");
			return;
		}

		$("#writeForm").attr({
			"method" : "POST",
			"action" : "/coursepage/courseboardInsert"
		});

		$("#writeForm").submit();

	});

	/* 제목클릭시 상세보기 */
	$(".postsDetail").click(function() {

		var postsNum = $(this).parents("tr").attr("data-num");
		$("#coursedataNo").val(postsNum);

		$("#detailForm").attr({
			"method" : "GET",
			"action" : "/coursepage/courseboardDetail"
		});

		$("#detailForm").submit();

	});

	/* 상세보기에서 수정버튼 클릭시 이벤트 */
	$(".updateBtn").click(function() {

		$("#updateForm").attr({
			"method" : "GET",
			"action" : "/coursepage/courseboardUpdateForm"
		});

		$("#updateForm").submit();

	});

	/* 상세보기에서 삭제버튼 클릭시 이벤트 */
	$(".deleteBtn").click(function() {
		
		$.ajax({
			url : "/coursepage/courseboardDelete",
			type : "POST",
			data : $("#updateForm").serialize(),
			error : function() {
				alert("기냥오류");
				return;
			},
			success : function(result) {
				
				if(result === 1) {
					alert("삭제성공");
					location.href = "/coursepage/courseboardList";
				} else if (result === 0) {
					alert("삭제실패");
					return;
				}
				
			}
		});
		
	});

	/* 수정폼에서 수정완료버튼 클릭시 이벤트 */
	$(".updateCompleteBtn").click(function() {
		
		$("#updateDataForm").attr({
			"method" : "POST",
			"action" : "/coursepage/courseboardUpdate"
		});
		
		$("#updateDataForm").submit();

	});

	/* 수정폼에서 취소버튼 클릭시 이벤트 */
	$(".updateCancelBtn").click(function() {
		
		$("#updateDataForm").attr({
			"method" : "POST",
			"action" : "/coursepage/courseboardUpdateCancel"
		});
		
		$("#updateDataForm").submit();

	});

});