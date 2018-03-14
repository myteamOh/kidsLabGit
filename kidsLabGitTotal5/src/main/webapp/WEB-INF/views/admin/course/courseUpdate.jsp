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
<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/include/dist/css/dashboard.css" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- 모바일 웹 페이지 설정 -->
<!-- <link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png" /> -->
<!-- 모바일 웹 페이지 설정 끝 -->
<!--[if lt IE 9]> <script src="/resources/include/js/html5shiv.js"></script> <![endif]-->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript"
	src="/resources/include/js/registCourse.js"></script>
<script type="text/javascript">
	$(function() {
		var course_status = $('<c:out value = "${updateData.course_status}" />');
		if (course_status == "등록대기") {
			$("#registwaitng").attr({
				"selected" : "selected"
			});
		} else if (course_status = "승인대기") {
			$("#agreewaiting").attr({
				"selected" : "selected"
			});
		} else if (course_status = "모집중") {
			$("#recruiting").attr({
				"selected" : "selected"
			});
		} else if (course_status = "진행중") {
			$("#progressing").attr({
				"selected" : "selected"
			});
		}
		// 강의실
		for (var i = 0; i < 8; i++) {
			if ($("#room").val() == $("#course_room option:eq(" + i + ")").val()) {
				$("#course_room option:eq(" + i + ")").attr("selected", "selected");
			}
		}
		// 날짜
		for (var i = 0; i < 5; i++) {
			if ($("#day").val() == $("#course_day option:eq(" + i + ")").val()) {
				$("#course_day option:eq(" + i + ")").attr("selected", "selected");
			}
		}
		// 시간
		for (var i = 0; i < 4; i++) {
			if ($("#hour").val() == $("#course_hour option:eq(" + i + ")").val()) {
				$("#course_hour option:eq(" + i + ")").attr("selected", "selected");
			}
		}
		var course_time = $('<c:out value = "${updateData.course_time}" />');

		//저장 버튼 클릭시 처리 이벤트
		$("#requestInsert").click(function() {
			// 입력값 체크
			if (!chkSubmit($('#course_name'), "강의명을")) {
				return;
			} else if (!chkSubmit($('#course_subject'), "강의과목을")) {
				return;
			} else if (!chkSubmit($('#course_summary'), "강의개요를")) {
				return;
			} else if (!chkSubmit($("#course_startdate"), "시작 날짜를")) {
				return;
			} else if (!chkSubmit($("#course_enddate"), "종료날짜를")) {
				return;
			} else if ($("#course_startdate").val() > $("#course_enddate").val()) {
				alert("종료날짜가 시작날짜보다 빠릅니다.");
				$("#course_enddate").val("${updateData.course_enddate}");
				return;
			} else {
				if ($('#file').val() != "") {
					if (!chkPlanFile($('#file'))) {
						return;
					}
				}
				$("#course_time").val(
					$("#course_day").val() + " " + $("#course_hour").val());
				$("#updateForm").attr({
					"method" : "POST",
					"action" : "/admin/course/courseUpdate"
				});
				$("#updateForm").submit();
			}
		});
		// 초기화버튼 클릭시 처리 이벤트
		$("requestReset").click(function() {
			$("#updateForm").each(function() {
				this.reset();
			});
		});
		//취소버튼 클릭시 처리 이벤트
		$("#requestCancel").click(function() {
			location.href = "/admin/course/courseList";
		});
	});
	function chgTime() {
		uform = document.updateForm;
		if (uform.course_day.value == "월요일" || uform.course_day.value == "화요일" || uform.course_day.value == "목요일" || uform.course_day.value == "금요일") {
			uform.course_hour.length = 3;
			uform.course_hour.options[0].text = "15:00 ~ 16:45";
			uform.course_hour.options[0].value = "15:00 ~ 16:45";
			uform.course_hour.options[1].text = "17:00 ~ 18:45";
			uform.course_hour.options[1].value = "17:00 ~ 18:45";
			uform.course_hour.options[2].text = "19:00 ~ 20:45";
			uform.course_hour.options[2].value = "19:00 ~ 20:45";
		} else {
			uform.course_hour.length = 4;
			uform.course_hour.options[0].text = "10:00 ~ 11:45";
			uform.course_hour.options[0].value = "10:00 ~ 11:45";
			uform.course_hour.options[1].text = "13:00 ~ 14:45";
			uform.course_hour.options[1].value = "13:00 ~ 14:45";
			uform.course_hour.options[2].text = "15:00 ~ 16:45";
			uform.course_hour.options[2].value = "15:00 ~ 16:45";
			uform.course_hour.options[3].text = "17:00 ~ 18:45";
			uform.course_hour.options[3].value = "17:00 ~ 18:45";
		}
	}
	function onlyNumber(obj) {
		$(obj).keyup(function() {
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
		});
	}
</script>
<style type="text/css">
#cl-dashboard {
	display: none;
}

