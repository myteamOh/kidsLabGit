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
			$("#notice_content").val(CKEDITOR.instances.notice_content.getData());
			if (!chkSubmit($('#notice_content'), "작성할 내용을")) {
				return;
			} else if (!chkSubmit($('#notice_title'), "제목을")) {
				return;
			} else {
				$("#n_writeForm").attr({
					"method" : "POST",
					"action" : "/admin/notice/noticeUpdate"
				});
				$("#n_writeForm").submit();
			}
		});

		/* 목록 버튼 클릭 시 처리 이벤트 */
		$("#noticeListBtn").click(function() {
			location.href = "/admin/notice/noticeList";
		});
	});
</script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>공지사항</h3>
		</div>
		<form name="n_writeForm" id="n_writeForm"
			enctype="multipart/form-data">
			<input type="hidden" name="notice_no" value="${updateData.notice_no}">
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
							<td>${updateData.notice_no }</td>
							<td class="ac">작성일</td>
							<td>${updateData.notice_registerdate}</td>
						</tr>
						<tr>
							<td class="ac">글제목</td>
							<td colspan="3"><input type="text" id="notice_title"
								name="notice_title" value="${updateData.notice_title }">
							</td>
						</tr>
						<tr>
							<td class="ac vm">내용</td>
							<td colspan="3"><textarea id="notice_content"
									name="notice_content">${updateData.notice_content }</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="contentBtn">
			<input type="button" value="수정" id="updateBtn"> <input
				type="button" value="글목록" id="noticeListBtn">
		</div>
	</div>
	<script type="text/javascript">
		CKEDITOR.config.language = 'english';
		CKEDITOR.replace('notice_content');
		$(function() {
			CKEDITOR.replace('notice_content', {
				customConfig : "/resources/include/ckeditor/config.js"
			});
		});
	</script>
</body>
</html>