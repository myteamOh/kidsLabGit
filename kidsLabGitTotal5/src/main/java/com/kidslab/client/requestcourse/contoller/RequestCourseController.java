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

		logger.info("강의신청 목록 호출");

		List<CourseVO> requestCourseList = requestCourseService.requestCourseList(cvo);

		model.addAttribute("requestCourseList", requestCourseList);
		model.addAttribute("courseData", cvo);
		System.out.println(cvo.getCourse_level());

		return "client/courseApply/courseApply";

	}

	/* 강의신청 상세보기 페이지 호출 */
	@RequestMapping(value = "/applyDetail", method = RequestMethod.GET)
	public String requestCourseDetail(@ModelAttribute("CourseVO") CourseVO cvo, Model model) {

		logger.info("강의신청 상세보기 페이지 호출!");
		logger.info("강의번호 : " + cvo.getCourse_no());

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

		logger.info("강의신청 및 결제 페이지 호출");

		ModelAndView mav = new ModelAndView();

		System.out.println("course_no : " + cvo.getCourse_no());
		System.out.println("parent_no : " + pvo.getParent_no());

		pvo = (ParentVO) session.getAttribute("Login");
		System.out.println("parent_no : " + pvo.getParent_no());

		CourseVO detail = new CourseVO();
		int limit = 0;

		detail = requestCourseService.requestCourseDetail(cvo);
		limit = requestCourseService.requestCourseCount(cvo);
		List<StudentVO> studentList = studentJoinService.studentList(pvo.getParent_no());
		
		System.out.println(limit);

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

		logger.info("쳌!");

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

		logger.info("강의신청 결제 확인페이지");

		System.out.println("결제방법 : " + rcvo.getRequestcourse_paymethod());
		System.out.println("결제금액 : " + rcvo.getRequestcourse_payamount());
		System.out.println("학부모 번호 : " + rcvo.getParent_no());
		System.out.println("학생 번호 : " + rcvo.getStudent_no());
		System.out.println("강의 번호 : " + rcvo.getCourse_no());
		System.out.println("결제방법 : " + rcvo.getRequestcourse_paymethod());
		System.out.println("선택 그시기 : " + rcvo.getBank_and_account());

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

		logger.info("강의신청 완료!");

		return "index";
	}

}
