package com.kidslab.teacher.finduser.service;

import java.util.List;

import com.kidslab.admin.jointeacher.vo.TeacherVO;

public interface FindTeacherService {
	// teacher 아이디 찾기
	public List<TeacherVO> findTeacherId(String teacher_name, String teacher_phone);

	// teacher 비밀번호 발급 전 아이디 이름 체크
	public TeacherVO findTeacherPw(String teacher_id, String teacher_name);

	// teacher 임시비밀번호 발급
	public int insertTeacherTemporaryPw(TeacherVO tvo, String ranNum);

}
