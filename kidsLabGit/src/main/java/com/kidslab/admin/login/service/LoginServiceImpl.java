package com.kidslab.admin.login.service;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.login.dao.LoginDao;
import com.kidslab.admin.login.vo.LoginVO;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private LoginDao loginDao;

	@Override
	public String loginCheck(LoginVO vo) {
		// TODO Auto-generated method stub
		String userId = "";
		userId = loginDao.loginCheck(vo);

		return userId;

	}

	@Override
	public LoginVO viewMember(LoginVO vo) {
		// TODO Auto-generated method stub
		return loginDao.viewMember(vo);
	}

	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();
	}

}
