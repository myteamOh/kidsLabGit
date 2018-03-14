<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 작성</title>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css" />
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
<script type="text/javascript"
	src="/resources/include/ckeditor/ckeditor.js"></script>
<script type="text/javascript">

	$(function() {
		//저장 버튼 클릭시 처리 이벤트
		$("#boardInsertBtn").click(function() {
			// 입력값 체크
			$("#faq_content").val(CKEDITOR.instances.faq_content.getData());
			if (!chkSubmit($('#faq_content'), "작성할 내용을")) {
				return;
			} else if (!chkSubmit($('#faq_title'), "제목을")) {
				return;
			} else if (!chkSubmit($('#faq_type'), "종류를")) {
				return;
			} else {
				$("#f_writeForm").attr({
					"method" : "POST",
					"action" : "/admin/faq/faqInsert"
				});
				$("#f_writeForm").submit();
			}
		});

		//목록버튼클릭시 처리 이벤트
		$("#boardListBtn").click(function() {
			location.href = "/admin/faq/faqList";
		});
	});
</script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>게시판 글작성</h3>
		</div>
		<div class="contentTB">
			<form id="f_writeForm" name="f_writeForm"
				enctype="multipart/form-data">
				<table id="boardWrite">
					<colgroup>
						<col width="17%" />
						<col width="83%" />
					</colgroup>
					<tr>
						<td class="ac">작성자</td>
						<td>관리자</td>
						<td><input type="hidden" id="admin_no" name="admin_no"
							value="${adminLogin.userNo }"></td>
					</tr>
					<tr>
						<td class="ac">글제목</td>
						<td><input type="text" name="faq_title" id="faq_title"></td>
					</tr>
					<tr>
						<td class="ac">글종류</td>
						<td><input type="text" name="faq_type" id="faq_type"></td>
					</tr>
					<tr>
						<td class="ac vm">내용</td>
						<td><textarea name="faq_content" id="faq_content"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="contentBtn">
			<input type="button" value="저장" class="but" id="boardInsertBtn">
			<input type="button" value="목록" class="but" id="boardListBtn">
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