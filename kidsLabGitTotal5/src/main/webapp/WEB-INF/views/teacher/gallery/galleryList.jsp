<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 목록</title>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/common.css" />
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="/resources/include/dist/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/include/dist/css/album.css" />

<style type="text/css">
/* 리스트 부분 */
#boardPage {
	clear: both;
}
</style>
<script type="text/javascript">
	$(function() {


		/* 한페이지에 보여줄 레코드 수 조회 후 선택한 값 그대로 유지하기 위한
		설정 */
		if ("<c:out value='${galleryeData.pageSize}' />" != "") {
			$("#pageSize").val("<c:out value='${galleryData.pageSize}' />");
		}


		/* 한 페이지에 보여줄 레코드 수 변경될 때마다 처리 이벤트 */
		$("#pageSize").change(function() {
			goPage(1);
		});

		/* 글쓰기 버튼 클릭 시 처리 이벤트 */
		$("#insertFormBtn").click(function() {
			location.href = "/teacher/gallery/galleryInsertForm";
		});

		/* 제목 클릭시 상세 페이지 이동을 위한 처리 이벤트 */
		$(".goDetail").click(function() {
			var gallery_no = $(this).parents("tr").attr("data-num");
			$("#gallery_no").val(gallery_no);
			console.log("글번호 : " + gallery_no);
			//상세 페이지로 이동하기 위해 form추가 (id : detailForm)
			$("#detailForm").attr({
				"method" : "get",
				"action" : "/teacher/gallery/galleryDetail"
			});
			$("#detailForm").submit();
		});

		/* 수정 클릭시 상세 페이지 이동을 위한 처리 이벤트 */
		$(".goEdit").click(function() {
			var gallery_no = $(this).parents("tr").attr("data-num");
			$("#gallery_no").val(gallery_no);
			console.log("글번호 : " + gallery_no);
			//상세 페이지로 이동하기 위해 form추가 (id : detailForm)
			$("#detailForm").attr({
				"method" : "get",
				"action" : "/teacher/gallery/galleryUpdateForm"
			});
			$("#detailForm").submit();
		});

		/* 검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징을 위한 실질적인 처리 함수 */
		function goPage(page) {
			if ($("#search").val() == "all") {
				$("#keyword").val("");
			}
			$("#page").val(page);
			$("#f_search").attr({
				"method" : "get",
				"action" : "/teacher/gallery/galleryList"
			});
			$("#f_search").submit();
		}
	});
</script>

</head>
<body>
	<div class="contentContainer">
		<div class="contentTit">
			<h3>갤러리 리스트</h3>
		</div>

		<%-- ======== 상세 페이지 이동을 위한 FORM ============ --%>
		<form name="detailForm" id="detailForm">
			<input type="hidden" name="gallery_no" id="gallery_no">

		</form>


		<input type="hidden" id="teacher_no" name="teacher_no"
			value="${teacherLogin.teacher_no}"> <input type="hidden"
			id="gallery_file" name="gallery_file"
			value="${gallery.gallery_file }"><input type="hidden"
			id="gallery_thumb" name="gallery_thumb"
			value="${gallery.gallery_thumb }">

		<%-- ============== 글쓰기 버튼 출력 시작============ --%>
		<div class="contentBtn" align="right">
			<input type="button" value="글쓰기" id="insertFormBtn">
		</div>
		<%-- ============== 글쓰기 버튼 출력 종료============ --%>




		<%-- ================= 리스트 시작 =============== --%>
		<div id="galleryList" align="center" style="background-color: #A9F5A9">

			<!--  데이터  출력 -->
			<c:choose>
				<c:when test="${not empty galleryList}">
					<c:forEach var="gallery" items="${galleryList}" varStatus="status">
						<div class="col-md-4" style="margin-top: 10px;">
							<div class="card mb-4 box-shadow">

								<img class="card-img-top" id="img"
									src="/uploadStorage/gallery/thumbnail/${gallery.gallery_thumb}"
									alt="Card image cap" width="150" height="200">

								<div class="card-body">
									<div>
										<div>${gallery.gallery_title}<br>${gallery.gallery_registerdate}<br>
										</div>
									</div>
									<div class="d-flex justify-content-between align-items-center">
										<div class="btn-group">
											<table>
												<tr data-num="${gallery.gallery_no}">
													<td style="display: none;">${gallery.gallery_no}</td>
													<td><button type="button"
															class="btn btn-sm btn-outline-secondary goDetail">View</button></td>
													<td><c:if
															test="${teacherLogin.teacher_no == gallery.teacher_no}">
															<button type="button"
																class="btn btn-sm btn-outline-secondary goEdit">Edit</button>
														</c:if></td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>


						</div>
					</c:forEach>
				</c:when>

				<c:otherwise>

					<div class="tac">
						<p>등록된 게시 물이 존재하지 않습니다.</p>
					</div>

				</c:otherwise>
			</c:choose>

		</div>
		<%-- ================= 리스트 종료 ================ --%>



		<%-- ============ 페이지 네비게이션 시작 =========== --%>
		<div id="boardPage" align="center">
			<tag:paging page="${param.page }" total="${total }"
				list_size="${galleryData.pageSize }">
			</tag:paging>
		</div>
		<%-- ============ 페이지 네비게이션 종료 =========== --%>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script>
		window.jQuery
		|| document
			.write('<script src="/resources/include/js/jquery-3.3.1.slim.js"><\/script>')
	</script>
	<!-- <script src="/resources/include/dist/js/popper.min.js"></script>
	<script src="/resources/include/dist/js/bootstrap.min.js"></script>
	<script src="/resources/include/dist/js/holder.min.js"></script> -->


</body>
</html>