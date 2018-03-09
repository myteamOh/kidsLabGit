<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,  initial-scale=1.0,  maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>1:1문의 글쓰기 폼</title>

<!-- 모바일 웹 페이지 설정 -->
<!-- <link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png" /> -->
<!-- 모바일 웹 페이지 설정 끝 -->
<!--[if lt IE 9]> <script src="/resources/include/js/html5shiv.js"></script> <![endif]-->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript"
	src="/resources/include/js/registCourse.js"></script>
<script type="text/javascript">
	$(function() {
		//저장 버튼 클릭시 처리 이벤트
		$("#inquiryInsertBtn").click(function() {
			// 입력값 체크
			if (!chkSubmit($('#inquiry_title'), "글 제목을")) {
				return;
			} else if (!chkSubmit($('#inquiry_content'), "글 내용을")) {
				return;
			} else {

				$("#i_writeForm").attr({
					"method" : "POST",
					"action" : "/inquiry/inquiryInsert.do"
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
			location.href = "/inquiry/inquiryList.do";
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
				<input type="hidden" name="parent_no" value="${inquiry.parent_no}">
				<div class="form-group form-group-sm">
					<label for="i_title" class="col-sm-2 control-label">글
						제목</label>
					<div class="col-sm-3">
						<input type="text" id="inquiry_title" name="inquiry_title"
							maxlength="30" class="form-control" placeholder="1:1문의 제목" />
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<!-- <div class="form-group form-group-sm">
					<label for="course_subject" class="col-sm-2 control-label">
						</label>
					<div class="col-sm-3">
						<input type="text" id="course_subject" name="course_subject"
							maxlength="15" class="form-control" placeholder="">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_level" class="col-sm-2 control-label">강의
						대상</label>
					<div class="col-sm-3">
						<select id="course_level" name="course_level" class="form-control">
							<option value="1~2학년" selected="selected">1~2학년</option>
							<option value="3~4학년">3~4학년</option>
							<option value="5~6학년">5~6학년</option>
						</select>
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_summary" class="col-sm-2 control-label">강의
						개요</label>
					<div class="col-sm-3">
						<textarea id="course_summary" name="course_summary"
							class="form-control" rows="15" cols="70"></textarea>
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div> -->
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input type="button" value="등록" id="inquiryInsertBtn"
							class="btn btn-default" /> <input type="button" value="재작성"
							id="requestReset" class="btn btn-default" /> <input
							type="button" value="목록보기" id="inquiryListBtn"
							class="btn btn-default" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>