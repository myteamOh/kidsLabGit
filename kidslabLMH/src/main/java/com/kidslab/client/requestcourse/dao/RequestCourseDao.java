package com.kidslab.client.requestcourse.dao;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;

public interface RequestCourseDao {

	public List<CourseVO> requestCourseList(CourseVO cvo);

	public CourseVO requestCourseDetail(CourseVO cvo);

}
