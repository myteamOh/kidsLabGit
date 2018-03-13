package com.kidslab.client.requestcourse.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.requestcourse.dao.RequestCourseDao;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;

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

		CourseVO vo = requestCourseDao.requestCourseDetail(cvo);

		int count = vo.getCourse_totalperson();

		System.out.println("총 자리 : " + count);

		List<RequestCourseVO> courseList = requestCourseDao.requestCourseCount(cvo);

		for (int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getRequestcourse_paymentstatus().equals("결제대기")) {
			count--;
			} else {
				break;
			}
		}

		System.out.println("남은 자리 : " + count);

		return count;
	}

	/* 강의 신청 */
	@Override
	public int requestCourseInsert(RequestCourseVO rcvo) {

		logger.info("강의신청!");
		
		if(rcvo.getRequestcourse_paymethod().equals("bankbookDeposit")) {
			rcvo.setRequestcourse_paymethod("무통장입금");
		}

		requestCourseDao.requestCourseInsert(rcvo);
		
		return 1;
	}

	/*학부모번호, 학생번호, 강의번호로 가져오기*/
	@Override
	public List<RequestCourseVO> reCourseSelectByNo(RequestCourseVO rcvo) {
		
		logger.info("번호로 강의신청정보 가져오기!");
		
		List<RequestCourseVO> list = requestCourseDao.reCourseSelectByNo(rcvo);
		
		return list;
	}

}
