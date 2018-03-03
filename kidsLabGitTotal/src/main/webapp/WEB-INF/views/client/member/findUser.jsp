<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 / 비밀번호 찾기</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/findUser.js"></script>

</head>
<body>
	<div>

		<h5>아이디 / 비밀번호를 찾기 위한 해당항목을 모두 입력해 주셔야 조회가 가능합니다.</h5>

		<div>

			<h4>학부모 아이디 / 비밀번호 찾기</h4>

			<table>
				<tr>
					<td colspan="2"><label>아이디 찾기</label></td>
				</tr>
				<tr>
					<td><label>이름</label></td>
					<td><input type="text" id="parentName" name="parent_name">
					</td>
				</tr>
				<tr>
					<td><label>연락처</label></td>
					<td><input type="text" id="parentPhone" name="parent_phone">
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="findParentId"
						value="아이디 찾기"></td>
				</tr>
				<tr>
					<td colspan="2"><table class="result"></table></td>
				</tr>
			</table>

			<table>
				<tr>
					<td colspan="2"><label>비밀번호 찾기</label></td>
				</tr>
				<tr>
					<td><label>아이디</label></td>
					<td><input type="email" name="userId"></td>
				</tr>
				<tr>
					<td><label>이름</label></td>
					<td><input type="text" name="parent_name"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="findParentPw"
						value="임시 비밀번호 발급"></td>
				</tr>
			</table>
		</div>

		<div>

			<h4>학생 아이디 / 비밀번호 찾기</h4>

			<table>
				<tr>
					<td colspan="2"><label>아이디 찾기</label></td>
				</tr>
				<tr>
					<td><label>이름</label></td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td><label>생년월일(6자리)</label></td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="아이디 찾기"></td>
				</tr>
				<tr>
					<td><table class="result"></table></td>
				</tr>
			</table>

			<table>
				<tr>
					<td colspan="2"><label>비밀번호 찾기</label></td>
				</tr>
				<tr>
					<td><label>아이디</label></td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td><label>이름</label></td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="새 비밀번호 설정"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>