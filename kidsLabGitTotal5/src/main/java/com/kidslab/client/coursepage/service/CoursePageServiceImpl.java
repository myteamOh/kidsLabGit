package com.kidslab.client.coursepage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;
import com.kidslab.client.coursepage.dao.CoursePageDao;

@Service
@Transactional
public class CoursePageServiceImpl implements CoursePageService {

	Logger logger = Logger.getLogger(CoursePageServiceImpl.class);

	@Autowired
	private CoursePageDao coursePageDao;

	/*강의정보 가져오기*/
	@Override
	public CourseVO selectCourse(CourseVO cvo) {
		
		CourseVO vo = coursePageDao.selectCourse(cvo);

		return vo;
	}

	/*게시판 글목록 공지사항 자료실 5개씩*/
	@Override
	public List<CourseDataVO> courseDataList(CourseDataVO cdvo) {

		List<CourseDataVO> courseDataList = coursePageDao.homeCourseDataList(cdvo);

		return courseDataList;
	}

}
