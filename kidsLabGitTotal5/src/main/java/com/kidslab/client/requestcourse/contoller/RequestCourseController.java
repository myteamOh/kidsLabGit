package com.kidslab.client.requestcourse.contoller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.requestcourse.service.RequestCourseService;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.client.studentjoin.service.StudentJoinService;

@Controller
@RequestMapping(value = "/requestcourse")
public class RequestCourseController {

	Logger logger = Logger.getLogger(RequestCourseController.class);

	@Autowired
	private StudentJoinService studentJoinService;

	@Autowired
	private RequestCourseService requestCourseService;

	/* 강의신청 목록조회 페이지 호출 */
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public String requestCourseList(@ModelAttribute("CourseVO") CourseVO cvo, Model model) {

		List<CourseVO> requestCourseList = requestCourseService.requestCourseList(cvo);

		model.addAttribute("requestCourseList", requestCourseList);
		model.addAttribute("courseData", cvo);

		return "client/courseApply/courseApply";

	}

	/* 강의신청 상세보기 페이지 호출 */
	@RequestMapping(value = "/applyDetail", method = RequestMethod.GET)
	public String requestCourseDetail(@ModelAttribute("CourseVO") CourseVO cvo, Model model) {

		int limit = 0;

		CourseVO detail = new CourseVO();

		detail = requestCourseService.requestCourseDetail(cvo);
		limit = requestCourseService.requestCourseCount(cvo);

		model.addAttribute("courseDetail", detail);
		model.addAttribute("courseCount", limit);

		return "client/courseApply/courseApplyDetail";
	}

	/* 강의신청 신청 및 결제 페이지 호출 */
	@RequestMapping(value = "/applyNPayment", method = RequestMethod.POST)
	public ModelAndView requestCoursePayment(@ModelAttribute("ParentVO") ParentVO pvo,
			@ModelAttribute("CourseVO") CourseVO cvo, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		pvo = (ParentVO) session.getAttribute("Login");

		CourseVO detail = new CourseVO();
		int limit = 0;

		detail = requestCourseService.requestCourseDetail(cvo);
		limit = requestCourseService.requestCourseCount(cvo);
		
		List<StudentVO> studentList = studentJoinService.studentList(pvo.getParent_no());

		mav.addObject("cDetail", detail);
		mav.addObject("cCount", limit);
		mav.addObject("studentList", studentList);
		mav.setViewName("client/courseApply/courseApplyPayment");

		return mav;

	}

	/* 강의신청 가능여부 체크 */
	@ResponseBody
	@RequestMapping(value = "/applyConfirmCheck", method = RequestMethod.POST)
	public int requestCourseConfirmCheck(@ModelAttribute("RequestCourseVO") RequestCourseVO rcvo) {

		int result = 0;

		/* insert의 result값은 성공시 1 */
		/* 1은 결제성공 0은 실패 */

		if (requestCourseService.reCourseSelectByNo(rcvo).isEmpty()) {
			result = 1;
		} else {
			result = 0;
		}

		return result;

	}

	/* 강의신청 후 결제 확인 페이지 */
	@RequestMapping(value = "/applyConfirm", method = RequestMethod.POST)
	public ModelAndView requestCourseConfirm(@ModelAttribute("RequestCourseVO") RequestCourseVO rcvo) {

		ModelAndView mav = new ModelAndView();

		RequestCourseVO vo = null;

		/* 데이터 변환 */
		if (rcvo.getRequestcourse_paymethod().equals("bankbookDeposit")) {
			rcvo.setRequestcourse_paymethod("무통장입금");
		}

		requestCourseService.requestCourseInsert(rcvo);
		
		vo = requestCourseService.reCourseSelectByNo(rcvo).get(0);
		
		vo.setBank_and_account(rcvo.getBank_and_account());

		mav.addObject("requestcoursedata", vo);
		mav.setViewName("client/courseApply/courseApplyConfirm");

		return mav;
	}

	/* 강의신청 완료후 메인페이지로 */
	@RequestMapping(value = "/applyComplete")
	public String applyComplete(Model model) {
		return "index";
	}

}
