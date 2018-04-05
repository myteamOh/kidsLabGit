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

	// 학부모 아이디 list가져오기
	@Override
	public List<ParentVO> findParentId(ParentVO pvo) {
		return session.selectList("findParentId", pvo);
	}

	// 학부모 아이디 찾기
	@Override
	public ParentVO matchParentId(ParentVO pvo) {
		return session.selectOne("matchParentId", pvo);
	}

	// 임시 비밀번호 설정을 위한 salt값 가져오기
	@Override
	public UserSecurity temporarySecuritySelect(String userId) {
		return (UserSecurity) session.selectOne("temporarySecuritySelect", userId);
	}

	// 학부모 임시비밀번호 설정하기 
	@Override
	public int temporaryPwUpdate(ParentVO pvo) {
		return session.update("temporaryPwUpdate", pvo);
	}

	// 학생 아이디 찾기
	@Override
	public List<StudentVO> findStudentId(StudentVO svo) {
		return session.selectList("findStudentId", svo);
	}

	// 학생 아이디 정보 체크
	@Override
	public StudentVO checkStudentInfo(StudentVO svo) {
		return session.selectOne("checkStudentInfo", svo);
	}

	// 학생 새 비밀번호 설정
	@Override
	public int newStudentPwInsert(StudentVO svo) {
		return session.update("newStudentPwInsert", svo);
	}

}
