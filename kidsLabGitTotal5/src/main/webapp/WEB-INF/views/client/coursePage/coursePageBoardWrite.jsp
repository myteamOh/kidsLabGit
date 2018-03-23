<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의페이지 게시판 글쓰기</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<!-- <script type="text/javascript" src="/resources/include/js/coursePage.js"></script> -->

</head>
<body>
	<form id="writeForm" enctype="multipart/form-data">
		<input type="hidden" name="course_no" value="${cNum}"> <input
			type="hidden" name="student_no" value="${Login.student_no}">

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
							<td><select id="statusSelector" name="coursedata_status">
									<c:choose>
										<c:when test="${not empty teacherLogin}">
											<option value="공지사항" selected="selected">공지사항</option>
											<option value="자료실">자료실</option>
										</c:when>
										<c:otherwise>
											<option value="과제" selected="selected">과제</option>
										</c:otherwise>
									</c:choose>
							</select></td>

							<td>작성자</td>
							<td><c:choose>
									<c:when test="${not empty teacherLogin}">
										<input type="text" id="writer" name="coursedata_writer"
											value="${teacherLogin.teacher_name}(${teacher_id})"
											readonly="readonly">
									</c:when>
									<c:otherwise>
										<input type="text" id="writer" name="coursedata_writer"
											value="${Login.userName}(${Login.userId})"
											readonly="readonly">
									</c:otherwise>
								</c:choose></td>
						</tr>

						<tr>
							<td>제목</td>
							<td colspan="3"><input type="text" id="title"
								name="coursedata_title"></td>
						</tr>

						<tr>
							<td>파일첨부</td>
							<td colspan="3"><input type="file" id="file" name="file"></td>
						</tr>

						<tr>
							<td>내용</td>
							<td colspan="3"><textarea cols="80" rows="15" id="content"
									name="coursedata_content"></textarea></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div>
				<input type="button" class="insertBtn" value="확인">
			</div>
		</section>
	</form>

</body>
</html>