<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>학생 리스트</title>
<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/include/dist/css/dashboard.css" rel="stylesheet">
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
<script
	src="/resources/include/dist/assets/js/ie-emulation-modes-warning.js"></script>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/board.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#excelDownBtn").click(function() {
			alert("excel버튼 클릭")
			$("#f_search").attr({
				"method" : "get",
				"action" : "/teacher/student/studentExcel.do"
			});
			$("#f_search").submit();
		});
	});
</script>
</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>학생 리스트</h3>
		</div>
		<%-- =============== 검색기능 종료 =============== --%>
		<form id="f_search">
			<div id="boardSearch">
				<input type="button" id="excelDownBtn" value="Excel"> <input
					type="hidden" name="course_no" id="course_no" value="${course_no }">
			</div>
		</form>
		<%-- =============== 리스트 시작 ================= --%>
		<div id="boardList">
			<table summary="학생 리스트">
				<thead>
					<tr>
						<th class="order" data-value="student_no">회원번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>성별</th>
						<th>학교</th>
						<th>생일</th>
						<th data-value="student_registerdate">가입날짜</th>
						<th>학부모명</th>
						<th>연락처</th>
						<th>회원상태</th>
					</tr>
				</thead>
				<tbody id="list">
					<!-- 데이터 출력 -->
					<c:choose>
						<c:when test="${not empty courseStudentList}">
							<c:forEach var="student" items="${courseStudentList}"
								varStatus="status">
								<tr class="tac" data-num="${student.student_no}">
									<td>${student.student_no }</td>
									<td>${student.userId }</td>
									<td>${student.student_name}</td>
									<td>${student.student_gender}</td>
									<td>${student.student_school}</td>
									<td>${student.student_birthday}</td>
									<td>${student.student_registerdate}</td>
									<td>${student.parent_name}</td>
									<td>${student.parent_phone}</td>
									<td>${student.student_status}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="10" class="tac">등록된 게시 물이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<%-- =============== 리스트 종료 ===============--%>

	</div>
</body>
</html>