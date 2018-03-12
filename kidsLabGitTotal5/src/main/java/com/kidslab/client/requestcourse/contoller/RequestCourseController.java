package com.kidslab.client.requestcourse.contoller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.client.requestcourse.service.RequestCourseService;

@Controller
@RequestMapping(value = "/requestcourse")
public class RequestCourseController {

	Logger logger = Logger.getLogger(RequestCourseController.class);

	@Autowired
	private RequestCourseService requestCourseService;

	/* 강의신청 목록조회 페이지 호출 */
	@RequestMapping(value = "/apply.do", method = RequestMethod.GET)
	public String requestCourseList(Model model) {

		logger.info("강의신청 목록 호출");

		return "client/courseApply/courseApply";

	}

	/* 강의신청 상세보기 페이지 호출 */
	@RequestMapping(value = "/applyDetail.do", method = RequestMethod.GET)
	public String requestCourseDetail(Model model) {

		logger.info("강의신청 상세보기 페이지 호출!");

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
