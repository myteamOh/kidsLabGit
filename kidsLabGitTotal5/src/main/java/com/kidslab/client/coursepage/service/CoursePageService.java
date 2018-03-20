package com.kidslab.client.coursepage.service;

import java.util.List;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;

public interface CoursePageService {
	
	public CourseVO selectCourse(CourseVO cvo);

	public List<CourseDataVO> courseDataList(CourseDataVO cdvo);

}
