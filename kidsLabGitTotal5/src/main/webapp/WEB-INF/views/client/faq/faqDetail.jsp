<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세 보기</title>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css">
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript">
	$(function() {

		/* 목록 버튼 클릭 시 처리 이벤트 */
		$("#faqListBtn").click(function() {
			location.href = "/client/faq/faqList";
		});
	});
</script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>게시판 상세보기</h3>
		</div>
		<form name="f_data" id="f_data" method="post">
			<input type="hidden" name="faq_no" value="${detail.faq_no}">
			<input type="hidden" name="admin_no" value="${detail.admin_no}">
			<input type="hidden" name="page" id="page" value="${param.page }">
			<input type="hidden" name="pageSize" id="pageSize"
				value="${param.pageSize }">
		</form>

		<!-- ============= 상세 정보 보여주기 시작 =========== -->
		<div class="contentTB">
			<table>
				<colgroup>
					<col width="17%">
					<col width="33%">
					<col width="17%">
					<col width="33%">
				</colgroup>
				<tbody>
					<tr>
						<td class="ac">작성자</td>
						<td>관리자</td>
						<td class="ac">글종류</td>
						<td>${detail.faq_type}</td>
					</tr>
					<tr>
						<td class="ac">제목</td>
						<td colspan="3">${detail.faq_title}</td>
					</tr>
					<tr>
						<td class="ac vm">내용</td>
						<td colspan="3">${detail.faq_content}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="contentBtn">
			<input type="button" value="글목록" id="faqListBtn">
		</div>
	</div>
</body>
</html>