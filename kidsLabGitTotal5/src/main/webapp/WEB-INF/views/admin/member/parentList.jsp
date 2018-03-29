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
<title>강사 리스트</title>
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
	href="/resources/include/css/adminPayment.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		/* 검색 후 검색 대상과 검색 단어 출력 */
		var word = "<c:out value='${parentData.keyword}' />";
		var start_date = "<c:out value='${parentData.start_date}' />";
		var end_date = "<c:out value='${parentData.end_date}' />";
		var parent_status = "<c:out value = '${parentData.parent_status}' />";
		var value = "";
		// 검색 후 검색 상태 출력
		if (parent_status != "") {
			$("#parent_status").val("<c:out value='${parentData.parent_status}' />");
		}
		if (word != "") {
			$("#keyword").val("<c:out value='${parentData.keyword}' />");
			$("#search").val("<c:out value='${parentData.search}' />");
		}
		if (start_date != "") {
			$("#start_date").val("<c:out value='${parentData.start_date}' />");
			$("#search").val("<c:out value='${parentData.search}' />");
		}
		if (end_date != "") {
			$("#end_date").val("<c:out value='${parentData.end_date}' />");
			$("#search").val("<c:out value='${parentData.search}' />");
		}
		/* 날짜 선택 후 date 박스 유지*/
		if ($("#search").val() == "parent_registerdate") {
			// 기간 선택창 보이기
			document.getElementById("typely").style.display = "inline";
			document.getElementById("keyword").style.display = "none";
		} else {
			// 기간창 안보이기
			document.getElementById("typely").style.display = "none";
			document.getElementById("keyword").style.display = "inline";
		}

		/* 한페이지에 보여줄 레코드 수 조회 후 선택한 값 그대로 유지하기 위한
		설정 */
		if ("<c:out value='${parentData.pageSize}' />" != "") {
			$("#pageSize").val("<c:out value='${parentData.pageSize}' />");
		}

		/* 검색 대상이 변경될 때마다 처리 이벤트 */
		$("#search").change(function() {
			if ($("#search").val() == "all") {
				$("#keyword").val("전체 데이터 조회합니다.");
			} else if ($("#search").val() == "parent_registerdate") {

			} else {
				$("#keyword").val("");
				$("#keyword").focus();
			}
		});

		$("#parent_status").change(function() {
			goPage(1);
		});

		/* 검색 버튼 클릭 시 처리 이벤트 */
		$("#searchData").click(function() {
			if ($("#search").val() != "all" && $("#search").val() != "parent_registerdate") {
				if (!chkSubmit($('#keyword'), "검색어를")) {
					return;
				}
			}
			goPage(1);
		});

		/* 한 페이지에 보여줄 레코드 수 변경될 때마다 처리 이벤트 */
		$("#pageSize").change(function() {
			goPage(1);
		});

	/* 제목 클릭시 상세 페이지 이동을 위한 처리 이벤트 */
	/* $(".goDetail").click(function() {
		var notice_no = $(this).parents("tr").attr("data-num");
		$("#teacher_no").val(teacher_no);
		console.log("글번호 : " + teacher_no);
		// 상세 페이지로 이동하기 위해 form 추가(id : detailForm)
		$("#detailForm").attr({
			"method" : "get",
			"action" : "/admin/notice/noticeDetail"
		});
		$("#detailForm").submit();
	}); */
	});

	/* 검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징을 위한 실질적인 처리 함수 */
	function goPage(page) {
		if ($("#search").val() == "all") {
			$("#keyword").val("");
		}
		$("#page").val(page);
		$("#p_search").attr({
			"method" : "get",
			"action" : "/admin/member/parentList"
		});
		$("#p_search").submit();
	}
	function jsChselect(value) {
		if (value == "parent_registerdate") {
			// 기간 선택창 보이기
			document.getElementById("typely").style.display = "inline";
			document.getElementById("keyword").style.display = "none";
		} else {
			// 기간창 안보이기
			document.getElementById("typely").style.display = "none";
			document.getElementById("keyword").style.display = "inline";
		}
	}
