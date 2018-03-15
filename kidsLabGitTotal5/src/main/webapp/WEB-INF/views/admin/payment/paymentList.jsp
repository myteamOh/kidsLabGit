<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0 , 
				minimum-scale=1.0 , user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/include/dist/css/dashboard.css" rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!-- [if It IE 9]>
<script src="/resources/include/dist/assets/js/ie8-responsive-file-warning.js"></script>
<[endif] -->
<script
	src="/resources/include/dist/assets/js/ie-emulation-modes-warning.js"></script>
<style type="text/css">
</style>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/adminPayment.css" />
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		// 검색 후 검색 대상과 검색 단어 출력
		var word = "<c:out value = '${paymentData.keyword}' />";
		var value = "";
		var requestCourse_paymentStatus = "<c:out value = '${paymentData.requestCourse_paymentStatus}' />";

		// 검색 후 검색 상태 출력
		if (requestCourse_paymentStatus != "") {
			$("#requestCourse_paymentStatus").val("<c:out value='${paymentData.requestCourse_paymentStatus}' />");
		}
		// 검색 후 검색 대상과 검색 단어 출력
		if (word != "") {
			$("#keyword").val("<c:out value='${paymentData.keyword}' />");
			$("#search").val("<c:out value='${paymentData.search}' />");


		/* if ($("#search").val() != 'parent_name') {
			//contains() 는 특정 텍스트를 포함한 요소 반환
			if ($("search").val() == 'student_name') {
				value = "#list tr td.student";
			} else if ($("search").val() == 'course_name') {
				value = "#list tr td.course";
			}
			$(value + ":contains('" + word + "')").each(
				function() {
					var regex = new RegExp(word, 'gi');
					$(this).html(
						$(this).text().replace(
							regex,
							"<span class='required'>" + word
							+ "</span>"));
				});
		} */
		}

		/* 한 페이지에 보여줄 레코드 수 조회 후 선택 한 값 그대로 유지하기 위한 설정*/
		if ("<c:out value='${paymentData.pageSize}' />" != "") {
			$("#pageSize").val("<c:out value='${paymentData.pageSize}' />");
		}

		/* 검색 대상이 변경 될 때마다 처리 이벤트*/
		$("#search").change(function() {
			if ($("#search").val() == "all") {
				$("#keyword").val("전체 데이터 조회합니다.");
			} else if ($("#search").val() != "all") {
				$("#keyword").val("");
				$("#keyword").focus();
			}
		});

		//등록상태 대상이 변경 될 때마다 처리 이벤트
		$("#requestCourse_paymentStatus").change(function() {
			goPage(1);
		})

		/* 검색 버튼 클릭 시 처리 이벤트 */
		$("#searchData").click(function() {
			if ($("#search").val() != "all") {
				if (!chkSubmit($('#keyword'), "검색어를")) {
					return;
				}
			}
			goPage(1);
		});

		/* 한 페이지에 보여줄 레코드 수 변경 될 때마다 처리 이벤트 */
		$("#pageSize").change(function() {
			goPage(1);
		});

		/* 수정 버튼 클릭 시 페이지 이동*/
		$(".registBtn").click(function() {
			var requestCourse_no = $(this).parents("tr").attr("data-num");
			$("#requestCourse_no").val(requestCourse_no);
			console.log("수강신청 번호 : " + requestCourse_no);

			$("#paymentUpdateForm").attr({
				"method" : "get",
				"action" : "/admin/payment/paymentUpdate"
			});
			$("#paymentUpdateForm").submit();
		});

	});
	/* 검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징을 위한 실질적인 처리 함수 */
	/* function goPage(page) {
		if ($("#search").val() == "all") {
			$("#keyword").val("");
		}
		$("#page").val(page);
		$("#c_search").attr({
			"method" : "get",
			"action" : "/admin/course/courseList.do"
		});
		$("#c_search").submit();
	} */
	function goPage(page) {
		if ($("#search").val() == "all") {
			$("#keyword").val("");
		}
		$("#page").val(page);
		$("#p_search").attr({
			"method" : "get",
			"action" : "/admin/payment/paymentList"
		});
		$("#p_search").submit();
	}
