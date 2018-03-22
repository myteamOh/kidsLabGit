<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 글 상세 보기</title>
<!-- Bootstrap core CSS -->
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css" />

<!-- 모바일 웹 페이지 설정 -->
<!-- <link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png" /> -->
<!-- 모바일 웹 페이지 설정 끝 -->
<!--[if lt IE 9]> <script src="/resources/include/js/html5shiv.js"></script> <![endif]-->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/reply.js"></script>
<script type="text/javascript">
	$(function() {
		$("#replySuccessBtn").click(function() {
			var text = "<c:out value='${detail.inquiry_title}' />";
			var findStr = "[답변완료]";
			if (text.indexOf(findStr) != -1) {
				alert("이미 답변이 완료되었습니다.");
				location.href = "/admin/inquiry/inquiryList";
				return;
			} else {
				$("#i_data").attr({
					"method" : "POST",
					"action" : "/admin/inquiry/inquiryList"
				});
				$("#i_data").submit();
			}
		});
	});

	$(document).ready(function() {

		// 댓글 버튼 클릭 시
		var reply = 0;
		$("#replyBtn").click(function() {
			if (reply == 0) {
				$(".reply").removeClass("hide");
				reply = 1;
			} else {
				$(".reply").addClass("hide");
				reply = 0;
			}
		});

		//목록 버튼클릭시 처리 이벤트
		$("#inquiryListBtn").click(function() {
			location.href = "/admin/inquiry/inquiryList";
		});

		/* 리셋 버튼 클릭시 처리 이벤트 */

		$("#requestReset").click(function() {
			$("#i_detail").each(function() {
				this.reset();
			});
		});

	});
</script>

<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>게시판 상세보기</h3>
		</div>
		<form name="i_data" id="i_data" method="post">
			<input type="hidden" name="inquiry_no" value="${detail.inquiry_no}">
			<input type="hidden" name="inquiry_title"
				value="${detail.inquiry_title }">
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
						<td>${detail.parent_id }</td>
						<td class="ac">작성일</td>
						<td>${detail.inquiry_registerdate}</td>
					</tr>
					<tr>
						<td class="ac">제목</td>
						<td colspan="3">${detail.inquiry_title}</td>
					</tr>
					<tr>
						<td class="ac vm">내용</td>
						<td colspan="3">${detail.inquiry_content}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="contentBtn">
			<input type="button" value="댓글" id="replyBtn"> <input
				type="button" value="답변완료" id="replySuccessBtn"> <input
				type="button" value="글목록" id="inquiryListBtn">
		</div>
	</div>

	<div class="reply hide">
		<jsp:include page="../inquiryComment/inquiryReply.jsp"></jsp:include>
	</div>

	<div>
		<ul id="comment_list">
			<!-- 여기에 동적 생성 요소가 들어가게 됩니다. -->
		</ul>
	</div>

</body>
</html>