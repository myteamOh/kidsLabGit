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
	var butChk = 0; // 수정버튼과 삭제버튼을 구별하기 위한 변수
	$(function() {
		/* 수정 버튼 클릭 시 처리 이벤트 */
		$("#updateBtn").click(function() {
			$("#f_data").attr({
				"method" : "get",
				"action" : "/admin/notice/updateForm"
			});
			$("#f_data").submit();
		});

		// 제거
		/* 삭제 버튼 클릭 시 처리 이벤트 */
		$("#deleteBtn").click(function() {
			if (confirm("정말로 삭제 하시겠습니까?") == true) {
				$("#f_data").attr({
					"method" : "post",
					"action" : "/admin/notice/noticeDelete"
				});
				$("#f_data").submit();
			} else {
				return;
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
		<form name="f_data" id="f_data" method="post">
			<input type="hidden" name="notice_no" value="${detail.notice_no}">
			<input type="hidden" name="admin_no" value="${detail.admin_no}">
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
						<td class="ac">작성일</td>
						<td>${detail.notice_registerdate}</td>
					</tr>
					<tr>
						<td class="ac">제목</td>
						<td colspan="3">${detail.notice_title}</td>
					</tr>
					<tr>
						<td class="ac vm">내용</td>
						<td colspan="3">${detail.notice_content}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="contentBtn">
			<input type="button" value="수정" id="updateBtn"> <input
				type="button" value="삭제" id="deleteBtn"> <input
				type="button" value="글목록" id="noticeListBtn">
		</div>
	</div>
</body>
</html>