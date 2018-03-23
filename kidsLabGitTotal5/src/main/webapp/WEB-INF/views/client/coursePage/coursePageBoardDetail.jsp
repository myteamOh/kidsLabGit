<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의페이지 게시판 글상세보기</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<!-- <script type="text/javascript" src="/resources/include/js/coursePage.js"></script> -->

</head>
<body>

	<!-- 수정또는 삭제를 위한 폼 -->
	<form id="updateForm">
		<input type="hidden" name="coursedata_no"
			value="${detail.coursedata_no}">
	</form>

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
						<td>${detail.coursedata_status}</td>
					</tr>

					<tr>
						<td>작성자</td>
						<td>${detail.coursedata_writer}</td>
						<td>작성일</td>
						<td>${detail.coursedata_registerdate}</td>
					</tr>

					<tr>
						<td>제목</td>
						<td colspan="3">${detail.coursedata_title}</td>
					</tr>

					<c:if test="${not empty detail.coursedata_file}">
						<tr>
							<td>파일첨부</td>
							<td colspan="3" class="downloadFile"><a
								href="/coursepage/download?coursedata_file=${detail.coursedata_file }">${detail.coursedata_file}</a></td>
						</tr>
					</c:if>

					<tr>
						<td>내용</td>
						<td colspan="3">${detail.coursedata_content}</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 작성자와 로그인정보(이름,아이디)가 일치시 수정, 삭제 버튼 활성화 -->
		<c:if test="${detail.coursedata_writer eq loginInfo}">
			<div>
				<input type="button" class="updateBtn" value="수정"> <input
					type="button" class="deleteBtn" value="삭제">
			</div>
		</c:if>
		<!-- 버튼영역끝 -->
	</section>

</body>
</html>