</script>
</head>
<body>
	<div class="contentContainer container-fluid" id="payment">
		<div class="contentTit">
			<h3>학부모 리스트</h3>
		</div>
		<%-- ======= 상세 페이지 이동을 위한 FORM ======== --%>
		<form name="detailForm" id="detailForm">
			<input type="hidden" name="parent_no" id="parent_no"> <input
				type="hidden" name="page" value="${parentData.page }"> <input
				type="hidden" name="pageSize" value="${parentData.pageSize }">
		</form>
		<%-- =============== 검색기능 시작 =============== --%>
		<div id="paymentSearch">
			<form id="p_search" name="p_search">
				<input type="hidden" id="page" name="page"
					value="${parentData.page }">
				<table summary="검색">
					<tr>
						<td id="btd1"><label>검색조건</label> <select id="search"
							name="search" onchange="jsChselect(this.value)">
								<option value="all">전체</option>
								<option value="parent_name">학부모명</option>
								<option value="parent_registerdate">가입날짜</option>
						</select> <input type="text" name="keyword" id="keyword"
							value="검색어를 입력하세요.">
							<div id="typely" style="display: none">
								<input type="text" id="start_date" name="start_date"> <input
									type="text" id="end_date" name="end_date">
							</div> <input type="button" value="검색" id="searchData"></td>
						<td id="btd3">회원상태<select id="parent_status"
							name="parent_status">
								<option value="all">전체</option>
								<option value="join">가입</option>
								<option value="break">탈퇴</option>
						</select></td>
						<td id="btd2">한페이지에 <select id="pageSize" name="pageSize">
								<option value="1">1줄</option>
								<option value="2">2줄</option>
								<option value="3">3줄</option>
								<option value="5">5줄</option>
								<option value="7">7줄</option>
								<option value="10">10줄</option>
						</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%-- =============== 검색기능 종료 =============== --%>

		<%-- =============== 리스트 시작 ================= --%>
		<div id="paymentList" class="container-fluid">
			<table summary="학부모 리스트"
				class="table-striped table-hover table-bordered mg-top-sm table">
				<thead>
					<tr>
						<th class="order" data-value="parent_no">회원번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>연락처</th>
						<th data-value="parent_registerdate">가입날짜</th>
						<th>주소</th>
						<th>회원상태</th>
					</tr>
				</thead>
				<tbody id="list">
					<!-- 데이터 출력 -->
					<c:choose>
						<c:when test="${not empty parentList}">
							<c:forEach var="parent" items="${parentList}" varStatus="status">
								<tr class="tac" data-num="${parent.parent_no}">
									<td>${parent.parent_no }</td>
									<td>${parent.userId }</td>
									<td>${parent.parent_name}</td>
									<td>${parent.parent_phone}</td>
									<td>${parent.parent_registerdate}</td>
									<td>${parent.parent_address}</td>
									<td>${parent.parent_status}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="7" class="tac">등록된 게시 물이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<%-- =============== 리스트 종료 ===============--%>

		<%-- ============ 페이지 네비게이션 시작 =========== --%>
		<div id="paymentPage">
			<tag:paging page="${param.page }" total="${total }"
				list_size="${parentData.pageSize }">
			</tag:paging>
		</div>
		<%-- ============ 페이지 네비게이션 종료 =========== --%>
	</div>
	<script type="text/javascript">
		$("#start_date").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			altFormat : "yy-mm-dd",
		});
		$("#start_date").attr({
			"readonly" : "readonly"
		});
		$("#end_date").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			altFormat : "yy-mm-dd",
		});
		$("#end_date").attr({
			"readonly" : "readonly"
		});
		$("#start_date").val($.datepicker.formatDate('yy-mm-dd', new Date()));
		$("#end_date").val($.datepicker.formatDate('yy-mm-dd', new Date()));
	</script>
</body>
</html>