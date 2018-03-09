package com.kidslab.admin.login.service;

import javax.servlet.http.HttpSession;

import com.kidslab.admin.login.vo.AdminLoginVO;

public interface AdminLoginService {
	public AdminLoginVO userIdSelect(String userId);

	public AdminLoginVO loginSelect(String userId, String userPw);

	public void logout(HttpSession session);

}