</script>
</head>
<body>
	<div class="contentContainer container-fluid" id="payment">
		<c:if test="${adminLogin.userId != null and adminLogin.userId != '' }">
			<div class="contentTit">
				<h3>결제 리스트</h3>
			</div>
			<%-- ===============수정 을 위한 FORM ============ --%>
			<form name="paymentUpdateForm" id="paymentUpdateForm">
				<input type="hidden" name="requestCourse_no" id="requestCourse_no">
				<input type="hidden" name="page" value="${paymentData.page }">
				<input type="hidden" name="pageSize"
					value="${paymentData.pageSize }">
			</form>
			<%-- ==============검색 기능 시작 ====================== --%>
			<div id="paymentSearch">
				<form id="p_search" name="p_search">
					<input type="hidden" id="page" name="page"
						value="${paymentData.page }">
					<table summary="검색">
						<tr>
							<td id="btd1"><label>검색조건</label> <select id="search"
								name="search">
									<option value="all">전체</option>
									<option value="parent_name">학부모명</option>
									<option value="student_name" class="student">학생명</option>
									<option value="course_name">강의명</option>
							</select> <input type="text" name="keyword" id="keyword"
								value="전체 데이터 조회합니다."> <input type="button" value="검색"
								id="searchData"></td>
							<td id="btd3">결제상태<select id="requestCourse_paymentStatus"
								name="requestCourse_paymentStatus">
									<option value="all" class="paymentClass">전체</option>
									<option value="paymentwaiting">결제대기</option>
									<option value="paymentcomplete">결제완료</option>
									<option value="refundwaiting">환불대기</option>
									<option value="refundcomplete">환불완료</option>
							</select></td>
							<td id="btd2">한페이지에 <select id="pageSize" name="pageSize">
									<option value="1">1줄</option>
									<option value="3">3줄</option>
									<option value="7">7줄</option>
									<option value="10">10줄</option>
							</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</c:if>
		<%-- =====================검색 기능 종료 ================== --%>

		<%-- =============== 리스트 시작 ================= --%>
		<c:if test="${adminLogin.userId != null and adminLogin.userId != '' }">
			<div id="paymentList" class="container-fluid">
				<table summary="결제 리스트" class="table-striped table-hover">
					<colgroup>
						<%-- <col width="10%" />
					<col width="62%" />
					<col width="15%" />
					<col width="13%" /> --%>
					</colgroup>
					<thead>
						<tr>
							<th>수강신청 번호</th>
							<th>학부모명</th>
							<th>학생명</th>
							<th>강의명</th>
							<th>예금주</th>
							<th>결제금액</th>
							<th>환불금액</th>
							<th>결제날짜</th>
							<th>결제상태</th>
						</tr>
					</thead>
					<tbody id="list">
						<!-- 데이터 출력 -->
						<c:choose>
							<c:when test="${not empty paymentList}">
								<c:forEach var="payment" items="${paymentList}"
									varStatus="status">
									<tr class="tac" data-num="${payment.requestCourse_no}">
										<td>${payment.requestCourse_no }</td>
										<td>${payment.parent_name}</td>
										<td class="student">${payment.student_name}</td>
										<td class="course">${payment.course_name}</td>
										<td>${payment.requestCourse_accountHolder }</td>
										<td>${payment.requestCourse_payAmount}</td>
										<td>${payment.requestCourse_refundCharge }</td>
										<td>${payment.requestCourse_paymentDate}</td>
										<c:choose>
											<c:when
												test="${payment.requestCourse_paymentStatus == '결제대기'}">
												<td>${payment.requestCourse_paymentStatus}<br> <input
													type="button" id="registBtn" name="registBtn"
													class="registBtn" value="수정"> <input type="button"
													id="registdeleteBtn" name="deleteBtn" class="deleteBtn"
													value="삭제">
												</td>
											</c:when>
											<c:when
												test="${payment.requestCourse_paymentStatus == '환불대기'}">
												<td>${payment.requestCourse_paymentStatus}<br> <input
													type="button" id="registBtn" name="registBtn"
													class="registBtn" value="수정"> <input type="button"
													id="registdeleteBtn" name="deleteBtn" class="deleteBtn"
													value="삭제">
												</td>
											</c:when>
											<c:otherwise>
												<td>${payment.requestCourse_paymentStatus }</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="9" class="tac">등록된 게시 물이 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</c:if>
		<%-- =============== 리스트 종료 ===============--%>
		<%-- =============== 페이지 네비게이션 시작 ============== --%>
		<c:if test="${adminLogin.userId != null and adminLogin.userId != '' }">
			<div id="paymentPage">
				<tag:paging page="${param.page }" total="${total }"
					list_size="${paymentData.pageSize }"></tag:paging>
			</div>
		</c:if>
		<!-- ================ 페이지 네비게이션 종료 ============== -->
	</div>
</body>
</html>