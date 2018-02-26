package com.kidslab.admin.login.dao;

import javax.servlet.http.HttpSession;

import com.kidslab.admin.login.vo.LoginVO;

public interface LoginDao {
	public String loginCheck(LoginVO vo);

	public LoginVO viewMember(LoginVO vo);

	public void logout(HttpSession session);
}
