package com.kidslab.admin.login.dao;

import javax.servlet.http.HttpSession;

import com.kidslab.admin.login.vo.AdminLoginVO;

public interface AdminLoginDao {
	public AdminLoginVO userIdSelect(String userId);

	public AdminLoginVO loginSelect(AdminLoginVO vo);

	public void logout(HttpSession session);
}
