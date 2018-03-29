$(function() {

	/* 학부모 아이디 찾기 처리 */
	$("#findParentId").click(
		function() {

			if ($('#findParentIdName').val() == ""
				|| $("#findParentIdName").val() == null) {
				alert("이름을 입력해 주세요.");
				return;
			} else if ($('#findParentIdPhone').val() == ""
				|| $("#findParentIdPhone").val() == null) {
				alert("연락처를 입력해 주세요.");
				return;
			}

			$.ajax({
				url : "/member/findparentid",
				type : "POST",
				data : "parent_name=" + $("#findParentIdName").val()
					+ "&parent_phone=" + $("#findParentIdPhone").val(),
				error : function() {
					alert("오류");
				},
				success : function(data) {
					alert("실행");

					$("#parentIdSearch").attr({
						"method" : "post",
						"action" : "/member/findparentid"
					});

					$("#parentIdSearch").submit();
				}
			});

		});

	/* 학부모 임시비밀번호 발급 처리 */
	$("#findParentPw").click(
		function() {
			if ($('#findParentPwId').val() == ""
				|| $("#findParentPwId").val() == null) {
				alert("아이디를 입력해 주세요.");
				return;
			} else if (1, $("#findParentPwId"), null, null) {
				alert("아이디를 형식에 맞게 입력해 주세요.");
				return;
			} else if ($('#findParentPwName').val() == ""
				|| $("#findParentPwName").val() == null) {
				alert("이름을 입력해 주세요.");
				return;
			}

			$.ajax({
				url : "/member/findparentpw",
				type : "POST",
				data : "userId=" + $("#findParentPwId").val()
					+ "&parent_name=" + $("#findParentPwName").val(),
				error : function() {
					alert("오류");
				},
				success : function(result) {

					if (result == 1) {
						alert("임시비밀번호 발급");
						location.href = "/login/login";
						return;
					} else if (result == 2) {
						alert("입력하신 아이디, 이름과 일치하는 정보가 없습니다.");
						return;
					}

				}
			});
		});

	/* 학생 아이디 찾기 처리 */
	$("#findStudentId").click(
		function() {
			if ($('#studentIdName').val() == ""
				|| $("#studentIdName").val() == null) {
				alert("이름을 입력해 주세요.");
				return;
			} else if ($('#studentIdBirth').val() == ""
				|| $("#studentIdBirth").val() == null) {
				alert("연락처를 입력해 주세요.");
				return;
			}

			$.ajax({
				url : "/member/findstudentid",
				type : "POST",
				data : "student_name=" + $("#studentIdName").val()
					+ "&student_birthday=" + $("#studentIdBirth").val(),
				error : function() {
					alert("오류");
				},
				success : function(data) {
					alert("실행");

					$("#studentIdSearch").attr({
						"method" : "post",
						"action" : "/member/findstudentid"
					});

					$("#studentIdSearch").submit();
				}
			});
		});

	/* 학생 정보 체크 */
	$("#newStudentPw").click(
		function() {

			if ($("#studentPwId").val() == ""
				|| $("#studentPwId").val() == null) {
				alert("아이디를 입력해 주세요.");
				return;
			} else if ($("#studentPwName").val() == ""
				|| $("#studentPwName").val() == null) {
				alert("이름을 입력해 주세요.");
				return;
			}

			$.ajax({
				url : "/member/checkstudentinfo",
				type : "POST",
				data : "userId=" + $("#studentPwId").val() + "&student_name=" + $("#studentPwName").val(),
				error : function() {
					alert("오류");
				},
				success : function(result) {
					if (result == 1) {
						alert("정보있음");
						location.href = "/member/newstudentpw";
						return;
					} else {
						alert("입력한 정보에 해당하는 계정이 없습니다.");
						$("#studentPwCheck").css("color", "#FF0000").html("입력한 정보에 해당하는 계정이 없습니다.");
						return;
					}
				}
			});

		});
	/* Teacher 아이디 찾기 처리 */
	$("#findIdBtn").click(
		function() {
			if ($('#teacher_name').val() == ""
				|| $("#teacher_name").val() == null) {
				alert("이름을 입력해 주세요.");
				return;
			} else if ($('#teacher_phone').val() == ""
				|| $("#teacher_phone").val() == null) {
				alert("연락처를 입력해 주세요.");
				return;
			}
			$.ajax({
				url : "/teacher/login/findTeacher",
				type : "POST",
				data : $("#teacherIdSearch").serialize(),
				dataType : "json",
				error : function() {
					alert("오류");
				},
				success : function(findIdCheck) {
					console.log(findIdCheck);
					list = findIdCheck;
					$(".remover").empty();
					console.log(list);
					if (list.length != 0) {
						$("#msg").before("<tr class='remover'><td colspan='2'><font color='blue'>아이디</font></td></tr>")
						for (var i = 0; i < list.length; i++) {
							$("#msg").before("<tr class='remover'><td colspan='2'><font color='red'>" + list[i].teacher_id + "</font></td></tr>");
						}
					} else {
						$("#msg").before("<tr class='remover'><td colspan='2'><font color='red'>정보에 해당하는 아이디가 없습니다.</font></td></tr>")
					}
				}
			});

		});
	/* Teacher 임시비밀번호 발급 처리 */
	$("#requestPwBtn").click(
		function() {
			if ($('#teacher_idPw').val() == ""
				|| $("#teacher_idPw").val() == null) {
				alert("아이디를 입력해 주세요.");
				return;
			} else if (1, $("#teacher_namePw"), null, null) {
				alert("아이디를 형식에 맞게 입력해 주세요.");
				return;
			} else if ($('#teacher_namePw').val() == ""
				|| $("#teacher_namePw").val() == null) {
				alert("이름을 입력해 주세요.");
				return;
			}

			$.ajax({
				url : "/teacher/login/findTeacherPw",
				type : "POST",
				data : $("#teacherPwSearch").serialize(),
				error : function() {
					alert("오류");
				},
				success : function(result) {
					if (result == 1) {
						alert("임시비밀번호 발급");
						location.href = "/teacher/login";
						return;
					} else if (result == 2) {
						alert("데이터가 없거나 실패");
						return;
					}

				}
			});
		});

});