package com.kidslab.admin.memberlist.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.admin.memberlist.dao.MemberListDao;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;
import com.kidslab.client.student.vo.StudentVO;

@Service
@Transactional
public class MemberListServiceImpl implements MemberListService {

	Logger logger = Logger.getLogger(MemberListServiceImpl.class);

	@Autowired
	private MemberListDao memberListDao;

	@Override
	public List<StudentVO> studentList(StudentVO svo) {
		// TODO Auto-generated method stub

		List<StudentVO> studentList = null;

		studentList = memberListDao.studentList(svo);
		return studentList;
	}

	@Override
	public List<StudentVO> parentList(StudentVO svo) {
		// TODO Auto-generated method stub

		List<StudentVO> parentList = null;

		parentList = memberListDao.parentList(svo);
		return parentList;
	}

	@Override
	public List<TeacherVO> teacherList(TeacherVO tvo) {
		// TODO Auto-generated method stub

		List<TeacherVO> teacherList = null;

		teacherList = memberListDao.teacherList(tvo);
		return teacherList;
	}

	@Override
	public int studentListCnt(StudentVO svo) {
		// TODO Auto-generated method stub
		return memberListDao.studentListCnt(svo);
	}

	@Override
	public int parentListCnt(StudentVO svo) {
		// TODO Auto-generated method stub
		return memberListDao.parentListCnt(svo);
	}

	@Override
	public int teacherListCnt(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return memberListDao.teacherListCnt(tvo);
	}

	@Override
	public Map<String, Integer> joinRootList() {
		// TODO Auto-generated method stub
		Map<String, Integer> rootList = null;
		rootList = memberListDao.joinRootList();
		return rootList;
	}

	@Override
	public Map<String, Integer> studentAgeList() {
		// TODO Auto-generated method stub
		Map<String, Integer> ageList = null;
		ageList = memberListDao.studentAgeList();
		return ageList;
	}

	@Override
	public RequestCourseVO paymentStatsList(RequestCourseVO rvo) {
		// TODO Auto-generated method stub
		RequestCourseVO payment = null;
		payment = memberListDao.paymentStatsList(rvo);
		logger.info(payment.getRequestcourse_payamount());
		return payment;
	}

	@Override
	public RequestCourseVO refundStatsList(RequestCourseVO rvo) {
		// TODO Auto-generated method stub
		RequestCourseVO refund = null;
		refund = memberListDao.refundStatsList(rvo);
		logger.info(refund.getRequestcourse_refundcharge());
		return refund;
	}

}
