package com.kidslab.client.coursepage.service;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;

public interface CoursePageService {

	public CourseVO selectCourse(CourseVO cvo);

	public List<CourseDataVO> homeCourseDataList(CourseDataVO cdvo);

	public List<CourseDataVO> courseDataList(CourseDataVO cdvo);

	public int coursePageListCnt(CourseDataVO cdvo);

	public int coursePageInsert(CourseDataVO cdvo);

	public CourseDataVO coursePageDetail(CourseDataVO cdvo);

	public int coursePageUpdate(CourseDataVO cdvo);

	public int coursePageDelete(int coursedata_no);

}
