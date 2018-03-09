package com.kidslab.client.finduser.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

@Repository
public class FindUserDaoImpl implements FindUserDao {

	Logger logger = Logger.getLogger(FindUserDaoImpl.class);

	@Autowired
	private SqlSession session;

	@Override
	public List<ParentVO> findParentId(ParentVO pvo) {
		return session.selectList("findParentId", pvo);
	}

	@Override
	public ParentVO matchParentId(ParentVO pvo) {
		return session.selectOne("matchParentId", pvo);
	}

	@Override
	public UserSecurity temporarySecuritySelect(String userId) {
		return (UserSecurity) session.selectOne("temporarySecuritySelect", userId);
	}

	@Override
	public int temporaryPwUpdate(ParentVO pvo) {
		return session.update("temporaryPwUpdate", pvo);
	}

	@Override
	public List<StudentVO> findStudentId(StudentVO svo) {
		return session.selectList("findStudentId", svo);
	}

	@Override
	public StudentVO checkStudentInfo(StudentVO svo) {
		return session.selectOne("checkStudentInfo", svo);
	}

	@Override
	public int newStudentPwInsert(StudentVO svo) {
		return session.update("newStudentPwInsert", svo);
	}

}
