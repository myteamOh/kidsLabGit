package com.kidslab.client.requestcourse.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;

@Repository
public class RequestCourseDaoImpl implements RequestCourseDao {

	@Autowired
	private SqlSession session;

	/* 글목록 구현 */
	@Override
	public List<CourseVO> requestCourseList(CourseVO cvo) {
		return session.selectList("requestCourseList", cvo);
	}

	/* 글 상세보기 */
	@Override
	public CourseVO requestCourseDetail(CourseVO cvo) {
		return session.selectOne("requestCourseDetail", cvo);
	}

	/* 강의신청인원 세기 */
	@Override
	public List<RequestCourseVO> requestCourseCount(CourseVO cvo) {
		return session.selectList("requestCourseCount", cvo);
	}

	/* 강의 신청 */
	@Override
	public int requestCourseInsert(RequestCourseVO rcvo) {
		return session.insert("requestCourseInsert", rcvo);
	}

	/* 번호로 신청정보 가져오기 */
	@Override
	public List<RequestCourseVO> reCourseSelectByNo(RequestCourseVO rcvo) {
		return session.selectList("reCourseSelectByNo", rcvo);
	}

	/* 강의신청 취소 처리 */
	@Override
	public int requestCourseDelete(int requestcourse_no) {
		return session.delete("requestCourseDelete", requestcourse_no);
	}

	@Override
	public RequestCourseVO reCourseSelectOne(RequestCourseVO rcvo) {
		return session.selectOne("reCourseSelectOne", rcvo);
	}

	/* 환불신청 처리 */
	@Override
	public int refundApply(RequestCourseVO rcvo) {
		return session.update("refundApply", rcvo);
	}

	/* 탈퇴가능여부 체크 */
	@Override
	public List<RequestCourseVO> withdrawCheck(ParentVO pvo) {
		return session.selectList("withdrawCheck", pvo);
	}

	@Override
	public List<RequestCourseVO> refundList(ParentVO pvo) {
		// TODO Auto-generated method stub
		return session.selectList("refundList", pvo);
	}

}
