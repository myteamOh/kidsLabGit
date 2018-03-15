<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher 정보 수정</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript"
	src="/resources/include/js/modifyTeacherInfo.js"></script>
<script type="text/javascript">
	var sel_file;
	$(document).ready(function() {
		$("#file").on("change", handleImgFileSelect);
		$("#file").on("change", function() {
			console.log($("#file").val());
		});
	});
	// 이미지 확인
	function handleImgFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}

			sel_file = f;

			var reader = new FileReader();
			reader.onload = function(e) {
				$("#img").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}
	$(function() {
		$("#modifyCancelBtn").click(function() {
			location.href = "/teacher/mypage";
		});

	});
</script>
<style type="text/css">
#cl-dashboard {
	display: none;
}

.img_wrap {
	width: 100px;
	margin-top: 50px;
	height: 150px;
}

.img_wrap img {
	max-width: 100%;
	max-height: 100%;
}
</style>
</head>
<body>

	<section>
		<div>
			<form id="teacherModifyForm" name="teacherModifyForm"
				enctype="multipart/form-data">
				<input type="hidden" id="teacher_no" name="teacher_no"
					value="${teacher.teacher_no}"> <input type="hidden"
					id="teacher_photo" name="teacher_photo"
					value="${teacher.teacher_photo }"><input type="hidden"
					id="teacher_thumb" name="teacher_thumb"
					value="${teacher.teacher_thumb }">
				<table>
					<tr>
						<td><label></label> <label for="file">사진</label></td>
						<td><div class="img_wrap">
								<img id="img"
									src="/uploadStorage/teacher/thumbnail/${teacher.teacher_thumb }">
							</div> <input type="file" id="file" name="file"></td>
					</tr>
					<tr>
						<td><label>이름</label></td>
						<td><input type="text" readonly="readonly" id="teacher_name"
							name="teacher_name" value="${teacher.teacher_name}"></td>
					</tr>

					<tr>
						<td><label>아이디</label></td>
						<td><input type="email" readonly="readonly" id="teacher_id"
							name="teacher_id" value="${teacher.teacher_id}"></td>
					</tr>

					<tr>
						<td><label>새 비밀번호</label></td>
						<td><input type="password" id="teacher_password"
							name="teacher_password" maxlength="13"><label
							class="check">확인 라벨</label></td>
					</tr>

					<tr>
						<td></td>
						<td><label>* 비밀번호는 문자, 숫자, 특수문자를 <br> 사용하여 8 ~
								13자리로 만들어 주세요.
						</label></td>
					</tr>

					<tr>
						<td><label>새 비밀번호 확인</label></td>
						<td><input type="password" id="userPwCheck"
							name="userPwCheck" maxlength="13"> <label class="check">확인
								라벨</label></td>
					</tr>

					<tr>
						<td><label>*</label> <label>연락처</label></td>
						<td><input type="text" id="teacher_phone"
							name="teacher_phone" value="${teacher.teacher_phone}"
							maxlength="11"> <label class="check">확인 라벨</label></td>
					</tr>


				</table>

				<div>
					<input type="button" id="teacherModifyBtn" value="수정 완료"> <input
						type="button" id="modifyCancelBtn" value="취소"> <input
						type="button" id="secessionBtn" value="탈퇴">
				</div>

			</form>
		</div>
	</section>

</body>
</html>