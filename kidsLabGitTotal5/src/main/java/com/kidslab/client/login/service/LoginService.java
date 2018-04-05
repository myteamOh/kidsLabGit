package com.kidslab.client.login.service;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface LoginService {

	// 학부모 로그인
	public ParentVO loginSelect(String userId, String userPw);

	// 학생 로그인
	public StudentVO loginSelectS(String userId, String userPw);

}
