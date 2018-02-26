package com.kidslab.admin.login.dao;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.login.vo.LoginVO;

@Repository // 현재 클래스를 스프링에서 관리하는 dao bean 으로 등록
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public String loginCheck(LoginVO vo) {
		// TODO Auto-generated method stub
		return (String) sqlSession.selectOne("loginCheck", vo);
	}

	@Override
	public LoginVO viewMember(LoginVO vo) {
		// TODO Auto-generated method stub
		return (LoginVO) sqlSession.selectOne("viewMember", vo);
	}

	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub

	}

}
