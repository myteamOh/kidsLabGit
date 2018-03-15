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

		var requestCourse_paymentStatus = $('<c:out value = "${updateData.requestCourse_paymentStatus}" />');
		if (requestCourse_paymentStatus == "결제대기") {
			$("#paymentwaitng").attr({
				"selected" : "selected"
			});
		} else if (requestCourse_paymentStatus = "환불대기") {
			console.log("확인");
			$("#refundwaiting").attr({
				"selected" : "selected"
			});
		}
		//저장 버튼 클릭시 처리 이벤트
		$("#requestInsert").click(function() {
			// 입력값 체크
			$("#updateForm").attr({
				"method" : "POST",
				"action" : "/admin/payment/paymentUpdate"
			});
			$("#updateForm").submit();
		});

		//취소버튼 클릭시 처리 이벤트
		$("#requestCancel").click(function() {
			location.href = "/admin/payment/paymentList";
		});
	});
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
				<input type="hidden" id="requestCourse_no" name="requestCourse_no"
					value="${updateData.requestCourse_no }"><input
					type="hidden" id="page" name="page" value="${param.page }"><input
					type="hidden" id="pageSize" name="pageSize"
					value="${param.pageSize }">
				<div class="form-group form-group-sm">
					<label for="parent_name" class="col-sm-2 control-label">학부모명</label>
					<div class="col-sm-3">
						<input type="text" id="parent_name" name="parent_name"
							maxlength="15" class="form-control"
							value="${updateData.parent_name }" readonly="readonly">
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="student_name" class="col-sm-2 control-label">학생명</label>
					<div class="col-sm-3">
						<input type="text" id="student_name" name="student_name"
							maxlength="15" class="form-control"
							value="${updateData.student_name }" readonly="readonly">
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="course_name" class="col-sm-2 control-label">강의명</label>
					<div class="col-sm-3">
						<input type="text" id="course_name" name="course_name"
							maxlength="15" class="form-control"
							value="${updateData.course_name }" readonly="readonly">
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="requestCourse_paymentDate"
						class="col-sm-2 control-label">결제날짜</label>
					<div class="col-sm-3">
						<input type="date" id="requestCourse_paymentDate"
							name="requestCourse_paymentDate" maxlength="15"
							class="form-control"
							value="${updateData.requestCourse_paymentDate }"
							readonly="readonly">
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="requestCourse_payAmount" class="col-sm-2 control-label">결제금액</label>
					<div class="col-sm-3">
						<input type="text" id="requestCourse_payAmount"
							name="requestCourse_payAmount"
							value="${updateData.requestCourse_payAmount }" maxlength="7"
							readonly="readonly">원
					</div>
					<label for="requestCourse_refundCharge"
						class="col-sm-2 control-label">환불금액</label>
					<div class="col-sm-3">
						<input type="text" id="requestCourse_refundCharge"
							name="requestCourse_refundCharge"
							value="${updateData.requestCourse_refundCharge }" maxlength="7"
							readonly="readonly">원
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="requestCourse_accountHolder"
						class="col-sm-2 control-label">예금주</label>
					<div class="col-sm-3">
						<input type="text" id="requestCourse_accountHolder"
							name="requestCourse_accountHolder"
							value="${updateData.requestCourse_accountHolder }"
							readonly="readonly">
					</div>
					<label for="requestCourse_refundBank"
						class="col-sm-2 control-label">은행명</label>
					<div class="col-sm-3">
						<input type="text" id="requestCourse_refundBank"
							name="requestCourse_refundBank"
							value="${updateData.requestCourse_refundBank }"
							readonly="readonly">
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="requestCourse_accountNumber"
						class="col-sm-2 control-label">환불계좌번호</label>
					<div class="col-sm-3">
						<input type="text" id="requestCourse_accountNumber"
							name="requestCourse_accountNumber"
							value="${updateData.requestCourse_accountNumber }"
							readonly="readonly">
					</div>
					<label for="requestCourse_paymentStatus"
						class="col-sm-2 control-label">결제상태</label>
					<div class="col-sm-3">
						<select id="requestCourse_paymentStatus"
							name="requestCourse_paymentStatus">
							<c:choose>
								<c:when
									test="${updateData.requestCourse_paymentStatus == '결제대기' }">
									<option value="paymentwaiting" id="paymentwaiting">결제대기</option>
									<option value="paymentcomplete">결제완료</option>
								</c:when>
								<c:when
									test="${updateData.requestCourse_paymentStatus == '환불대기' }">
									<option value="refundwaiting" id="refundwaiting">환불대기</option>
									<option value="refundcomplete">환불완료</option>
								</c:when>
							</c:choose>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input type="button" value="등록" id="requestInsert"
							class="btn btn-default" /><input type="button" value="취소"
							id="requestCancel" class="btn btn-default" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>