package com.kidslab.admin.login.service;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.login.dao.AdminLoginDao;
import com.kidslab.admin.login.vo.AdminLoginVO;

@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {
	Logger logger = Logger.getLogger(AdminLoginServiceImpl.class);

	@Autowired
	private AdminLoginDao adminLoginDao;

	@Override
	public AdminLoginVO userIdSelect(String userId) {
		// TODO Auto-generated method stub
		logger.info("userIdSelect : " + userId);
		return adminLoginDao.userIdSelect(userId);

	}

	@Override
	public AdminLoginVO loginSelect(String userId, String userPw) {
		// TODO Auto-generated method stub
		AdminLoginVO vo = null;
		if (adminLoginDao.userIdSelect(userId) != null) {
			AdminLoginVO lvo = new AdminLoginVO();
			lvo.setUserId(userId);
			lvo.setUserPw(userPw);

			vo = adminLoginDao.loginSelect(lvo);
		}
		return vo;
	}

	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();
	}

}
