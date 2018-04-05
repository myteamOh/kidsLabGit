package com.kidslab.client.finduser.dao;

import java.util.List;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface FindUserDao {

	// 학부모 아이디 찾기
	public List<ParentVO> findParentId(ParentVO pvo);

	// 학생 아이디 찾기
	public List<StudentVO> findStudentId(StudentVO svo);

	// 학부모 비밀번호 발급전 아이디 체크
	public ParentVO matchParentId(ParentVO pvo);

	// salt값 가져오기 위한 메소드
	public UserSecurity temporarySecuritySelect(String userId);

	// 학부모 임시비밀번호 설정
	public int temporaryPwUpdate(ParentVO pvo);

	// 학생 정보 체크
	public StudentVO checkStudentInfo(StudentVO svo);

	// 학생 새 비밀번호 설정
	public int newStudentPwInsert(StudentVO svo);

}
