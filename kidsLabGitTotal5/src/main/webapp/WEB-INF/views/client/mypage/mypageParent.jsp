<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage(학부모)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/mypage.js"></script>

<!-- <script type="text/javascript">
	function tabSetting() {
		// 탭 컨텐츠 hide 후 현재 탭메뉴 페이지만 show
		$('.tabPage').hide();
		$($('.current').find('a').attr('href')).show();

		// Tab 메뉴 클릭 이벤트 생성
		$('li').click(
				function(event) {
					var tagName = event.target.tagName; // 현재 선택된 태그네임
					var selectedLiTag = (tagName.toString() == 'A') ? $(
							event.target).parent('li') : $(event.target); // A태그일 경우 상위 Li태그 선택, Li태그일 경우 그대로 태그 객체
					var currentLiTag = $('li[class~=current]'); // 현재 current 클래그를 가진 탭
					var isCurrent = false;

					// 현재 클릭된 탭이 current를 가졌는지 확인
					isCurrent = $(selectedLiTag).hasClass('current');

					// current를 가지지 않았을 경우만 실행
					if (!isCurrent) {
						$($(currentLiTag).find('a').attr('href')).hide();
						$(currentLiTag).removeClass('current');

						$(selectedLiTag).addClass('current');
						$($(selectedLiTag).find('a').attr('href')).show();
					}

					return false;
				});
	}

	$(function() {
		// 탭 초기화 및 설정
		tabSetting();
	});
</script> -->

<style type="text/css">
/* .tabWrap {
	width: 900px;
	height: 500px;
}

.tab_Menu {
	margin: 0px;
	padding: 0px;
	list-style: none;
}

.tabMenu {
	background-color: #BDBDBD;
	width: 150px;
	margin: 0px;
	text-align: center;
	padding-top: 10px;
	padding-bottom: 10px;
	float: left;
	width: 150px;
}

.tabMenu a {
	color: #000000;
	font-weight: bold;
	text-decoration: none;
}

.current {
	background-color: #FFFFFF;
	border: 1px solid blue;
	border-bottom: hidden;
}

.tabPage {
	width: 900px;
	height: 470px;
	float: left;
	border: 1px solid blue;
} */
table {
	width: 350px;
}
</style>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/mypageParent.css">

