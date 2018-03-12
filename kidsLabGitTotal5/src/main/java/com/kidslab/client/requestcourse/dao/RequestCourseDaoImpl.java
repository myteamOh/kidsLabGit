package com.kidslab.client.requestcourse.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.course.vo.CourseVO;

@Repository
public class RequestCourseDaoImpl implements RequestCourseDao {
	
	@Autowired
	private SqlSession session;

	/*글목록 구현*/
	@Override
	public List<CourseVO> requestCourseList(CourseVO cvo) {
		return session.selectList("requestCourseList", cvo);
	}

	/*글 상세보기*/
	@Override
	public CourseVO requestCourseDetail(CourseVO cvo) {
		return session.selectOne("requestCourseDetail", cvo);
	}

}
