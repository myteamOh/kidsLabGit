package com.kidslab.admin.login.dao;

import com.kidslab.admin.login.vo.LoginVO;

public interface LoginDao {
	public LoginVO userIdSelect(String userId);

	public LoginVO loginSelect(LoginVO lvo);
}
