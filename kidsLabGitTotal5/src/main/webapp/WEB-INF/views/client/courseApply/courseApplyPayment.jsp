<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의신청(수강신청 및 결제)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="/resources/include/js/courseApply.js"></script>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/courseApplyPayment.css">

</head>
<body>
	<section>
		<div>
			<form class="confirmForm">
				<div>
					<h3>수강신청 및 결제</h3>

					<input type="hidden" name="parent_no" value="${Login.parent_no}">
					<input type="hidden" name="course_no" value="${cDetail.course_no}">
					<input type="hidden" name="requestcourse_payamount"
						value="${cDetail.course_pay}"> <input type="hidden"
						id="limitCount" value="${cCount}">

					<table>
						<caption>수강정보</caption>
						<tr>
							<td>강의명</td>
							<td>${cDetail.course_name}</td>
						</tr>
						<tr>
							<td>강의 과목</td>
							<td>${cDetail.course_subject}</td>
						</tr>
						<tr>
							<td>강의 대상</td>
							<td>${cDetail.course_level}</td>
						</tr>
						<tr>
							<td>강의 기간</td>
							<td>${cDetail.course_startdate}~${cDetail.course_enddate}</td>
						</tr>
						<tr>
							<td>강의 요일 및 시간</td>
							<td>${cDetail.course_time}</td>
						</tr>
						<tr>
							<td>수강학생</td>
							<td><select class="selectStudent" name="student_no">
									<c:choose>
										<c:when test="${not empty studentList}">
											<c:forEach var="sList" items="${studentList}">
												<option value="${sList.student_no}">${sList.student_name}</option>
											</c:forEach>
										</c:when>

										<c:otherwise>
											<option value="">자녀를 추가해 주세요.</option>
										</c:otherwise>
									</c:choose>
							</select></td>
						</tr>
					</table>
				</div>

				<br>

				<div class="refundPart">
					<label>환불규정 안내</label>
					<div class="refundRule">
						<pre>교습 시작 전 또는 당일: 이미 납부한 교습비 등의 전액을 반환합니다.<br>총 교습시간의 1/3 경과 전: 이미 납부한 교습비 등의 2/3해당액을 반환합니다.<br>총 교습시간의 1/2 경과 전: 이미 납부한 교습비 등의 1/2해당액을 반환합니다.<br>총 교습시간의 1/2 경과 후: 반환하지 않습니다.<br><br> ※ '교습 비 등'이란 학습자가 수강료, 이용료 또는 교습료 등과<br>    그 외에 추가로 납부하는 일체의 경비를 말합니다.</pre>
					</div>
				</div>

				<br>

				<div>
					<table>
						<caption>결제하기</caption>
						<tr>
							<td>결제금액</td>
							<td>${cDetail.course_pay}</td>
						</tr>
						<tr>
							<td>결제방법</td>
							<td><input type="radio" id="bankbookDeposit"
								name="requestcourse_paymethod" value="bankbookDeposit"
								checked="checked"><label for="bankbookDeposit">무통장입금</label></td>
						</tr>
						<tr>
							<td>결제은행 및 계좌번호</td>
							<td><select name="bank_and_account">
									<option value="신한은행 110-000-000000">신한은행
										110-000-000000</option>
							</select></td>
						</tr>
					</table>
				</div>

				<h4>현재는 무통장입금만 가능합니다.</h4>

				<div class="btnPart">
					<input type="button" class="payBtn" value="결제"> <input
						type="button" class="toListBtn" value="취소">
				</div>
			</form>
		</div>
	</section>
</body>
</html>