<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 등록 화면</title>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css" />
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript"
	src="/resources/include/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		/* 저장 버튼 클릭 시 처리 이벤트 */
		$("#boardInsertBtn").click(function() {
			//입력값  체크
			if (!chkSubmit($('#gallery_title'), "제목을"))
				return;
			else if (!chkSubmit($('#gallery_content'), "작성할 내용을"))
				return;
			else {
				
				if($('#file').val()!=""){
					if(!chkFile($('#file'))) return;
				}
				
				$("#f_writeForm").attr({
					"method" : "POST",
					"action" : "/teacher/gallery/galleryInsert"
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
	<div class="contentContainer">
		<div class="contentTit">
			<h3>갤러리 글작성</h3>
		</div>

		<div class="contentTB">
			<form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
				<table id="galleryInsert">
					<colgroup>
						<col width="17%" />
						<col width="83%" />
					</colgroup>
					<tr>
						<td class="ac">작성자</td>
						<td><input type="text" name="teacher_no" id="teacher_no" value="${teacherLogin.teacher_no }" readonly="readonly"></td>
					</tr>
					<tr>
						<td class="ac">프로젝트 제목</td>
						<td><input type="text" name="gallery_title" id="gallery_title"></td>
					</tr>
					<tr>
						<td class="ac vm">내용</td>
						<td><textarea name="gallery_content" id="gallery_content"></textarea></td>
					</tr>
					<tr>
						<td class="ac">첨부파일</td>
						<td><input type="file" name="file" id="file" /></td>
					</tr>		
				</table>
			</form>
		</div>

		<div class="contentBtn">
			<input type="button" value="저장" class="but" id="boardInsertBtn">
			<input type="button" value="목록" class="but" id="boardListBtn">
		</div>
	</div>

</body>
</html>