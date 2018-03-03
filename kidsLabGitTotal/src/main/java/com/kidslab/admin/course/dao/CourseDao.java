package com.kidslab.admin.course.dao;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;

public interface CourseDao {
	// 글 목록 구현
	public List<CourseVO> courseList(CourseVO cvo);

	// 페이징 추가
	public int courseListCnt(CourseVO cvo);

	// 강의 상세 보기
	public CourseVO courseDetail(CourseVO cvo);

	// 강의 등록 및 수정
	public int courseUpdate(CourseVO cvo);

	// 강의 삭제
	public int courseDelete(int course_no);
}
