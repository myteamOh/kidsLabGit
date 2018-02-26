<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,  initial-scale=1.0,  maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Join Teacher!!</title>

<!-- 모바일 웹 페이지 설정 -->
<!-- <link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png" /> -->
<!-- 모바일 웹 페이지 설정 끝 -->
<!--[if lt IE 9]> <script src="/resources/include/js/html5shiv.js"></script> <![endif]-->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/join.js"></script>
<script type="text/javascript">
	function errCodeCheck() {
		var errCode = '<c:out value = "${errCode}" />';
		if (errCode != '') {
			switch (parseInt(errCode)) {
			case 1:
				alert("이미 가입된 회원입니다!");
				break;
			case 2:
				alert("회원가입 처리가 실패하였습니다.  잠시 후 다시 시도해 주십시오.");
				break;
			}
		}
	}
	var sel_file;
	$(document).ready(function() {
		$("#file").on("change", handleImgFileSelect);
	});
	//이미지 확인
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
	<div class="contentContainer">
		<div class="well">
			<form id="teacherForm" class="form-horizontal" name="teacherForm"
				enctype="multipart/form-data">
				<div class="form-group form-group-sm">
					<label for="teacher_id" class="col-sm-2 control-label">사용자
						ID</label>
					<div class="col-sm-3">
						<input type="text" id="teacher_id" name="teacher_id"
							maxlength="30" class="form-control" placeholder="ID" />
					</div>
					<div class="col-sm-2">
						<input type="button" id="idConfirmBtn" value="아이디 중복체크"
							class="form-control btn-primary" />
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="teacher_password" class="col-sm-2 control-label">비밀
						번호</label>
					<div class="col-sm-3">
						<input type="password" id="teacher_password"
							name="teacher_password" maxlength="15" class="form-control"
							placeholder="Password">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="teacher_passwordCheck" class="col-sm-2 control-label">비밀번호
						확인 </label>
					<div class="col-sm-3">
						<input type="password" id="teacher_passwordCheck"
							name="teacher_passwordCheck" maxlength="15" class="form-control"
							placeholder="Password Confirm">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="teacher_name" class="col-sm-2 control-label">회원이름</label>
					<div class="col-sm-3">
						<input type="text" id="teacher_name" name="teacher_name"
							maxlength="7" class="form-control" placeholder="NAME">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="teacher_phone" class="col-sm-2 control-label">핸드폰
						번호 </label>
					<div class="col-sm-3">
						<input type="text" id="teacher_phone" name="teacher_phone"
							maxlength="11" class="form-control"
							placeholder="Phone Number(not -)">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="file" class="col-sm-2 control-label">사진</label>
					<div class="col-sm-3">
						<input type="file" id="file" name="file">
						<div class="img_wrap">
							<img id="img">
						</div>
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input type="button" value="확인" id="joinInsert"
							class="btn btn-default" /> <input type="button" value="재작성"
							id="joinReset" class="btn btn-default" /> <input type="button"
							value="취소" id="joinCancel" class="btn btn-default" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>