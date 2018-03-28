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
<title>공지사항 목록</title>
<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/include/dist/css/dashboard.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/adminPayment.css" />
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
<script type="text/javascript">
	$(function() {
		/* 검색 후 검색 대상과 검색 단어 출력 */
		var word = "<c:out value='${noticeData.keyword}' />";
		var start_date = "<c:out value='${noticeData.start_date}' />";
		var end_date = "<c:out value='${noticeData.end_date}' />";
		var value = "";
		if (word != "") {
			$("#keyword").val("<c:out value='${noticeData.keyword}' />");
			$("#search").val("<c:out value='${noticeData.search}' />");
		}
		if (start_date != "") {
			$("#start_date").val("<c:out value='${noticeData.start_date}' />");
			$("#search").val("<c:out value='${noticeData.search}' />");
		}
		if (end_date != "") {
			$("#end_date").val("<c:out value='${noticeData.end_date}' />");
			$("#search").val("<c:out value='${noticeData.search}' />");
		}
		/* 날짜 선택 후 date 박스 유지*/
		if ($("#search").val() == "notice_registerdate") {
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
		if ("<c:out value='${noticeData.pageSize}' />" != "") {
			$("#pageSize").val("<c:out value='${noticeData.pageSize}' />");
		}

		/* 검색 대상이 변경될 때마다 처리 이벤트 */
		$("#search").change(function() {
			if ($("#search").val() == "all") {
				$("#keyword").val("전체 데이터 조회합니다.");
			} else if ($("#search").val() == "notice_registerdate") {

			} else {
				$("#keyword").val("");
				$("#keyword").focus();
			}
		});

		/* 검색 버튼 클릭 시 처리 이벤트 */
		$("#searchData").click(function() {
			if ($("#search").val() != "all" && $("#search").val() != "notice_registerdate") {
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

		/* 글쓰기 버튼 클릭 시 처리 이벤트 */
		$("#insertFormBtn").click(function() {
			location.href = "/admin/notice/writeForm";
		});

		/* 제목 클릭시 상세 페이지 이동을 위한 처리 이벤트 */
		$(".goDetail").click(function() {
			var notice_no = $(this).parents("tr").attr("data-num");
			$("#notice_no").val(notice_no);
			console.log("글번호 : " + notice_no);
			// 상세 페이지로 이동하기 위해 form 추가(id : detailForm)
			$("#detailForm").attr({
				"method" : "get",
				"action" : "/admin/notice/noticeDetail"
			});
			$("#detailForm").submit();
		});
	});

	/* 검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징을 위한 실질적인 처리 함수 */
	function goPage(page) {
		if ($("#search").val() == "all") {
			$("#keyword").val("");
		}
		$("#page").val(page);
		$("#n_search").attr({
			"method" : "get",
			"action" : "/admin/notice/noticeList"
		});
		$("#n_search").submit();
	}
	function jsChselect(value) {
		if (value == "notice_registerdate") {
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
			<h3>게시판 리스트</h3>
		</div>
		<%-- ======= 상세 페이지 이동을 위한 FORM ======== --%>
		<form name="detailForm" id="detailForm">
			<input type="hidden" name="notice_no" id="notice_no"> <input
				type="hidden" name="page" value="${noticeData.page }"> <input
				type="hidden" name="pageSize" value="${noticeData.pageSize }">
		</form>
		<%-- =============== 검색기능 시작 =============== --%>
		<div id="paymentSearch">
			<form id="n_search" name="n_search">
				<input type="hidden" id="page" name="page"
					value="${noticeData.page }">
				<table summary="검색">
					<colgroup>
						<col width="70%">
						<col width="30%">
					</colgroup>
					<tr>
						<td id="btd1"><label>검색조건</label> <select id="search"
							name="search" onchange="jsChselect(this.value)">
								<option value="all">전체</option>
								<option value="notice_title">제목</option>
								<option value="notice_content">내용</option>
								<option value="notice_registerdate">작성날짜</option>
						</select> <input type="text" name="keyword" id="keyword"
							value="전체 목록을 조회합니다.">
							<div id="typely" style="display: none">
								<input type="text" id="start_date" name="start_date"> <input
									type="text" id="end_date" name="end_date">
							</div> <input type="button" value="검색" id="searchData"></td>
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
			<table summary="공지사항 리스트"
				class="table-striped table-hover table-bordered mg-top-sm table">
				<colgroup>
					<col width="10%" />
					<col width="62%" />
					<col width="15%" />
					<col width="13%" />
				</colgroup>
				<thead>
					<tr>
						<th class="order" data-value="notice_no">글번호</th>
						<th>글제목</th>
						<th data-value="notice_registerdate">작성일</th>
						<th class="borcle">작성자</th>
					</tr>
				</thead>
				<tbody id="list">
					<!-- 데이터 출력 -->
					<c:choose>
						<c:when test="${not empty noticeList}">
							<c:forEach var="notice" items="${noticeList}" varStatus="status">
								<tr class="tac" data-num="${notice.notice_no}">
									<td>${notice.notice_no }</td>
									<td class="goDetail tal">${notice.notice_title}</td>
									<td>${notice.notice_registerdate}</td>
									<td class="name">관리자</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4" class="tac">등록된 게시 물이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<%-- =============== 리스트 종료 ===============--%>

		<%-- =========== 글쓰기 버튼 출력 시작 ==========--%>
		<div class="contentBtn">
			<input type="button" value="글쓰기" id="insertFormBtn">
		</div>
		<%-- ============ 글쓰기 버튼 출력 종료 =========== --%>
		<%-- ============ 페이지 네비게이션 시작 =========== --%>
		<div id="paymentPage">
			<tag:paging page="${param.page }" total="${total }"
				list_size="${noticeData.pageSize }">
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