package com.kidslab.client.requestcourse.contoller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.requestcourse.service.RequestCourseService;

@Controller
@RequestMapping(value = "/requestcourse")
public class RequestCourseController {

	Logger logger = Logger.getLogger(RequestCourseController.class);

	@Autowired
	private RequestCourseService requestCourseService;

	/* 강의신청 목록조회 페이지 호출 */
	@RequestMapping(value = "/apply.do", method = RequestMethod.GET)
	public String requestCourseList(@ModelAttribute("CourseVO") CourseVO cvo, Model model) {

		logger.info("강의신청 목록 호출");
		
		List<CourseVO> requestCourseList = requestCourseService.requestCourseList(cvo);
		
		model.addAttribute("requestCourseList", requestCourseList);
		model.addAttribute("courseData", cvo);
		System.out.println(cvo.getCourse_level());

		return "client/courseApply/courseApply";

	}

	/* 강의신청 상세보기 페이지 호출 */
	@RequestMapping(value = "/applyDetail.do", method = RequestMethod.GET)
	public String requestCourseDetail(@ModelAttribute("CourseVO") CourseVO cvo, Model model) {

		logger.info("강의신청 상세보기 페이지 호출!");
		logger.info("강의번호 : " + cvo.getCourse_no());
		
		int limit = 0;
		
		CourseVO detail = new CourseVO();
		
		detail = requestCourseService.requestCourseDetail(cvo);
		limit = requestCourseService.requestCourseCount(cvo);
		
		model.addAttribute("courseDetail", detail);

		return "client/courseApply/courseApplyDetail";
	}

	/* 강의신청 신청 및 결제 페이지 호출 */
	@RequestMapping(value = "/applyPayment.do", method = RequestMethod.POST)
	public String requestCoursePayment(Model model) {

		logger.info("강의신청 및 결제 페이지 호출");

		return "client/courseApply/courseApplyPayment";

	}
	
	/*강의신청 결제 확인 페이지*/
	@RequestMapping(value="/applyConfirm.do", method=RequestMethod.POST)
	public String requestCourseConfirm(Model model) {
		
		logger.info("강의신청 결제 확인페이지");
		
		return "client/courseApply/courseApplyConfirm";
	}

}
