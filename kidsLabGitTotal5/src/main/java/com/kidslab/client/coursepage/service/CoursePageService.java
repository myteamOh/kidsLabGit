package com.kidslab.client.coursepage.service;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;

public interface CoursePageService {

	// 강의 정보 가져오기
	public CourseVO selectCourse(CourseVO cvo);

	// 게시판 글목록 공지사항 자료실 5개씩
	public List<CourseDataVO> homeCourseDataList(CourseDataVO cdvo);

	// 전체 글목록
	public List<CourseDataVO> courseDataList(CourseDataVO cdvo);

	// 전체 글 수
	public int coursePageListCnt(CourseDataVO cdvo);

	// 글 입력처리
	public int coursePageInsert(CourseDataVO cdvo);

	// 글 상세보기
	public CourseDataVO coursePageDetail(CourseDataVO cdvo);

	// 글 수정하기
	public int coursePageUpdate(CourseDataVO cdvo);

	// 글 삭제
	public int coursePageDelete(int coursedata_no);

}
