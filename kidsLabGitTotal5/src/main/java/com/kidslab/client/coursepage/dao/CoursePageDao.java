package com.kidslab.client.coursepage.dao;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;

public interface CoursePageDao {

	// 강의 정보 가져오기
	public CourseVO selectCourse(CourseVO cvo);

	// 리스트 5개
	public List<CourseDataVO> homeCourseDataList(CourseDataVO cdvo);

	// 전체리스트
	public List<CourseDataVO> courseDataList(CourseDataVO cdvo);

	// 리스트 카운트
	public int coursePageListCnt(CourseDataVO cdvo);

	// 글입력처리
	public int coursePageInsert(CourseDataVO cdvo);

	// 글 상세보기
	public CourseDataVO coursePageDetail(CourseDataVO cdvo);

	// 글 수정 처리
	public int coursePageUpdate(CourseDataVO cdvo);

	// 글 삭제
	public int coursePageDelete(int coursedata_no);

}
