package com.kidslab.client.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

@Repository
public class LoginDaoImpl implements LoginDao {

	Logger logger = Logger.getLogger(LoginDaoImpl.class);

	@Autowired
	private SqlSession session;

	@Override
	public ParentVO loginSelect(ParentVO pvo) {
		return session.selectOne("loginSelect", pvo);
	}

	@Override
	public StudentVO loginSelectS(StudentVO svo) {
		return session.selectOne("loginSelectS", svo);
	}

}