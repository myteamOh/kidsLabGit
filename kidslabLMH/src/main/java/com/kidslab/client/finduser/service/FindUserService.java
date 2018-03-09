package com.kidslab.client.finduser.service;

import java.util.List;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface FindUserService {

	public List<ParentVO> findParentId(String parentName, String parentPhone);

	public ParentVO findParentPw(String parentId, String parentName);

	public int insertTemporaryPw(ParentVO pvo, String ranNum);

	public List<StudentVO> findStudentId(String studentName, String studentBirthday);

	public StudentVO checkStudentInfo(StudentVO svo);

	public int newStudentPwInsert(StudentVO svo);

}
