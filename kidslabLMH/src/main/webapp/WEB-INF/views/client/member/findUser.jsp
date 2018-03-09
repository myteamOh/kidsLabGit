<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 / 비밀번호 찾기</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/findUser.js"></script>

</head>
<body>
	<section>
		<div>

			<h5>아이디 / 비밀번호를 찾기 위한 해당항목을 모두 입력해 주셔야 조회가 가능합니다.</h5>

			<div>

				<h4>학부모 아이디 찾기 / 임시 비밀번호 받기</h4>

				<form id="parentIdSearch">
					<table>

						<tr>
							<td colspan="2"><label>아이디 찾기</label></td>
						</tr>
						<tr>
							<td><label>이름</label></td>
							<td><input type="text" id="findParentIdName"
								name="parent_name" maxlength="5"></td>
						</tr>
						<tr>
							<td><label>연락처</label></td>
							<td><input type="text" id="findParentIdPhone"
								name="parent_phone" maxlength="11"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" id="findParentId"
								value="아이디 찾기"></td>
						</tr>

						<c:choose>
							<c:when test="${list == 'success'}">
								<tr>
									<td>아이디</td>
									<td>가입일(년-월-일)</td>
								</tr>
								<c:forEach var="parent" items="${parentIdList}">
									<tr>
										<td>${parent.userId}</td>
										<td>${parent.parent_registerdate}</td>
									</tr>
								</c:forEach>
							</c:when>

							<c:when test="${list == 'fail'}">
								<tr>
									<td colspan="2" style="color: red;">입력한 정보와 일치하는 아이디가
										없습니다.</td>
								</tr>
							</c:when>

							<c:otherwise>
							</c:otherwise>
						</c:choose>

					</table>
				</form>

				<form id="parentPwSearch">
					<table>
						<tr>
							<td colspan="2"><label>임시 비밀번호 받기</label></td>
						</tr>
						<tr>
							<td><label>아이디</label></td>
							<td><input type="email" id="findParentPwId" name="userId"></td>
						</tr>
						<tr>
							<td><label>이름</label></td>
							<td><input type="text" id="findParentPwName"
								name="parent_name" maxlength="5"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" id="findParentPw"
								value="임시 비밀번호 발급"></td>
						</tr>
					</table>
				</form>
			</div>

			<div>

				<h4>학생 아이디 찾기 / 비밀번호 재설정</h4>

				<form id="studentIdSearch">
					<table>
						<tr>
							<td colspan="2"><label>아이디 찾기</label></td>
						</tr>
						<tr>
							<td><label>이름</label></td>
							<td><input type="text" id="studentIdName"
								name="student_name" maxlength="5"></td>
						</tr>
						<tr>
							<td><label>생년월일(6자리)</label></td>
							<td><input type="text" id="studentIdBirth"
								name="student_birthday" maxlength="6"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" id="findStudentId"
								value="아이디 찾기"></td>
						</tr>

						<c:choose>
							<c:when test="${studnetList == 'success'}">
								<tr>
									<td>아이디</td>
									<td>가입일(년-월-일)</td>
								</tr>
								<c:forEach var="student" items="${studentIdList}">
									<tr>
										<td>${student.userId}</td>
										<td>${student.student_registerdate}</td>
									</tr>
								</c:forEach>
							</c:when>

							<c:when test="${studentList == 'fail'}">
								<tr>
									<td colspan="2" style="color: red;">입력한 정보와 일치하는 아이디가
										없습니다.</td>
								</tr>
							</c:when>

							<c:otherwise>
							</c:otherwise>
						</c:choose>

					</table>
				</form>

				<table>
					<tr>
						<td colspan="2"><label>비밀번호 재설정</label></td>
					</tr>
					<tr>
						<td><label>아이디</label></td>
						<td><input type="text" id="studentPwId" name="userId"></td>
					</tr>
					<tr>
						<td><label>이름</label></td>
						<td><input type="text" id="studentPwName" name="student_name"
							maxlength="5"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" id="newStudentPw"
							value="새 비밀번호 설정"></td>
					</tr>
					<tr>
						<td colspan="2"><label id="studentPwCheck"></label></td>
					</tr>
				</table>
			</div>
		</div>
	</section>
</body>
</html>