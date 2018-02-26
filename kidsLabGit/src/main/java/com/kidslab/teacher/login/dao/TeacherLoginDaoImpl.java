package com.kidslab.teacher.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.teacher.login.vo.TeacherLoginVO;

@Repository
public class TeacherLoginDaoImpl implements TeacherLoginDao {

	@Autowired
	private SqlSession session;

	@Override
	public TeacherLoginVO userIdSelect(String teacher_id) {
		// TODO Auto-generated method stub
		return (TeacherLoginVO) session.selectOne("userIdSelect", teacher_id);
	}

	@Override
	public TeacherLoginVO loginSelect(TeacherLoginVO lvo) {
		// TODO Auto-generated method stub
		return (TeacherLoginVO) session.selectOne("loginSelet", lvo);
	}

	@Override
	public int loginHistoryInsert(TeacherLoginVO lvo) {
		// TODO Auto-generated method stub
		return session.insert("loginHistoryInsert", lvo);
	}

	@Override
	public int loginHistoryUpdate(TeacherLoginVO lvo) {
		// TODO Auto-generated method stub
		return session.update("loginHistoryUpdate", lvo);
	}

	@Override
	public TeacherLoginVO loginHistorySelect(String teacher_id) {
		// TODO Auto-generated method stub
		return (TeacherLoginVO) session.selectOne("loginHistorySelect", teacher_id);
	}

}
