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
<title>1:1 문의 게시글 목록</title>
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
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		/* 글쓰기 버튼 클릭 시 처리 이벤트 */
		$("#insertFormBtn").click(function() {
			location.href = "/inquiry/inquiryWriteForm";
		});

		/* 제목 클릭시 상세 페이지 이동을 위한 처리 이벤트 */
		$(".goDetail").click(function() {
			var inquiry_no = $(this).parents("tr").attr("data-num");
			$("#inquiry_no").val(inquiry_no);
			console.log("글번호 : " + inquiry_no);
			//상세 페이지로 이동하기 위해 form추가 (id : detailForm)
			$("#detailForm").attr({
				"method" : "get",
				"action" : "/inquiry/inquiryDetail"
			});
			$("#detailForm").submit();
		});
	});
</script>


</head>
<body>
	<div class="contentContainer">



		<div class="contentTit">
			<h3>1:1 문의글 리스트</h3>
		</div>

		<%-- ======== 상세 페이지 이동을 위한 FORM ============ --%>
		<form name="detailForm" id="detailForm">
			<input type="hidden" name="inquiry_no" id="inquiry_no">
		</form>

		<%-- ======== 검색용 ============ --%>
		<form name="parentlink" id="detailForm">
			<input type="hidden" name="parent_no" id="parent_no"
				value="${Login.parent_no}">
		</form>

	
		<div align="right">
			<label>※1:1문의는 회원만 이용하실 수 있습니다.</label>
		</div>
		<%-- ================= 리스트 시작 =============== --%>
		<div id="inquiryList">
			<table summary="게시판 리스트">
					
				
				<colgroup>
					<col width="10%" />
					<col width="72%" />
					<col width="15%" />
					<col width="3" />
				</colgroup>
				<thead>
					<tr>
						<th class="order">번호</th>
						<th>제목</th>
						<th data-value="inquiry_registerdate" class="order">등록일</th>
					</tr>
				</thead>
				<tbody id="list">
					<!--  데이터  출력 -->
					<!-- 게시물이 없다면 입력된 문장 출력하게 함 -->
					<c:choose>
						<c:when test="${not empty inquiryList}">
							<c:forEach var="inquiry" items="${inquiryList}"
								varStatus="status">
								<tr class="tac" data-num="${inquiry.inquiry_no}">
									<td>${inquiry.inquiry_no}</td>
									<td class="goDetail tal">${inquiry.inquiry_title}</td>
									<td>${inquiry.inquiry_registerdate}</td>
									<td style="display:none">${inquiry.parent_no}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="3" class="tac">등록된 게시물이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>


				</tbody>
			</table>
		</div>
		<%-- ================= 리스트 종료 ================ --%>

		<%-- ============== 글쓰기 버튼 출력 시작============ --%>
		<div class="contentBtn">
			<input type="button" value="글쓰기" id="insertFormBtn">
		</div>
		<%-- ============== 글쓰기 버튼 출력 종료============ --%>



	</div>
</body>
</html>