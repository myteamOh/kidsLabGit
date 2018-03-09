package com.kidslab.client.finduser.dao;

import java.util.List;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface FindUserDao {

	public List<ParentVO> findParentId(ParentVO pvo);

	public List<StudentVO> findStudentId(StudentVO svo);

	public ParentVO matchParentId(ParentVO pvo);

	public UserSecurity temporarySecuritySelect(String userId);

	public int temporaryPwUpdate(ParentVO pvo);

	public StudentVO checkStudentInfo(StudentVO svo);

	public int newStudentPwInsert(StudentVO svo);

}
