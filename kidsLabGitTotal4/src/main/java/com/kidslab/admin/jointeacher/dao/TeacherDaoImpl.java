package com.kidslab.admin.jointeacher.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.jointeacher.vo.TeacherSecurity;
import com.kidslab.admin.jointeacher.vo.TeacherVO;

@Repository("teacherDaoImpl")
public class TeacherDaoImpl implements TeacherDao {

	@Autowired
	private SqlSession session;

	@Override
	public int securityInsert(TeacherSecurity set) {
		// TODO Auto-generated method stub
		return session.insert("securityInsert", set);
	}

	@Override
	public TeacherSecurity securitySelect(String userId) {
		// TODO Auto-generated method stub
		return (TeacherSecurity) session.selectOne("securitySelect", userId);
	}

	@Override
	public int securityDelete(String userId) {
		// TODO Auto-generated method stub
		return session.delete("securityDelete", userId);
	}

	@Override
	public TeacherVO teacherSelect(String teacher_id) {
		// TODO Auto-generated method stub
		return (TeacherVO) session.selectOne("teacherSelect", teacher_id);
	}

	@Override
	public int teacherInsert(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return session.insert("teacherInsert");
	}

	@Override
	public int teacherUpdate(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return session.update("teacherUpdate", tvo);
	}

	@Override
	public int teacherDelete(String teacher_id) {
		// TODO Auto-generated method stub
		return session.delete("memberDelete", teacher_id);
	}

}
