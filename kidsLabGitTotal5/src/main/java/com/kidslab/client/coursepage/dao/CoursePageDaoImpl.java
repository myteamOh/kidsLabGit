package com.kidslab.client.coursepage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;

@Repository
public class CoursePageDaoImpl implements CoursePageDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public CourseVO selectCourse(CourseVO cvo) {
		return session.selectOne("selectCourse", cvo);
	}

	@Override
	public List<CourseDataVO> homeCourseDataList(CourseDataVO cdvo) {
		return session.selectList("homeCourseDataList", cdvo);
	}

}
