package com.kidslab.admin.memberlist.dao;

import java.util.List;
import java.util.Map;

import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;
import com.kidslab.client.student.vo.StudentVO;

public interface MemberListDao {
	// 학생, 학부모 목록
	public List<StudentVO> studentList(StudentVO svo);

	// 학부모 목록
	public List<StudentVO> parentList(StudentVO svo);

	// 강사 목록
	public List<TeacherVO> teacherList(TeacherVO tvo);

	// 학생 페이징 추가
	public int studentListCnt(StudentVO svo);

	// 학부모 페이징 추가
	public int parentListCnt(StudentVO svo);

	// 강사 페이징 추가
	public int teacherListCnt(TeacherVO svo);

	// 학부모 가입 경로에 따른 파이차트
	public Map<String, Integer> joinRootList();

	// 학생 나이 별 회원수 바차트
	public Map<String, Integer> studentAgeList();

	// 매출 통계
	public RequestCourseVO paymentStatsList(RequestCourseVO rvo);

	// 환불 통계
	public RequestCourseVO refundStatsList(RequestCourseVO rvo);
}
