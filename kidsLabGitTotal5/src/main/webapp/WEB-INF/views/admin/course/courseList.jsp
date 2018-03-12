<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0 , 
				minimum-scale=1.0 , user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link href="/resources/include/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/include/dist/css/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!-- [if It IE 9]>
<script src="/resources/include/dist/assets/js/ie8-responsive-file-warning.js"></script>
<[endif] -->
<script
	src="/resources/include/dist/assets/js/ie-emulation-modes-warning.js"></script>
<style type="text/css">
</style>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/adminCourse.css" />
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		// 검색 후 검색 대상과 검색 단어 출력
		var word = "<c:out value = '${courseData.keyword}' />";
		var value = "";
		var course_status = "<c:out value = '${courseData.course_status}' />";

		// 검색 후 검색 상태 출력
		if (course_status != "") {
			$("#course_status").val("<c:out value='${courseData.course_status}' />");
		}
		// 검색 후 검색 대상과 검색 단어 출력
		if (word != "") {
			$("#keyword").val("<c:out value='${courseData.keyword}' />");
			$("#search").val("<c:out value='${courseData.search}' />");


			if ($("#search").val() != 'teacher_name') {
				//contains() 는 특정 텍스트를 포함한 요소 반환
				$(value + ":contains('" + word + "')").each(
					function() {
						var regex = new RegExp(word, 'gi');
						$(this).html(
							$(this).text().replace(
								regex,
								"<span class='required'>" + word
								+ "</span>"));
					});
			}
		}

		/* 한 페이지에 보여줄 레코드 수 조회 후 선택 한 값 그대로 유지하기 위한 설정*/
		if ("<c:out value='${courseData.pageSize}' />" != "") {
			$("#pageSize").val("<c:out value='${courseData.pageSize}' />");
		}

		/* 검색 대상이 변경 될 때마다 처리 이벤트*/
		$("#search").change(function() {
			if ($("#search").val() == "all") {
				$("#keyword").val("전체 데이터 조회합니다.");
			} else if ($("#search").val() != "all") {
				$("#keyword").val("");
				$("#keyword").focus();
			}
		});

		//등록상태 대상이 변경 될 때마다 처리 이벤트
		$("#course_status").change(function() {
			goPage(1);
		});

		/* 검색 버튼 클릭 시 처리 이벤트 */
		$("#searchData").click(function() {
			if ($("#search").val() != "all") {
				if (!chkSubmit($('#keyword'), "검색어를")) {
					return;
				}
			}
			goPage(1);
		});

		/* 한 페이지에 보여줄 레코드 수 변경 될 때마다 처리 이벤트 */
		$("#pageSize").change(function() {
			goPage(1);
		});

		/* 수정 버튼 클릭 시 페이지 이동*/
		$(".registBtn").click(function() {
			var course_no = $(this).parents("tr").attr("data-num");
			$("#course_no").val(course_no);
			console.log("글번호 : " + course_no);

			$("#courseUpdateForm").attr({
				"method" : "get",
				"action" : "/admin/course/courseUpdate"
			});
			$("#courseUpdateForm").submit();
		});
		/* 등록 버튼 클릭 시 페이지 이동 */
		/* $(".updateBtn").click(function() {
			var course_no = $(this).parents("tr").attr("data-num");
			$("#course_no").val(course_no);
			console.log("글번호 : " + course_no);
			$("#courseUpdateForm").attr({
				"method" : "get",
				"action" : "/admin/course/courseUpdate"
			});
			$("#courseUpdateForm").submit();
		}); */

	});
	/* 검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징을 위한 실질적인 처리 함수 */
	/* function goPage(page) {
		if ($("#search").val() == "all") {
			$("#keyword").val("");
		}
		$("#page").val(page);
		$("#c_search").attr({
			"method" : "get",
			"action" : "/admin/course/courseList.do"
		});
		$("#c_search").submit();
	} */
	function goPage(page) {
		if ($("#search").val() == "all") {
			$("#keyword").val("");
		}
		$("#page").val(page);
		$("#c_search").attr({
			"method" : "get",
			"action" : "/admin/course/courseList.do"
		});
		$("#c_search").submit();
	}
