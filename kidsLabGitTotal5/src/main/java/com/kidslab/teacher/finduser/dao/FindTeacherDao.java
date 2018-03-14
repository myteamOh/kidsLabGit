package com.kidslab.teacher.finduser.dao;

import java.util.List;

import com.kidslab.admin.jointeacher.vo.TeacherSecurity;
import com.kidslab.admin.jointeacher.vo.TeacherVO;

public interface FindTeacherDao {
	// teacher 아이디 찾기
	public List<TeacherVO> findTeacherId(TeacherVO tvo);

	// teacher 아이디 확인
	public TeacherVO matchTeacherId(TeacherVO tvo);

	// 임시비밀번호 확인
	public TeacherSecurity temporaryTeacherSecuritySelect(String teacher_id);

	// 임시비밀번호로 변경
	public int temporaryTeacherPwUpdate(TeacherVO tvo);
}
