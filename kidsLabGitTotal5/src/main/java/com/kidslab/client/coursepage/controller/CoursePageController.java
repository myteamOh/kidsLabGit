package com.kidslab.client.coursepage.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;
import com.kidslab.client.coursepage.service.CoursePageService;

@Controller
@RequestMapping(value = "/coursepage")
public class CoursePageController {

	Logger logger = Logger.getLogger(CoursePageController.class);

	@Autowired
	private CoursePageService coursePageService;

	/* 강의 페이지 버튼 클릭시 처리 */
	@RequestMapping(value = "/coursemain", method=RequestMethod.POST)
	public ModelAndView coursePage(@ModelAttribute("CourseVO") CourseVO cvo) {
		
		logger.info(cvo.getCourse_no() + "번강의 강의페이지 입장!");
		
		ModelAndView mav = new ModelAndView();
		CourseDataVO cdvo = new CourseDataVO();
		
		CourseVO vo = coursePageService.selectCourse(cvo);
		
		cdvo.setCourse_no(cvo.getCourse_no());
		
		cdvo.setCoursedata_status("공지사항");
		List<CourseDataVO> courseDataNoticeList = coursePageService.courseDataList(cdvo);
		
		cdvo.setCoursedata_status("자료실");
		List<CourseDataVO> courseDataDataList = coursePageService.courseDataList(cdvo);
		
		mav.addObject("course", vo);
		mav.addObject("cdNoticeList", courseDataNoticeList);
		mav.addObject("cdDataList", courseDataDataList);
		mav.setViewName("client/coursePage/coursePageHome");
		
		return mav;
	}
	
	/*게시판처리*/
	@RequestMapping(value="/courseboard")
	public String coursePageBoard() {
		
		logger.info("게시판이다해");
		
		
		
		return "";
	}

}
