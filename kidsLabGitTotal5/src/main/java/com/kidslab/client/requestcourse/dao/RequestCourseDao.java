package com.kidslab.client.requestcourse.dao;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;

public interface RequestCourseDao {

	public List<CourseVO> requestCourseList(CourseVO cvo);

	public CourseVO requestCourseDetail(CourseVO cvo);

	public List<RequestCourseVO> requestCourseCount(CourseVO courseNum);

	public int requestCourseInsert(RequestCourseVO rcvo);

	public List<RequestCourseVO> reCourseSelectByNo(RequestCourseVO rcvo);

}
