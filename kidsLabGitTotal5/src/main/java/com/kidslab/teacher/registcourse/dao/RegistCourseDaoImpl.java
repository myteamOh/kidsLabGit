package com.kidslab.teacher.registcourse.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.teacher.registcourse.vo.RegistCourseVO;

@Repository
public class RegistCourseDaoImpl implements RegistCourseDao {

	@Autowired
	private SqlSession session;

	@Override
	public int registCourseInsert(RegistCourseVO rvo) {
		return session.insert("registCourseInsert", rvo);
	}

}
