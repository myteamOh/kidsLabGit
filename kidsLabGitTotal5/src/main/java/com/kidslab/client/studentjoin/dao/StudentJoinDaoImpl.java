package com.kidslab.client.studentjoin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.client.student.vo.StudentVO;

@Repository
public class StudentJoinDaoImpl implements StudentJoinDao {

	@Autowired
	private SqlSession session;

	@Override
	public StudentVO studentSelect(String userId) {
		return (StudentVO) session.selectOne("studentSelect", userId);
	}

	@Override
	public int studentInsert(StudentVO svo) {
		return session.insert("studentInsert");
	}

	@Override
	public int studentUpdate(StudentVO svo) {
		return session.update("studentUpdate", svo);
	}
	
	@Override
	public List<StudentVO> studentList(int parentNum) {
		return session.selectList("studentList", parentNum);
	}

}
