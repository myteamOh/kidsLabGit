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
<script type="text/javascript"
	src="/resources/include/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	var butChk = 0; // 수정버튼과 삭제버튼을 구별하기 위한 변수
	$(function() {
		/* 수정 버튼 클릭 시 처리 이벤트 */
		$("#updateBtn").click(function() {
			$("#faq_content").val(CKEDITOR.instances.faq_content.getData());
			if (!chkSubmit($('#faq_content'), "작성할 내용을")) {
				return;
			} else if (!chkSubmit($('#faq_title'), "제목을")) {
				return;
			} else {
				$("#f_writeForm").attr({
					"method" : "POST",
					"action" : "/admin/faq/faqUpdate"
				});
				$("#f_writeForm").submit();
			}
		});

		/* 목록 버튼 클릭 시 처리 이벤트 */
		$("#faqListBtn").click(function() {
			location.href = "/admin/faq/faqList";
		});
	});
</script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>FAQ</h3>
		</div>
		<form name="f_writeForm" id="f_writeForm"
			enctype="multipart/form-data">
			<input type="hidden" name="faq_no" value="${updateData.faq_no}">
			<input type="hidden" name="admin_no" value="${adminLogin.userNo}">
			<input type="hidden" name="page" id="page" value="${param.page }">
			<input type="hidden" name="pageSize" id="pageSize"
				value="${param.pageSize }">


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
							<td class="ac">글번호</td>
							<td>${updateData.faq_no }</td>
							<td class="ac">글종류</td>
							<td><input type="text" id="faq_type" name="faq_type"
								value="${updateData.faq_type }" readonly="readonly"></td>
						</tr>
						<tr>
							<td class="ac">글제목</td>
							<td colspan="3"><input type="text" id="faq_title"
								name="faq_title" value="${updateData.faq_title }"></td>
						</tr>
						<tr>
							<td class="ac vm">내용</td>
							<td colspan="3"><textarea id="faq_content"
									name="faq_content">${updateData.faq_content }</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="contentBtn">
			<input type="button" value="수정" id="updateBtn"> <input
				type="button" value="글목록" id="faqListBtn">
		</div>
	</div>
	<script type="text/javascript">
		CKEDITOR.config.language = 'english';
		CKEDITOR.replace('faq_content');
		$(function() {
			CKEDITOR.replace('faq_content', {
				customConfig : "/resources/include/ckeditor/config.js"
			});
		});
	</script>
</body>
</html>