</script>
</head>
<body>

	<div class="contentContainer container-fluid">
		<c:if test="${adminLogin.userId != null and adminLogin.userId != '' }">
			<div class="contentTit">
				<h3>강의 리스트</h3>
			</div>

			<%-- ===============수정 페이지 이동을 위한 FORM ============ --%>
			<form name="courseUpdateForm" id="courseUpdateForm">
				<input type="hidden" name="course_no" id="course_no"> <input
					type="hidden" name="page" value="${courseData.page }"> <input
					type="hidden" name="pageSize" value="${courseData.pageSize }">
			</form>
			<%-- ==============검색 기능 시작 ====================== --%>

			<div id="courseSearch">
				<form id="c_search" name="c_search">
					<input type="hidden" id="page" name="page"
						value="${courseData.page }">
					<table summary="검색">
						<tr>
							<td id="btd1"><label>검색조건</label> <select id="search"
								name="search">
									<option value="all" selected="selected">전체</option>
									<option value="teacher_name">강사이름</option>
							</select> <input type="text" name="keyword" id="keyword"
								value="전체 데이터 조회합니다."> <input type="button" value="검색"
								id="searchData"></td>
							<td id="btd3">등록상태<select id="course_status"
								name="course_status">
									<option value="all">전체</option>
									<option value="registwaiting">등록대기</option>
									<option value="agreewaiting">승인대기</option>
									<option value="recruiting">모집중</option>
									<option value="progressing">진행중</option>
									<option value="cancel">폐강</option>
							</select></td>
							<td id="btd2">한페이지에 <select id="pageSize" name="pageSize">
									<option value="1">1줄</option>
									<option value="3">3줄</option>
									<option value="7">7줄</option>
									<option value="10">10줄</option>
							</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%-- =====================검색 기능 종료 ================== --%>

			<%-- =============== 리스트 시작 ================= --%>

			<div id="courseList" class="container-fluid">
				<table summary="강의 리스트" class="table-striped table-hover">
					<colgroup>
						<%-- <col width="10%" />
					<col width="62%" />
					<col width="15%" />
					<col width="13%" /> --%>
					</colgroup>
					<thead>
						<tr>
							<th>강의 번호 <%-- <c:choose>
								<c:when
									test="${data.order_by=='b_num' and data.order_sc=='ASC' }">▲</c:when>
								<c:when
									test="${data.order_by=='b_num'  and data.order_sc=='DESC'}">▼</c:when>
								<c:otherwise>▲</c:otherwise>
							</c:choose> --%>
							</th>
							<th class="borcle">강사명</th>
							<th>강의명</th>
							<th>강의과목 <%-- <c:choose>
								<c:when
									test="${data.order_by=='b_date' and data.order_sc=='ASC' }">▲</c:when>
								<c:when
									test="${data.order_by=='b_date' and data.order_sc=='DESC' }">▼</c:when>
								<c:otherwise>▲</c:otherwise>
							</c:choose> --%>
							</th>
							<th>강의대상</th>
							<th>강의개요</th>
							<th>강의계획서</th>
							<th>등록일</th>
							<th>등록상태</th>
						</tr>
					</thead>
					<tbody id="list">
						<!-- 데이터 출력 -->
						<c:choose>
							<c:when test="${not empty courseList}">
								<c:forEach var="course" items="${courseList}" varStatus="status">
									<tr class="tac" data-num="${course.course_no}">
										<td>${course.course_no }</td>
										<td>${course.teacher_name}</td>
										<td>${course.course_name}</td>
										<td>${course.course_subject}</td>
										<td>${course.course_level}</td>
										<td>${course.course_summary}</td>
										<td>${course.course_plan}</td>
										<td>${course.course_registerdate}</td>
										<c:choose>
											<c:when test="${course.course_status == '등록대기'}">
												<td>${course.course_status}<br> <input
													type="button" id="registBtn" name="registBtn"
													class="registBtn" value="등록"> <input type="button"
													id="registdeleteBtn" name="deleteBtn" class="deleteBtn"
													value="삭제">
												</td>
											</c:when>
											<c:when test="${ course.course_status == '승인대기'}">
												<td>${course.course_status}<br> <input
													type="button" id="updateBtn" name="updateBtn"
													class="registBtn" value="수정"> <input type="button"
													class="deleteBtn" id="agreedeleteBtn" name="agreedeleteBtn"
													value="삭제">
												</td>
											</c:when>
											<c:otherwise>
												<td>${course.course_status }</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="9" class="tac">등록된 게시 물이 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>

			</div>
			<%-- =============== 리스트 종료 ===============--%>
		</c:if>
		<%-- =============== 페이지 네비게이션 시작 ============== --%>
		<c:if test="${adminLogin.userId != null and adminLogin.userId != '' }">
			<div id="coursePage">
				<tag:paging page="${param.page }" total="${total }"
					list_size="${courseData.pageSize }"></tag:paging>
			</div>
		</c:if>
		<div class="well">
			<c:if test="${adminLogin.userId == null or adminLogin.userId == '' }">
				로그인을 해주세요.
		</c:if>
		</div>
		<!-- ================ 페이지 네비게이션 종료 ============== -->
	</div>
</body>
</html>