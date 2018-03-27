<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 페이지(게시판)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<!-- <script type="text/javascript" src="/resources/include/js/coursePage.js"></script> -->
<style type="text/css">
#searchPage {
	position: relative;
	left: 445px;
}

#searchStatus {
	position: relative;
	right: 445px;
}

#coursepageTable {
	width: 100%;
}
</style>
<link type="text/css" rel="stylesheet"
	href="/resources/include/css/coursePageBoardList.css">
<script type="text/javascript">
	$(function() {
		var coursedata_status = "<c:out value='${courseboardData.coursedata_status}' />";
		// 검색 후 상태 출력
		if (coursedata_status != "") {
			$("#coursedata_status").val("<c:out value='${courseboardData.coursedata_status}' />");
		}
		if ("<c:out value='${courseboardData.pageSize}' />" != "") {
			$("#pageSize").val("<c:out value='${courseboardData.pageSize}' />");
		}
		// 페이지 사이즈 변경 될 때마다 처리 이벤트
		$("#pageSize").change(function() {
			goPage(1);
		});
		// 상태 변경 될 때마다 처리 이벤트
		$("#coursedata_status").change(function() {
			goPage(1);
		});
	})

	function goPage(page) {
		/* if ($("#search").val() == "all") {
			$("#keyword").val("");
		} */
		$("#page").val(page);
		$("#n_search").attr({
			"method" : "get",
			"action" : "/coursepage/courseboardList"
		});
		$("#n_search").submit();
	}
</script>
</head>
<body>
	<!-- 상세 페이지 이동을 위한 FORM -->
	<form id="detailForm">
		<input type="hidden" name="coursedata_no" id="coursedataNo"> <input
			type="hidden" name="page" value="${courseboardData.page }"> <input
			type="hidden" name="pageSize" value="${courseboardData.pageSize }">
	</form>
	<!-- 검색 기능 -->
	<div id="coursepageSearch">
		<form id="n_search" name="n_search">
			<input type="hidden" id="page" name="page"
				value="${courseboardData.page }">
			<table>
				<tr>
					<td><div id="searchStatus">
							<select id="coursedata_status" name="coursedata_status">
								<option value="all">전체</option>
								<option value="notice">공지사항</option>
								<option value="dataroom">자료실</option>
								<option value="task">과제</option>
							</select>
						</div></td>
					<td><div id="searchPage">
							한페이지에<select id="pageSize" name="pageSize">
								<option value="5">5줄</option>
								<option value="10">10줄</option>
							</select>
						</div></td>
				</tr>
			</table>
		</form>
	</div>

	<div>
		<table border="1" id="coursepageTable">
			<colgroup>
				<col width="5%">
				<col width="5%">
				<col width="45%">
				<col width="20%">
				<col width="20%">
			</colgroup>
			<tr>
				<th>글번호</th>
				<th>글종류</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일(또는 최근수정일)</th>
			</tr>
			<c:choose>
				<c:when test="${not empty courseboardList}">
					<c:forEach var="postList" items="${courseboardList}">
						<tr data-num="${postList.coursedata_no}">
							<td>${postList.coursedata_no}</td>
							<td>${postList.coursedata_status}</td>
							<td class="postsDetail">${postList.coursedata_title}</td>
							<td class="writer">${postList.coursedata_writer}</td>
							<td>${postList.coursedata_registerdate}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">작성된 글이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

	<div>
		<input type="button" class="courseBoardWrite" value="글쓰기">
	</div>
	<%-- ============ 페이지 네비게이션 시작 =========== --%>
	<div id="boardPage">
		<tag:paging page="${param.page }" total="${total }"
			list_size="${courseboardData.pageSize }">
		</tag:paging>
	</div>
	<%-- ============ 페이지 네비게이션 종료 =========== --%>

</body>
</html>