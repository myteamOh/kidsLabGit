package com.kidslab.client.requestcourse.service;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;

public interface RequestCourseService {

	public List<CourseVO> requestCourseList(CourseVO cvo);

	public CourseVO requestCourseDetail(CourseVO cvo);

	public int requestCourseCount(CourseVO cvo);

}
