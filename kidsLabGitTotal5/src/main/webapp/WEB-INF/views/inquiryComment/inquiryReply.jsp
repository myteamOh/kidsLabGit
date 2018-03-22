<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>댓글</title>

<link rel="stylesheet" type="text/css"
	href="/resources/include/css/reply.css" />

<!-- jQuery Framework 참조하기 -->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		/* 기본 덧글 목록 불러오기 */
		var inquiry_no = "<c:out value='${idto.inquiry_no}' />";
		listAll(inquiry_no);
		//리스트 요청 함수
		function listAll(inquiry_no) {
			$("#comment_list").html("");
			var url = "/admin/replies/all/" + inquiry_no + ".do";
			$.getJSON(url, function(data) {
				console.log(data.length);

				$(data).each(function() {
					var inquiry_reply_no = this.inquiry_reply_no;
					var inquiry_reply_content = this.inquiry_reply_content;
					var inquiry_reply_registerdate = this.inquiry_reply_registerdate;
					addNewItem(inquiry_reply_no, inquiry_reply_content, inquiry_reply_registerdate);
				});
			}).fail(function() {
				alert("덧글 목록을 불러오는데 실패하였습니다. 잠시후에 다시 시도해 주세요.");
			});
		}
		/* 새로운 글을 화면에 추가하기 위한 함수 */
		function addNewItem(inquiry_reply_no, inquiry_reply_content, inquiry_reply_registerdate) {
			// 새로운 글이 추가될 li태그 객체
			var new_li = $("<li>");
			new_li.attr("data-num", inquiry_reply_no);
			new_li.addClass("comment_item");

			// 작성자 정보가 지정될 <p>태그
			var writer_p = $("<p>");
			writer_p.addClass("writer");

			// 작성자 정보의 이름
			var name_span = $("<span>");
			name_span.addClass("name");
			name_span.html("관리자");

			// 작성일시
			var date_span = $("<span>");
			date_span.html(" / " + inquiry_reply_registerdate + " ");

			// 내용
			var content_p = $("<p>");
			content_p.addClass("con");
			content_p.html(inquiry_reply_content);

			// 조립하기
			writer_p.append(name_span).append(date_span);
			new_li.append(writer_p).append(content_p);
			$("#comment_list").append(new_li);
		}
	});
</script>
</head>
<body>
	<div id="replyContainer">
		<ul id="comment_list">
			<!-- 여기에 동적 생성 요소가 들어가게 됩니다. -->
		</ul>
	</div>
</body>
</html>

