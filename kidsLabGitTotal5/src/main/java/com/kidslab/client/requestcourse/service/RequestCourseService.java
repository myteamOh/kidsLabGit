package com.kidslab.client.requestcourse.service;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;

public interface RequestCourseService {

	public List<CourseVO> requestCourseList(CourseVO cvo);

	public CourseVO requestCourseDetail(CourseVO cvo);

	public int requestCourseCount(CourseVO cvo);

	public int requestCourseInsert(RequestCourseVO rcvo);

	public List<RequestCourseVO> reCourseSelectByNo(RequestCourseVO rcvo);

	public int requestCourseDelete(int requestcourse_no);

	public RequestCourseVO reCourseSelectOne(RequestCourseVO rcvo);

	public int refundApply(RequestCourseVO rcvo);
	
	public int withdrawCheck(ParentVO pvo);
	
}
