package com.kidslab.admin.course.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.course.dao.CourseDao;
import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.teacher.login.vo.TeacherLoginVO;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	Logger logger = Logger.getLogger(CourseServiceImpl.class);

	@Autowired
	private CourseDao courseDao;

	// 강의 목록 구현
	@Override
	public List<CourseVO> courseList(CourseVO cvo) {
		// TODO Auto-generated method stub

		List<CourseVO> courseList = null;

		// 정렬에 대한 기본값 설정 보류

		courseList = courseDao.courseList(cvo);

		return courseList;
	}

	// 전체 레코드 수 구현
	@Override
	public int courseListCnt(CourseVO cvo) {
		// TODO Auto-generated method stub
		return courseDao.courseListCnt(cvo);
	}

	// 강의 상세보기
	@Override
	public CourseVO courseDetail(CourseVO cvo) {
		// TODO Auto-generated method stub
		CourseVO detail = null;
		detail = courseDao.courseDetail(cvo);
		return detail;
	}

	// 강의 등록 및 수정
	@Override
	public int courseUpdate(CourseVO cvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = courseDao.courseUpdate(cvo);
		} catch (Exception e) {
			// TODO: handle exception
			result = 0;
		}
		return result;
	}

	// 강의 삭제
	@Override
	public int courseDelete(int course_no) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = courseDao.courseDelete(course_no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public List<CourseVO> teacherCourseList(TeacherLoginVO tvo) {

		List<CourseVO> teacherCourseList = null;

		teacherCourseList = courseDao.teacherCourseList(tvo);

		return teacherCourseList;
	}

	@Override
	public List<StudentVO> courseStudentList(CourseVO cvo) {
		// TODO Auto-generated method stub
		List<StudentVO> courseStudentList = null;

		courseStudentList = courseDao.courseStudentList(cvo);

		return courseStudentList;
	}

}
