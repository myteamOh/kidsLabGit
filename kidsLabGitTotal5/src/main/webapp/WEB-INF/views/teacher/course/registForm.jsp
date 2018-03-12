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
<title>Join Teacher!!</title>

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
		$("#requestInsert").click(function() {
			// 입력값 체크
			if (!chkSubmit($('#course_name'), "강의명을")) {
				return;
			} else if (!chkSubmit($('#course_subject'), "강의과목을")) {
				return;
			} else if (!chkSubmit($('#course_summary'), "강의개요를")) {
				return;
			} else if ($('#file').val() == "" || $('#file').val() == null) {
				alert("강의계획서를 등록해주세요.");
				return;
			} else {
				if ($('#file').val() != "") {
					if (!chkPlanFile($('#file'))) {
						return;
					}
				}
				$("#registForm").attr({
					"method" : "POST",
					"action" : "/teacher/course/registCourse"
				});
				$("#registForm").submit();
			}
		});

		$("#requestReset").click(function() {
			$("#registForm").each(function() {
				this.reset();
			});
		});

		//취소버튼클릭시 처리 이벤트
		$("#requestCancel").click(function() {
			location.href = "/teacher/login";
		});
	});
</script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<div class="contentContainer">
		<div class="well">
			<form id="registForm" class="form-horizontal" name="registForm"
				enctype="multipart/form-data">
				<input type="hidden" name="teacher_no"
					value="${teacherLogin.teacher_no}">
				<div class="form-group form-group-sm">
					<label for="course_name" class="col-sm-2 control-label">강의명</label>
					<div class="col-sm-3">
						<input type="text" id="course_name" name="course_name"
							maxlength="30" class="form-control" placeholder="강의명" />
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_subject" class="col-sm-2 control-label">강의
						과목</label>
					<div class="col-sm-3">
						<input type="text" id="course_subject" name="course_subject"
							maxlength="15" class="form-control" placeholder="강의 과목">
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
				</div>
				<div class="form-group form-group-sm">
					<label for="file" class="col-sm-2 control-label">강의 계획서</label>
					<div class="col-sm-3">
						<input type="file" id="file" name="file">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input type="button" value="신청" id="requestInsert"
							class="btn btn-default" /> <input type="button" value="재작성"
							id="requestReset" class="btn btn-default" /> <input
							type="button" value="취소" id="requestCancel"
							class="btn btn-default" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>