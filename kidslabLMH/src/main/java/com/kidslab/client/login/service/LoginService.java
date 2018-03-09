package com.kidslab.client.login.service;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface LoginService {

	public ParentVO loginSelect(String userId, String userPw);

	public StudentVO loginSelectS(String userId, String userPw);

}
