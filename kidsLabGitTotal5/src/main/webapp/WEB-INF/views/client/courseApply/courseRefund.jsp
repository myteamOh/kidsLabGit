<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환불페이지</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/mypage.js"></script>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/courseRefund.css">

</head>
<body>

	<section>

		<div class="refundPart">
			<label>환불규정</label>
			<div class="refundRule">
				<pre>교습 시작 전 또는 당일: 이미 납부한 교습비 등의 전액을 반환합니다.<br>총 교습시간의 1/3 경과 전: 이미 납부한 교습비 등의 2/3해당액을 반환합니다.<br>총 교습시간의 1/2 경과 전: 이미 납부한 교습비 등의 1/2해당액을 반환합니다.<br>총 교습시간의 1/2 경과 후: 반환하지 않습니다.<br><br> ※ '교습 비 등'이란 학습자가 수강료, 이용료 또는 교습료 등과<br>    그 외에 추가로 납부하는 일체의 경비를 말합니다.</pre>
			</div>
		</div>

		<form id="refundForm">
			<div>

				<input type="hidden" name="requestcourse_no"
					value="${refundData.requestcourse_no}">

				<table>
					<caption>결제 내역</caption>
					<tr>
						<td>강의명</td>
						<td>${refundData.course_name}</td>
					</tr>

					<tr>
						<td>등록과목</td>
						<td>${refundData.course_subject}</td>
					</tr>

					<tr>
						<td>수강일자</td>
						<td>강의시작 ${refundData.pass_day}일차</td>
					</tr>

					<tr>
						<td>강의기간</td>
						<td>${refundData.course_startdate}~
							${refundData.course_enddate}</td>
					</tr>

					<tr>
						<td>강의요일 및 시간</td>
						<td>${refundData.course_time}</td>
					</tr>

					<tr>
						<td>자녀이름</td>
						<td>${refundData.student_name}</td>
					</tr>

					<tr>
						<td>결제된 강의료</td>
						<td>${refundData.requestcourse_payamount}</td>
					</tr>

					<tr>
						<td><label>* </label>환불계좌은행</td>
						<td><select id="refundBank" name="requestcourse_refundbank">
								<option value="신한은행" selected="selected">신한은행</option>
								<option value="국민은행">국민은행</option>
								<option value="농협">농협</option>
								<option value="우리은행">우리은행</option>
								<option value="외환은행">외환은행</option>
								<option value="하나은행">하나은행</option>
								<option value="기업은행">기업은행</option>
								<option value="제일은행">제일은행</option>
								<option value="신협">신협</option>
								<option value="부산은행">부산은행</option>
						</select></td>
					</tr>

					<tr>
						<td><label>* </label>환불 계좌번호</td>
						<td><input type="text" class="refund_account"
							name="requestcourse_accountnumber"
							placeholder="'-'제외 정확히 입력해 주세요.(10~14자리)"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);"><label
							class="check"></label></td>
					</tr>

					<tr>
						<td><label>* </label>예금주</td>
						<td><input type="text" class="refund_name"
							name="requestcourse_accountholder" placeholder="ex)홍길동 2~5자리 한글"
							onkeyup="spaceDelete(this);" onchange="spaceDelete(this);"><label
							class="check"></label></td>
					</tr>

					<tr>
						<td>환불금액</td>
						<td><input type="text" name="requestcourse_refundcharge"
							value="${refundData.requestcourse_refundcharge}"
							readonly="readonly"></td>
					</tr>
				</table>
			</div>
		</form>

		<br>

		<h4>환불 계좌은행, 환불 계좌번호, 예금주 불일치시 환불이 취소될 수 있습니다.</h4>

		<br>

		<div class="btnPart">
			<input type="button" class="refundOk" value="환불신청"> <input
				type="button" class="refundCancel" value="취소">
		</div>

	</section>

</body>
</html>