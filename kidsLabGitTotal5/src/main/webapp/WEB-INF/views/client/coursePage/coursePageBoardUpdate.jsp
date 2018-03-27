<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의페이지 게시판 글수정하기</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<!-- <script type="text/javascript" src="/resources/include/js/coursePage.js"></script> -->

<link type="text/css" rel="stylesheet" href="/resources/include/css/coursePageBoardUpdate.css">

</head>
<body>

	<form id="updateDataForm" enctype="multipart/form-data">
		<input type="hidden" name="coursedata_no"
			value="${updateForm.coursedata_no}">

		<section>

			<div>
				<table>
					<colgroup>
						<col width="17%">
						<col width="33%">
						<col width="17%">
						<col width="33%">
					</colgroup>

					<tbody>
						<tr>
							<td>글 종류</td>
							<td>${updateForm.coursedata_status}</td>
						</tr>

						<tr>
							<td>작성자</td>
							<td>${updateForm.coursedata_writer}</td>
							<td>작성일</td>
							<td>${updateForm.coursedata_registerdate}</td>
						</tr>

						<tr>
							<td>제목</td>
							<td colspan="3"><input type="text" name="coursedata_title" value="${updateForm.coursedata_title}"></td>
						</tr>
						
						<tr>
							<td>파일첨부</td>
							<td colspan="3"><input type="file" id="file" name="file"></td>
						</tr>

						<c:if test="${not empty updateForm.coursedata_file}">
							<tr>
								<td>첨부된파일</td>
								<td colspan="3" class="downloadFile">${updateForm.coursedata_file}</td>
							</tr>
						</c:if>

						<tr>
							<td>내용</td>
							<td colspan="3"><textarea cols="80" rows="15" id="content"
									name="coursedata_content">${updateForm.coursedata_content}</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="btnPart">
				<input type="button" class="updateCompleteBtn" value="수정완료"> <input
					type="button" class="updateCancelBtn" value="취소">
			</div>

		</section>

	</form>

</body>
</html>