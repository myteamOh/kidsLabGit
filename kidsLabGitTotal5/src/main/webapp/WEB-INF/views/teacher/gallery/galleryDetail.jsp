<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 상세 보기</title>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css" />
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript">
	var butChk = 0; // 수정버튼과 삭제버튼을 구별하기 위한 변수

	var sel_file;
	$(document).ready(function() {
		$("#file").on("change", handleImgFileSelect);
		$("#file").on("change", function() {
			console.log($("#file").val());
		});
	});
	// 이미지 확인
	function handleImgFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("확장자는 `이미지 확장자만 가능합니다.");
				return;
			}

			sel_file = f;

			var reader = new FileReader();
			reader.onload = function(e) {
				$("#img").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}
	$(function() {
		/* 첨부파일 이미지 보여주기 위한 속성 추가 */
		/* 		var file = "<c:out value = '${detail.gallery_file}' />";
		 if (file != "") {
		 $("#fileImage").attr({
		 src : "/uploadStorage/gallery/${detail.gallery_file}",
		 width : "450px",
		 height : "200px"
		 });

		 } */

		/* 수정 버튼 클릭 시 처리 이벤트 */
		$("#updateBtn").click(function() {
			$("#f_data").attr({
				"method" : "get",
				"action" : "/teacher/gallery/galleryUpdateForm"
			});
			$("#f_data").submit();
		});

		/* 목록 버튼 클릭 시 처리 이벤트 */
		$("#boardListBtn").click(function() {
			location.href = "/teacher/gallery/galleryList";
		});

		/* 삭제 버튼 클릭 시 처리 이벤트 */
		$("#deleteBtn").click(function() {
			if (confirm("정말로 삭제 하시겠습니까?") == true) {
				$("#f_data").attr({
					"method" : "post",
					"action" : "/teacher/gallery/galleryDelete"
				});
				$("#f_data").submit();
			} else {
				return;
			}
		});

	});
</script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>게시판 상세보기</h3>
		</div>
		<form name="f_data" id="f_data" method="POST">
			<input type="hidden" name="gallery_no" value="${detail.gallery_no}" />
			<input type="hidden" name="page" id="page" value="${param.page}" />
			<input type="hidden" name="pageSize" id="pageSize"
				value="${param.pageSize}" /> <input type="hidden"
				name="gallery_file" id="gallery_file" value="${detail.gallery_file}" />
		</form>
		<div class="contentTB">
			<table>
				<colgroup>
					<col width="17%" />
					<col width="33%" />
					<col width="17%" />
					<col width="33%" />
				</colgroup>
				<tbody>
					<tr>
						<td class="ac">작성자</td>
						<td><input style="" value="${detail.teacher_no}"
							readonly="readonly"></td>
						<td class="ac">작성일</td>
						<td>${detail.gallery_registerdate}</td>
					</tr>
					<tr>
						<td class="ac">제목</td>
						<td colspan="3">${detail.gallery_title}</td>
					</tr>
					<tr>
						<td class="ac vm">내용</td>
						<td colspan="3">${detail.gallery_content}</td>
					</tr>
					<c:if test="${detail.gallery_file !=''}">
						<tr>
							<td class="ac vm">첨부파일</td>
							<td colspan="3"><div class="img_wrap">
									<img id="img"
										src="/uploadStorage/gallery/${detail.gallery_file}" />
								</div></td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<%-- =============== 상세 정보 보여주기 종료 ============ --%>
		<div>
			<input type="button" value="목록" id="boardListBtn">
			<c:if test="${teacherLogin.teacher_no == detail.teacher_no}">
				<input type="button" value="수정" id="updateBtn">
				<input type="button" value="삭제" id="deleteBtn">
			</c:if>
		</div>
	</div>
</body>
</html>