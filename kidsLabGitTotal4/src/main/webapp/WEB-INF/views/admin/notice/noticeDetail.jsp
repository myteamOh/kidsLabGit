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

	/* 수정 버튼 클릭 시 처리 이벤트 */
	$("#updateFormBtn").click(function() {
		butChk = 1;
	});

	// 제거
	/* 삭제 버튼 클릭 시 처리 이벤트 */
	$("#boardDeleteBtn").click(function() {
		butChk = 2;
	});

	/* 목록 버튼 클릭 시 처리 이벤트 */
	$("#boardListBtn").click(function() {
		location.href = "/admin/notice/noticeList";
	});
	function checkBtn() {
		$.ajax({
			url : "/admin/notice/updateForm",
			type : "get",
			data : $("#f_data").serialize(), // 폼전체 데이터전송
			dataType : "text",
			error : function() {
				// 오류 발생 시
				alert("시스템 오류");
			},
			success : function() {
				if (butChk == 1) {
					goUrl = "/admin/notice/updateForm";
				} else if (butChk == 2) {
					goUrl = "/admin/notice/noticeDelete";
				}
				$("#f_data").attr("action", goUrl);
				$("#f_data").submit();
			}
		});
	}
</script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>게시판 상세보기</h3>
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
	</div>
</body>
</html>