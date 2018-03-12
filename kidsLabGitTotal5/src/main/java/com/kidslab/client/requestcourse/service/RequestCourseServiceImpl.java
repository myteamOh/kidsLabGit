package com.kidslab.client.requestcourse.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.requestcourse.dao.RequestCourseDao;

@Service
@Transactional
public class RequestCourseServiceImpl implements RequestCourseService {

	Logger logger = Logger.getLogger(RequestCourseServiceImpl.class);

	@Autowired
	private RequestCourseDao requestCourseDao;

	/* 강의목록 구현 */
	@Override
	public List<CourseVO> requestCourseList(CourseVO cvo) {

		List<CourseVO> courseList = null;

		courseList = requestCourseDao.requestCourseList(cvo);

		return courseList;
	}

	/* 강의 상세보기 */
	@Override
	public CourseVO requestCourseDetail(CourseVO cvo) {
		
		CourseVO detail = null;
		
		detail = requestCourseDao.requestCourseDetail(cvo);

		return detail;
	}

	/* 수강신청한 listcount */
	@Override
	public int requestCourseCount(CourseVO cvo) {
		
		

		return 0;
	}

}
