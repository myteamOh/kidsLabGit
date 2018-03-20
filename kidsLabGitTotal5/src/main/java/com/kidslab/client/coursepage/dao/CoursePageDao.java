package com.kidslab.client.coursepage.dao;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;

public interface CoursePageDao {

	public CourseVO selectCourse(CourseVO cvo);

	public List<CourseDataVO> homeCourseDataList(CourseDataVO cdvo);

}
