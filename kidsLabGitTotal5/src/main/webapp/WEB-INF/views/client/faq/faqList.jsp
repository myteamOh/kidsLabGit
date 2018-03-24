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
<title>FAQ 목록</title>
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
<script type="text/javascript">
	$(function() {
		/* 검색 후 검색 대상과 검색 단어 출력 */
		var word = "<c:out value='${faqData.keyword}' />";
		var value = "";
		if (word != "") {
			$("#keyword").val("<c:out value='${faqData.keyword}' />");
			$("#search").val("<c:out value='${faqData.search}' />");
		}

		/* 한페이지에 보여줄 레코드 수 조회 후 선택한 값 그대로 유지하기 위한
		설정 */
		if ("<c:out value='${faqData.pageSize}' />" != "") {
			$("#pageSize").val("<c:out value='${faqData.pageSize}' />");
		}

		/* 검색 대상이 변경될 때마다 처리 이벤트 */
		$("#search").change(function() {
			if ($("#search").val() == "all") {
				$("#keyword").val("전체 데이터 조회합니다.");
			} else {
				$("#keyword").val("");
				$("#keyword").focus();
			}
		});

		/* 검색 버튼 클릭 시 처리 이벤트 */
		$("#searchData").click(function() {
			if ($("#search").val() != "all") {
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
		$(".goDetail").click(function() {
			var notice_no = $(this).parents("tr").attr("data-num");
			$("#faq_no").val(notice_no);
			console.log("글번호 : " + notice_no);
			// 상세 페이지로 이동하기 위해 form 추가(id : detailForm)
			$("#detailForm").attr({
				"method" : "GET",
				"action" : "/client/faq/faqDetail"
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
		$("#f_search").attr({
			"method" : "get",
			"action" : "/client/faq/faqList"
		});
		$("#f_search").submit();
	}
</script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>게시판 리스트</h3>
		</div>
		<%-- ======= 상세 페이지 이동을 위한 FORM ======== --%>
		<form name="detailForm" id="detailForm">
			<input type="hidden" name="faq_no" id="faq_no"> <input
				type="hidden" name="page" value="${faqData.page }"> <input
				type="hidden" name="pageSize" value="${faqData.pageSize }">
		</form>
		<%-- =============== 검색기능 시작 =============== --%>
		<div id="boardSearch">
			<form id="f_search" name="f_search">
				<input type="hidden" id="page" name="page" value="${faqData.page }">
				<table summary="검색">
					<colgroup>
						<col width="70%">
						<col width="30%">
					</colgroup>
					<tr>
						<td id="btd1"><label>검색조건</label> <select id="search"
							name="search">
								<option value="all">전체</option>
								<option value="faq_title">제목</option>
								<option value="faq_content">내용</option>
								<option value="faq_type">종류</option>
						</select> <input type="text" name="keyword" id="keyword"
							value="검색어를 입력하세요."> <input type="button" value="검색"
							id="searchData"></td>
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
		<div id="boardList">
			<table summary="공지사항 리스트">
				<colgroup>
					<col width="10%" />
					<col width="62%" />
					<col width="38%" />
				</colgroup>
				<thead>
					<tr>
						<th class="order" data-value="faq_no">글번호</th>
						<th>글제목</th>
						<th>글 종류</th>
					</tr>
				</thead>
				<tbody id="list">
					<!-- 데이터 출력 -->
					<c:choose>
						<c:when test="${not empty faqList}">
							<c:forEach var="faq" items="${faqList}" varStatus="status">
								<tr class="tac" data-num="${faq.faq_no}">
									<td>${faq.faq_no }</td>
									<td class="goDetail tal">${faq.faq_title}</td>
									<td>${faq.faq_type}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="3" class="tac">등록된 게시 물이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<%-- =============== 리스트 종료 ===============--%>

		<%-- ============ 페이지 네비게이션 시작 =========== --%>
		<div id="boardPage">
			<tag:paging page="${param.page }" total="${total }"
				list_size="${faqData.pageSize }">
			</tag:paging>
		</div>
		<%-- ============ 페이지 네비게이션 종료 =========== --%>
	</div>

</body>
</html>