</head>
<body>

	<section>
		<div>

			<!-- 강의 처리를 위한 폼 -->
			<form id="courseForm">
				<input type="hidden" id="requestcourse_no" name="requestcourse_no">
			</form>

			<!-- pw 체크를 위한 폼 -->
			<form id="pwCheckForm">
				<input type="hidden" id="parentNum" name="parent_no"
					value="${Login.parent_no}">
			</form>

			<form id="parentMypage">
				<div id="hello">
					<label>안녕하세요 ${Login.userName}(${Login.userId})님. 반갑습니다. </label>
				</div>
				<br>

				<div id="modifyBtnPart">
					<input type="button" class="modifyInfo mypageBtn" value="회원 정보 수정">
					<c:if test="${not empty rcListMap}">
						<input type="button" class="refundListBtn mypageBtn"
							value="환불신청 및 환불내역 조회">
					</c:if>
				</div>

				<!-- 자녀리스트 -->
				<!-- 기본 -->
				<c:choose>
					<c:when test="${not empty rcListMap}">
						<hr>
						<br>
						<div id="studentJoinBtnPart">
							<input type="button" id="studentJoin" class="mypageBtn"
								value="자녀 추가" src="/resources/include/img/plus.png">
						</div>
						<div>

							<c:forEach var="child" items="${rcListMap}">
								<div id="childListPart">
									<p class="studentInfo">${child.key.student_name}(${child.key.userId})</p>
									<div class="courseTotalList">
										<c:choose>
											<c:when test="${not empty child.value}">
												<table>
													<caption>결제 대기중인 강의 목록</caption>
													<c:forEach var="waitingCourse" items="${child.value}">
														<c:choose>
															<c:when
																test="${waitingCourse.course_status eq '모집중' && waitingCourse.requestcourse_paymentstatus eq '결제대기'}">

																<tr data-num="${waitingCourse.requestcourse_no}">
																	<td colspan="2">${waitingCourse.course_name}</td>
																	<td><input type="button"
																		class="applyCancel listBtn" value="신청 취소"></td>
																</tr>

															</c:when>
														</c:choose>
													</c:forEach>
												</table>

												<br>

												<table>
													<caption>수강 대기중인 강의</caption>
													<c:forEach var="readyCourse" items="${child.value}">
														<c:choose>
															<c:when
																test="${readyCourse.course_status eq '모집중' && readyCourse.requestcourse_paymentstatus eq '결제완료'}">
																<tr data-num="${readyCourse.requestcourse_no}">
																	<td>${readyCourse.course_name}</td>
																	<td><a
																		href="/mypage/download?course_plan=${readyCourse.course_plan }"><input
																			type="button" class="coursePlan listBtn"
																			value="강의계획서"></a></td>
																	<td><input type="button"
																		class="courseCancel listBtn" value="수강 취소"></td>
																</tr>
															</c:when>
														</c:choose>
													</c:forEach>
												</table>

												<br>

												<table>
													<caption>수강중인 강의 목록</caption>
													<c:forEach var="studying" items="${child.value}">
														<c:choose>
															<c:when
																test="${studying.course_status eq '진행중' && studying.requestcourse_paymentstatus eq '결제완료'}">

																<tr data-num="${studying.requestcourse_no}">
																	<td colspan="2">${studying.course_name}</td>
																	<td><input type="button"
																		class="courseCancel listBtn" value="수강 포기"></td>
																</tr>
															</c:when>
														</c:choose>
													</c:forEach>
												</table>
												<br>
											</c:when>
											<c:otherwise>
												<p id="noneCourse">신청한 강의가 없습니다.</p>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:forEach>

						</div>
						<br>
					</c:when>

					<c:otherwise>
						<br>
						<hr>
						<div id="studentJoinBtnPart">
							<input type="button" id="studentJoin"
								class="mypageBtn studentJoinNone" value="자녀 추가"
								src="/resources/include/img/plus.png">
						</div>
						<br>
					</c:otherwise>
				</c:choose>

				<!-- <div class="tabWrap">
					<ul class="tab_Menu">
						<li class="tabMenu current"><a href="#tabContent01">Tab 1</a></li>
						<li class="tabMenu"><a href="#tabContent02">Tab 2</a></li>
						<li class="tabMenu"><a href="#tabContent03">Tab 3</a></li>
					</ul>
					<div class="tab_Content_Wrap">
						<div id="tabContent01" class="tabPage">Tab1 Content</div>
						<div id="tabContent02" class="tabPage">Tab2 Content</div>
						<div id="tabContent03" class="tabPage">Tab3 Content</div>
					</div>
				</div> -->

				<!-- 이하 실험용 -->
				<%-- <c:choose>
					<c:when test="${not empty testtopmap}">
						자녀있을때
						<div>
							<input type="button" id="studentJoin" value="자녀 추가"
								src="/resources/include/img/plus.png">
						</div>
						자녀리스트
						
						<ul>
							<c:forEach var="testtopmap" items="${testtopmap}">
								<li>${testtopmap.key.student_name}(${testtopmap.key.userId})
									<div>
										<c:choose>
											<c:when test="${not empty testtopmap.value}">

												<table>
													<caption>결제 대기중인 강의 목록</caption>
													<c:forEach var="testmiddelmap1" items="${testtopmap.value}">
														<c:choose>
															<c:when test="${not empty testmiddelmap1.value}">

																<c:forEach var="testbottommap1"
																	items="${testmiddelmap1.value}">
																	<c:choose>
																		<c:when test="${not empty testbottommap1.value}">
																			<c:forEach var="testlistvo1"
																				items="${testbottommap1.value}">
																				<tr data-num="${testlistvo1.requestcourse_no}">
																					<td>${testlistvo1.course_name}</td>
																					<td><input type="button" value="신청 취소"></td>
																				</tr>
																			</c:forEach>
																		</c:when>
																		<c:otherwise>
																			<tr>
																				<td>inner음슴</td>
																			</tr>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>

															</c:when>
															<c:otherwise>
																<tr>
																	<td>outter음슴</td>
																</tr>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</table>

												<br>

												<table>
												<caption>수강 대기중인 강의 목록</caption>
													<c:forEach var="testmiddelmap2" items="${testtopmap.value}"
														end="0">
														<c:forEach var="testbottommap2"
															items="${testmiddelmap2.value}" begin="1">
															<c:choose>
																<c:when test="${not empty testbottommap2}">
																	<c:forEach var="testlistvo2"
																		items="${testbottommap2.value}">
																		<tr data-num="${testlistvo2.requestcourse_no}">
																			<td>${testlistvo2.course_name}</td>
																			<td><input type="button" value="강의계획서"></td>
																			<td><input type="button" value="수강 취소"></td>
																		</tr>
																	</c:forEach>
																</c:when>
																<c:otherwise>
																	<tr>
																		<td>음슴</td>
																	</tr>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</c:forEach>
												</table>
												
												<br>

												<table>
												<caption>수강중인 강의 목록</caption>
													<c:forEach var="testmiddelmap3" items="${testtopmap.value}"
														begin="1">
														<c:forEach var="testbottommap3"
															items="${testmiddelmap3.value}" begin="1">
															<c:choose>
																<c:when test="${not empty testbottommap3.value}">
																	<c:forEach var="testlistvo3"
																		items="${testbottommap3.value}">
																		<tr data-num="${testlistvo3.requestcourse_no}">
																			<td>${testlistvo3.course_name}</td>
																			<td><input type="button" value="학생 평가"></td>
																			<td><input type="button" value="수강 포기"></td>
																		</tr>
																	</c:forEach>
																</c:when>
																<c:otherwise>
																	<tr>
																		<td>음슴</td>
																	</tr>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</c:forEach>
												</table>

											</c:when>
											<c:otherwise>
												<p>신청한 강의가 없습니다.</p>
											</c:otherwise>
										</c:choose>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:when>

					<c:otherwise>
						자녀 없을떄
						<div>
							<input type="button" id="studentJoin" value="자녀 추가"
								src="/resources/include/img/plus.png">
						</div>
					</c:otherwise>
				</c:choose> --%>

			</form>
		</div>
	</section>

	<%-- <form>
		<table>
			<caption>환불내역</caption>
			<tr>
				<th>No.</th>
				<th>환불계좌은행</th>
				<th>환불계좌번호</th>
				<th>예금주</th>
				<th>환불금액</th>
				<th>환불신청일</th>
				<th>상태</th>
			</tr>
			<c:forEach var="" items="">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		</table>
	</form> --%>

</body>
</html>