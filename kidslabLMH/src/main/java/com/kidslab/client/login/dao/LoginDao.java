package com.kidslab.client.login.dao;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface LoginDao {

	public ParentVO loginSelect(ParentVO pvo);

	public StudentVO loginSelectS(StudentVO svo);

}