#course_pay {
	text-align: right;
}
</style>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>강의 등록 및 수정</h3>
		</div>
		<div class="well">
			<form id="updateForm" class="form-horizontal" name="updateForm"
				enctype="multipart/form-data">
				<input type="hidden" id="course_no" name="course_no"
					value="${updateData.course_no }"> <input type="hidden"
					id="course_plan" name="course_plan"
					value="${updateData.course_plan }"> <input type="hidden"
					id="course_time" name="course_time"
					value="${updateData.course_time }"> <input type="hidden"
					id="page" name="page" value="${param.page }"> <input
					type="hidden" id="pageSize" name="pageSize"
					value="${param.pageSize }">
				<div class="form-group form-group-sm">
					<label for="course_name" class="col-sm-2 control-label">강의명</label>
					<div class="col-sm-3">
						<input type="text" id="course_name" name="course_name"
							maxlength="30" class="form-control"
							value="${updateData.course_name }" />
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
							maxlength="15" class="form-control"
							value="${updateData.course_subject }">
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
							<option value="${updateData.course_level }" selected="selected">${updateData.course_level }</option>
						</select>
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_summary" class="col-sm-2 control-label">강의
						시간</label>
					<div class="col-sm-3">
						<select id="course_day" name="course_day" onchange="chgTime()">
							<option value="월요일">월요일</option>
							<option value="화요일">화요일</option>
							<option value="목요일">목요일</option>
							<option value="금요일">금요일</option>
							<option value="토요일">토요일</option>
						</select> <select id="course_hour" name="course_hour">
							<option value="15:00 ~ 16:45">15:00 ~ 16:45</option>
							<option value="17:00 ~ 18:45">17:00 ~ 18:45</option>
							<option value="19:00 ~ 20:45">19:00 ~ 20:45</option>
						</select> <input type="hidden" id="day" name="day" value="${day }">
						<input type="hidden" id="hour" name="hour" value="${hour }">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_room" class="col-sm-2 control-label">강의실</label>
					<div class="col-sm-3">
						<select id="course_room" name="course_room">
							<option value="1">1 강의실</option>
							<option value="2">2 강의실</option>
							<option value="3">3 강의실</option>
							<option value="4">4 강의실</option>
							<option value="5">5 강의실</option>
							<option value="6">6 강의실</option>
							<option value="7">7 강의실</option>
							<option value="8">8 강의실</option>
						</select><input type="hidden" id="room" value="${updateData.course_room }">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_pay" class="col-sm-2 control-label">강의료</label>
					<div class="col-sm-3">
						<input type="text" id="course_pay" name="course_pay"
							onkeydown="onlyNumber(this)" value="${updateData.course_pay }"
							maxlength="7">원
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_status" class="col-sm-2 control-label">등록상태</label>
					<div class="col-sm-3">
						<select id="course_status" name="course_status">
							<c:choose>
								<c:when test="${ updateData.course_status == '등록대기'}">
									<option value="registwaiting" id="registwaiting">등록대기</option>
									<option value="agreewaiting">승인대기</option>
									<option value="recruiting">모집중</option>
									<option value="progressing">진행중</option>
									<option value="cancel">폐강</option>
								</c:when>
								<c:when test="${ updateData.course_status == '승인대기'}">
									<option value="agreewaiting" id="agreewaiting">승인대기</option>
									<option value="recruiting">모집중</option>
									<option value="progressing">진행중</option>
									<option value="cancel">폐강</option>
								</c:when>
								<c:when test="${ updateData.course_status == '모집중'}">
									<option value="recruiting" id="recruiting">모집중</option>
									<option value="progressing">진행중</option>
									<option value="cancel">폐강</option>
								</c:when>
								<c:when test="${ updateData.course_status == '진행중'}">
									<option value="progressing" id="progressing">진행중</option>
									<option value="end">강의종료</option>
								</c:when>
							</c:choose>
						</select>
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_startdate" class="col-sm-2 control-label">시작날짜</label>
					<div class="col-sm-3">
						<input type="text" id="course_startdate" name="course_startdate">
					</div>
					<label for="course_enddate" class="col-sm-2 control-label">종료날짜</label>
					<div class="col-sm-3">
						<input type="text" id="course_enddate" name="course_enddate">
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
							class="form-control" rows="15" cols="50">${updateData.course_summary }</textarea>
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="file" class="col-sm-2 control-label">강의 계획서</label>
					<div class="col-sm-3">
						<input type="file" id="file" name="file"><span
							id="oldFile">기존 파일명 : ${updateData.course_plan }</span>
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input type="button" value="등록" id="requestInsert"
							class="btn btn-default" /> <input type="button" value="재작성"
							id="requestReset" class="btn btn-default" /> <input
							type="button" value="취소" id="requestCancel"
							class="btn btn-default" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$("#course_startdate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			altFormat : "yy-mm-dd"
		});
		$("#course_startdate").attr({
			"value" : "${updateData.course_startdate}",
			"readonly" : "readonly"
		});
		$("#course_enddate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			altFormat : "yy-mm-dd"
		});
		$("#course_enddate").attr({
			"value" : "${updateData.course_enddate}",
			"readonly" : "readonly"
		});
		$("#course_enddate").change(function() {
			var startDate = $("input[name='course_startdate']").val();
			var startDateArr = startDate.split('-');
	
			var endDate = $("input[name='course_enddate']").val();
			var endDateArr = endDate.split('-');
	
			var startDateCompare = new Date(startDateArr[0], parseInt(startDateArr[1]) - 1, startDateArr[2]);
			var endDateCompare = new Date(endDateArr[0], parseInt(endDateArr[1]) - 1, endDateArr[2]);
	
			if (startDateCompare.getTime() > endDateCompare.getTime()) {
				alert("시작날짜와 종료날짜를 확인해 주세요.") ;
				$("#course_enddate").val("${updateData.course_enddate}");
				return;
			}
		});
	</script>
</body>
</html>