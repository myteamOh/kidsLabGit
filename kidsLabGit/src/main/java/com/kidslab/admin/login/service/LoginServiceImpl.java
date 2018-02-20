package com.kidslab.admin.login.service;

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
	public LoginVO userIdSelect(String userId) {
		// TODO Auto-generated method stub
		return loginDao.userIdSelect(userId);
	}

	@Override
	public LoginVO loginSelect(String userId, String userPw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int loginHistoryInsert(LoginVO lvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int loginHistoryUpdate(LoginVO lvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LoginVO loginHistorySelect(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
