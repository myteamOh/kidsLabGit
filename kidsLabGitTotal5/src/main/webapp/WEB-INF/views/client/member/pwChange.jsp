<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 비밀번호 설정</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/pwChange.js"></script>

<link type="text/css" rel="stylesheet"
	href="/resources/include/css/pwChange.css">

</head>
<body>

	<section>
		<div>
			<table>
				<caption>비밀번호 재설정</caption>
				<tr>
					<td><label>새 비밀번호</label></td>
					<td><input type="password" id="newPassword" name="userPw"
						onkeyup="spaceDelete(this);" onchange="spaceDelete(this);"><br>
						<label class="check"></label></td>
				</tr>
				<tr>
					<td></td>
					<td><label>* 비밀번호는 문자+숫자+특수문자를 모두<br> 사용하여 8 ~
							13자리로 만들어 주세요.
					</label><br> <br></td>
				</tr>
				<tr>
					<td><label>새 비밀번호 재입력</label></td>
					<td><input type="password" id="newPasswordCheck"><br>
					<label class="check"></label></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="okBtn" value="변경">
						<input type="button" id="cancelBtn" value="취소"></td>
				</tr>
			</table>
		</div>
	</section>

</body>
</html>