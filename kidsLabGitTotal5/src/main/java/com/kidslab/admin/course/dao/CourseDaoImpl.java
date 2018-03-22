package com.kidslab.admin.course.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.teacher.login.vo.TeacherLoginVO;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private SqlSession session;

	// 글 목록 구현
	@Override
	public List<CourseVO> courseList(CourseVO cvo) {
		// TODO Auto-generated method stub
		return session.selectList("courseList", cvo);
	}

	// 전체 레코드 건수 구현 (추가)
	@Override
	public int courseListCnt(CourseVO cVo) {
		// TODO Auto-generated method stub
		return (Integer) session.selectOne("courseListCnt");
	}

	// 강의 상세보기
	@Override
	public CourseVO courseDetail(CourseVO cvo) {
		// TODO Auto-generated method stub
		return (CourseVO) session.selectOne("courseDetail", cvo);
	}

	// 강의 등록 및 수정
	@Override
	public int courseUpdate(CourseVO cvo) {
		// TODO Auto-generated method stub
		return (Integer) session.update("courseUpdate", cvo);
	}

	// 강의 삭제
	@Override
	public int courseDelete(int course_no) {
		// TODO Auto-generated method stub
		return (Integer) session.delete("courseDelete", course_no);
	}

	@Override
	public List<CourseVO> teacherCourseList(TeacherLoginVO tvo) {
		// TODO Auto-generated method stub
		return session.selectList("teacherCourseList", tvo);
	}

	@Override
	public List<StudentVO> courseStudentList(CourseVO cvo) {
		// TODO Auto-generated method stub
		return session.selectList("courseStudentList", cvo);
	}

}
