package com.kidslab.admin.login.dao;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.login.vo.AdminLoginVO;

@Repository // 현재 클래스를 스프링에서 관리하는 dao bean 으로 등록
public class AdminLoginDaoImpl implements AdminLoginDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public AdminLoginVO userIdSelect(String userId) {
		// TODO Auto-generated method stub
		return (AdminLoginVO) sqlSession.selectOne("userIdSelect", userId);
	}

	@Override
	public AdminLoginVO loginSelect(AdminLoginVO vo) {
		// TODO Auto-generated method stub
		return (AdminLoginVO) sqlSession.selectOne("loginSelect", vo);
	}

	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub

	}

}
