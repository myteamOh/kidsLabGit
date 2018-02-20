package com.kidslab.admin.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.login.vo.LoginVO;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SqlSession session;

	@Override
	public LoginVO userIdSelect(String userId) {
		// TODO Auto-generated method stub
		return (LoginVO) session.selectOne("userIdSelect", userId);
	}

	@Override
	public LoginVO loginSelect(LoginVO lvo) {
		// TODO Auto-generated method stub
		return (LoginVO) session.selectOne("loginSelect", lvo);
	}

}
