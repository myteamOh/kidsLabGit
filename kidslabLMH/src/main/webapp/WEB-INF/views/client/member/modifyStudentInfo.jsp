<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 (학생)</title>
</head>
<body>

	<section>

		<div>
			<form>
				<table>
					<tr>
						<td><label>이름</label></td>
						<td><input type="text" value="${student.student_name}" readonly="readonly"></td>
					</tr>

					<tr>
						<td><label>아이디</label></td>
						<td><input type="text" value="${student.userId}" readonly="readonly"></td>
					</tr>

					<tr>
						<td><label>현재 비밀번호</label></td>
						<td><input type="password"><label>여부 라벨</label></td>
					</tr>

					<tr>
						<td><label>새 비밀번호</label></td>
						<td><input type="password"><label>여부 라벨</label></td>
					</tr>

					<tr>
						<td></td>
						<td><label>* 비밀번호는 문자, 숫자, 특수문자를 <br> 사용하여 8 ~
								13자리로 만들어 주세요.
						</label></td>
					</tr>

					<tr>
						<td><label>새 비밀번호 확인</label></td>
						<td><input type="password"> <label>여부 라벨</label></td>
					</tr>

					<tr>
						<td><label>*</label> <label>학교</label></td>
						<td><input type="text" name="student_school" value="${student.student_school}"><label>여부 라벨</label></td>
					</tr>
				</table>

				<div>
					<input type="button" id="okBtn" value="수정 완료"> <input type="button" id="cancelBtn"
						value="취소">
				</div>
			</form>
		</div>

	</section>

</body>
</html>