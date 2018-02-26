$(function() {

	/* 로그인 버튼 클릭시 이벤트 */
	$("#loginBtn").click(function() {
		console.log("로그인 시도!");

		location.href = "/kidslabmain";
	});

	/* 회원가입 버튼 클릭시 이벤트 */
	$("#parentJoinBtn").click(function() {
		console.log("회원가입폼 출력!");

		location.href = "/member/agree.do";
	});

	/* 아이디 / 비밀번호 찾기 버튼 클릭시 이벤트 */
	$("#findBtn").click(function() {
		console.log("서치폼 출력!");

		location.href = "/member/findUser.do";
	});

	/* 강사로그인 버튼 클릭시 이벤트 */
	$("#teacherLoginBtn").click(function() {
		console.log("강사로그인 출력!");

		location.href = "";
	});

});