<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 수정</title>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css" />
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		var value = "${updateData.gallery_file}";
		if (value != "") {
			var img = $("<img>");
			$('#imgView').hover(function() {
				img.attr({
					src : "/uploadStorage/gallery/${updateData.gallery_file}",
					width : "450px",
					height : "200px"
				});
				img.addClass("imgViewData");
				$('#imgArea').append(img);
			}, function() {
				img.remove();
			});
		} else {
			$('#imgView').hide();
		}

		/*  수정  버튼  클릭  시  처리  이벤트 */
		$("#boardUpdateBtn").click(function() {
			//입력값  체크
			if (!chkSubmit($('#gallery_title'), "제목을"))
				return;
			else if (!chkSubmit($('#gallery_content'), "작성할 내용을"))
				return;
			else {
				$("#f_writeForm").attr({
					"method" : "POST",
					"action" : "/teacher/gallery/galleryUpdate"
				});
				$("#f_writeForm").submit();
			}
		});

		/* 목록 버튼 클릭 시 처리 이벤트 */
		$("#boardListBtn").click(function() {
			location.href = "/teacher/gallery/galleryList";
		});
	});
</script>
</head>
<body>

	<%-- ======== 버튼 나타내기 비교 FORM ============ --%>
	<form name ="">
	
	</form>
	
	
	<div class="contentContainer">
		<div class="contentTit">
			<h3>갤러리 수정</h3>
		</div>

		<div class="contentTB">
			<form id="f_writeForm" name="f_writeForm">
				<input type="hidden" id="gallery_no" name="gallery_no"
					value="${updateData.gallery_no}" />
				<table>
					<colgroup>
						<col width="17%" />
						<col width="33%" />
						<col width="17%" />
						<col width="33%" />
					</colgroup>
					<tbody>
						<tr>
							<td class="ac">글번호</td>
							<td>${updateData.gallery_no}</td>
							<td class="ac">작성일</td>
							<td>${updateData.gallery_registerdate}</td>
						</tr>
						<tr>
							<td class="ac">작성자</td>
							<td colspan="3"><input style=""
								value="${updateData.teacher_no}" readonly="readonly" /></td>
						</tr>
						<tr>
							<td class="ac">글제목</td>
							<td colspan="3"><input type="text" name="gallery_title"
								id="gallery_title" value="${updateData.gallery_title}" /></td>
						</tr>
						<tr>
							<td class="ac vm">내용</td>
							<td colspan="3"><textarea name="gallery_content"
									id="gallery_content">${updateData.gallery_content}</textarea></td>
						</tr>
						<tr>
							<td class="ac">첨부파일</td>
							<td colspan="3"><input type="file" name="file" id="file">
								<span id="imgView">기존 이미지파일명: ${updateData.gallery_file}<span
									id="imgArea"> </span></span></td>
						</tr>


					</tbody>
				</table>
			</form>
		</div>
		<div class="contentBtn">
			<input type="button" value="수정" id="boardUpdateBtn"> <input
				type="button" value="목록" id="boardListBtn">
		</div>
	</div>

</body>
</html>