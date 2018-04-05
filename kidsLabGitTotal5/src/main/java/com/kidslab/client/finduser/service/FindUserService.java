package com.kidslab.client.finduser.service;

import java.util.List;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface FindUserService {

	// 학부모 아이디 찾기
	public List<ParentVO> findParentId(String parentName, String parentPhone);

	// 학부모 비밀번호 재발급 처리
	public ParentVO findParentPw(String parentId, String parentName);

	// 임시비밀번호를 새비밀번호로 설정
	public int insertTemporaryPw(ParentVO pvo, String ranNum);

	// 학생 아이디 찾기
	public List<StudentVO> findStudentId(String studentName, String studentBirthday);

	// 학생 정보 체크
	public StudentVO checkStudentInfo(StudentVO svo);

	// 학생 새 비밀번호 설정
	public int newStudentPwInsert(StudentVO svo);

}
