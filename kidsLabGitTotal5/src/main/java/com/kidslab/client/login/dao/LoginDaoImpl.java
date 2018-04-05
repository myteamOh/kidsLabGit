package com.kidslab.client.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SqlSession session;

	// 학부모 로그인
	@Override
	public ParentVO loginSelect(ParentVO pvo) {
		// 강사와 혼동가능하기에 직접경로 설정함.
		return session.selectOne("com.kidslab.client.login.dao.LoginDao.loginSelect", pvo);
	}

	// 학생 로그인
	@Override
	public StudentVO loginSelectS(StudentVO svo) {
		return session.selectOne("com.kidslab.client.login.dao.LoginDao.loginSelectS", svo);
	}

}