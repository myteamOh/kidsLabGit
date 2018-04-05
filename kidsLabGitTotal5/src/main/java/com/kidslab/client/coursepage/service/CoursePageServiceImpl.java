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

	/* 강의정보 가져오기 */
	@Override
	public CourseVO selectCourse(CourseVO cvo) {

		CourseVO vo = coursePageDao.selectCourse(cvo);

		return vo;
	}

	/* 게시판 글목록 공지사항 자료실 5개씩 */
	@Override
	public List<CourseDataVO> homeCourseDataList(CourseDataVO cdvo) {

		List<CourseDataVO> courseDataList = coursePageDao.homeCourseDataList(cdvo);

		return courseDataList;
	}

	/* 전체 글목록 */
	@Override
	public List<CourseDataVO> courseDataList(CourseDataVO cdvo) {
		List<CourseDataVO> courseDataList = coursePageDao.courseDataList(cdvo);
		return courseDataList;
	}

	/* 전체글수 */
	@Override
	public int coursePageListCnt(CourseDataVO cdvo) {
		return coursePageDao.coursePageListCnt(cdvo);
	}

	// 글 입력 처리
	@Override
	public int coursePageInsert(CourseDataVO cdvo) {

		int result = 0;

		try {
			result = coursePageDao.coursePageInsert(cdvo); // 입력 성공시 result = 1
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 글상세보기
	@Override
	public CourseDataVO coursePageDetail(CourseDataVO cdvo) {

		CourseDataVO vo = null;

		vo = coursePageDao.coursePageDetail(cdvo);

		return vo;
	}

	// 글수정 처리
	@Override
	public int coursePageUpdate(CourseDataVO cdvo) {

		logger.info("글수정 처리 서비스!");

		int result = 0;

		try {
			result = coursePageDao.coursePageUpdate(cdvo); // 글 수정 성공시 result = 1
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 글삭제
	@Override
	public int coursePageDelete(int coursedata_no) {

		int result = 0;

		result = coursePageDao.coursePageDelete(coursedata_no);

		return result;
	}

}
