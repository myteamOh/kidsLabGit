package com.kidslab.client.login.dao;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface LoginDao {

	// 학부모 로그인
	public ParentVO loginSelect(ParentVO pvo);

	// 학생 로그인
	public StudentVO loginSelectS(StudentVO svo);

}
