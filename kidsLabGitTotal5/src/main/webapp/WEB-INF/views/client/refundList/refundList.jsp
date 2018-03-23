<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>환불 리스트</title>
<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/include/dist/css/dashboard.css" rel="stylesheet">
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
<script
	src="/resources/include/dist/assets/js/ie-emulation-modes-warning.js"></script>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>환불 리스트</h3>
		</div>
		<%-- =============== 검색기능 종료 =============== --%>
		<%-- =============== 리스트 시작 ================= --%>
		<div id="boardList">
			<table summary="환불 리스트">
				<thead>
					<tr>
						<th class="order">학생명</th>
						<th>강의명</th>
						<th>결제금액</th>
						<th>환불금액</th>
						<th>예금주</th>
						<th>환불은행</th>
						<th>환불계좌</th>
						<th>수강신청날짜</th>
						<th>결제완료날짜</th>
						<th>환불신청날짜</th>
						<th>환불완료날짜</th>
						<th>결제상태</th>
					</tr>
				</thead>
				<tbody id="list">
					<!-- 데이터 출력 -->
					<c:choose>
						<c:when test="${not empty refundList}">
							<c:forEach var="refund" items="${refundList}" varStatus="status">
								<tr class="tac">
									<td>${refund.student_name }</td>
									<td>${refund.course_name }</td>
									<td>${refund.requestcourse_payamount}</td>
									<td>${refund.requestcourse_refundcharge}</td>
									<td>${refund.requestcourse_accountholder}</td>
									<td>${refund.requestcourse_refundbank}</td>
									<td>${refund.requestcourse_accountnumber}</td>
									<td>${refund.requestcourse_paymentdate}</td>
									<td>${refund.requestcourse_paycompletedate}</td>
									<td>${refund.requestcourse_refunddate}</td>
									<td>${refund.requestcourse_refundcomplete}</td>
									<td>${refund.requestcourse_paymentstatus}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="12" class="tac">등록된 게시 물이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<%-- =============== 리스트 종료 ===============--%>

	</div>
</body>
</html>