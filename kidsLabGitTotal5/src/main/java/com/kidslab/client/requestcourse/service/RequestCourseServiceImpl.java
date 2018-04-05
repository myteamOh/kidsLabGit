package com.kidslab.client.requestcourse.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.parent.vo.ParentVO;
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

		List<RequestCourseVO> courseList = requestCourseDao.requestCourseCount(cvo);

		if (courseList.size() > 0) {
			for (int i = 0; i < courseList.size(); i++) {
				if (courseList.get(i).getRequestcourse_paymentstatus().equals("결제대기")
						|| courseList.get(i).getRequestcourse_paymentstatus().equals("결제완료")) {
					count--;
				} else {
					break;
				}
			}
		}

		return count;
	}

	/* 강의 신청 */
	@Override
	public int requestCourseInsert(RequestCourseVO rcvo) {

		if (rcvo.getRequestcourse_paymethod().equals("bankbookDeposit")) {
			rcvo.setRequestcourse_paymethod("무통장입금");
		}

		requestCourseDao.requestCourseInsert(rcvo);

		return 1;
	}

	/* 학부모번호, 학생번호, 강의번호로 가져오기 */
	@Override
	public List<RequestCourseVO> reCourseSelectByNo(RequestCourseVO rcvo) {

		List<RequestCourseVO> list = requestCourseDao.reCourseSelectByNo(rcvo);

		return list;
	}

	/* 수강 신청 취소시 이벤트 */
	@Override
	public int requestCourseDelete(int requestcourse_no) {

		int result = 0;

		try {
			result = requestCourseDao.requestCourseDelete(requestcourse_no);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}

		return result;
	}

	@Override
	public RequestCourseVO reCourseSelectOne(RequestCourseVO rcvo) {

		RequestCourseVO vo = requestCourseDao.reCourseSelectOne(rcvo);

		return vo;
	}

	/* 환불신청 처리 */
	@Override
	public int refundApply(RequestCourseVO rcvo) {

		int result = requestCourseDao.refundApply(rcvo);

		return result;
	}

	/* 학부모 탈퇴가능여부 확인 */
	@Override
	public int withdrawCheck(ParentVO pvo) {

		int result = 0;

		List<RequestCourseVO> checkList = requestCourseDao.withdrawCheck(pvo);

		if (checkList.isEmpty()) {
			result = 1;
		} else if (!requestCourseDao.withdrawCheck(pvo).isEmpty()) {
			result = 2;
		}

		return result;
	}

	@Override
	public List<RequestCourseVO> refundList(ParentVO pvo) {
		
		List<RequestCourseVO> refundList = requestCourseDao.refundList(pvo);

		return refundList;
	}

}
