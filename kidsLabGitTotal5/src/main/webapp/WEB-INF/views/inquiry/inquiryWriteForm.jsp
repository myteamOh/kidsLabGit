<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,  initial-scale=1.0,  maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>1:1문의 글쓰기 폼</title>
<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css" />


<!-- 모바일 웹 페이지 설정 -->
<!-- <link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png" /> -->
<!-- 모바일 웹 페이지 설정 끝 -->
<!--[if lt IE 9]> <script src="/resources/include/js/html5shiv.js"></script> <![endif]-->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript"
	src="/resources/include/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		//저장 버튼 클릭시 처리 이벤트
		$("#inquiryInsertBtn").click(
				function() {
					// 입력값 체크
					$("#inquiry_content").val(
							CKEDITOR.instances.inquiry_content.getData()); // 글 쓰는 부분에 Ckeditor 적용
					if (!chkSubmit($('#inquiry_content'), "작성할 내용을")) {
						return;
					} else if (!chkSubmit($('#inquiry_title'), "제목을")) {
						return;
					} else {
						$("#i_writeForm").attr({
							"method" : "POST",
							"action" : "/inquiry/inquiryInsert"
						});
						$("#i_writeForm").submit();
					}

				});

		/* 리셋 버튼 클릭시 처리 이벤트 */

		$("#requestReset").click(function() {
			$("#i_writeForm").each(function() {
				this.reset();
			});
		});

		//목록 버튼클릭시 처리 이벤트
		$("#inquiryListBtn").click(function() {
			location.href = "/inquiry/inquiryList";
		});

	});
</script>




<!-- registForm = i_writeForm -->

<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<div class="contentContainer">
		<div class="well">
			<form id="i_writeForm" class="form-horizontal" name="i_writeForm"
				enctype="multipart/form-data">
				<input type="hidden" name="parent_no" id="parent_no"
					value="${Login.parent_no}">
				<div class="form-group form-group-sm">

					<div class="col-1g-12">
						<table id="inquiryWrite">
							<colgroup>
								<col width="17%" />
								<col width="83%" />
							</colgroup>
							<tr>

								<td><input type="text" name="inquiry_title"
									id="inquiry_title" placeholder="제목을 입력하여 주십시오."></td>
							</tr>
							<tr>

								<td><textarea name="inquiry_content" id="inquiry_content"
										class="ckeditor" rows="20" cols="50"
										placeholder="문의사항을 입력하여 주십시오."></textarea></td>
							</tr>


						</table>
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
			</form>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-6">
				<input type="button" value="등록" id="inquiryInsertBtn"
					class="btn btn-default" /> <input type="button" value="재작성"
					id="requestReset" class="btn btn-default" /> <input type="button"
					value="목록보기" id="inquiryListBtn" class="btn btn-default" />
			</div>
		</div>

	</div>

</body>
</html>