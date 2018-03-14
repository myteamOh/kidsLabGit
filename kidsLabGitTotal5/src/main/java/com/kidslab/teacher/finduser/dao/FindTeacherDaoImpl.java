package com.kidslab.teacher.finduser.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.jointeacher.vo.TeacherSecurity;
import com.kidslab.admin.jointeacher.vo.TeacherVO;

@Repository
public class FindTeacherDaoImpl implements FindTeacherDao {

	@Autowired
	private SqlSession session;

	// 정보에 맞는 아이디 List
	@Override
	public List<TeacherVO> findTeacherId(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return session.selectList("findTeacherId", tvo);
	}

	// 비밀번호 찾기를 위한 Id 찾음
	@Override
	public TeacherVO matchTeacherId(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return session.selectOne("matchTeacherId", tvo);
	}

	// 임시 비밀번호 확인
	@Override
	public TeacherSecurity temporaryTeacherSecuritySelect(String teacher_id) {
		// TODO Auto-generated method stub
		return (TeacherSecurity) session.selectOne("temporaryTeacherSecuritySelect", teacher_id);
	}

	// 임시 비밀번호 설정
	@Override
	public int temporaryTeacherPwUpdate(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return session.update("temporaryTeacherPwUpdate", tvo);
	}

}
