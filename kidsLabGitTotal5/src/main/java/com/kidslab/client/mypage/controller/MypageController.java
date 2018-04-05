package com.kidslab.client.mypage.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.client.login.service.LoginService;
import com.kidslab.client.login.vo.UserLoginVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.parentjoin.service.ParentJoinService;
import com.kidslab.client.requestcourse.service.RequestCourseService;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.client.studentjoin.service.StudentJoinService;
import com.kidslab.common.file.FileViewAction;
import com.sun.xml.internal.ws.api.model.ExceptionType;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

	private FileViewAction fileViewAction;

	public void setFileViewAction(FileViewAction fileViewAction) {
		this.fileViewAction = fileViewAction;
	}

	Logger logger = Logger.getLogger(MypageController.class);

	@Autowired
	private ParentJoinService parentJoinService;

	@Autowired
	private StudentJoinService studentJoinService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private RequestCourseService rcService;

	/* 학부모 mypage */
	@RequestMapping(value = "/parentMypage", method = RequestMethod.GET)
	public String parentMypage(@ModelAttribute ParentVO pvo, Model model, HttpSession session) {

		pvo = (ParentVO) session.getAttribute("Login");

		RequestCourseVO rcvo = new RequestCourseVO();

		List<StudentVO> studentList = studentJoinService.studentList(pvo.getParent_no());

		/*--------------------------------------기본---------------------------------------------*/

		List<RequestCourseVO> requestCourseList = new ArrayList<>();

		Map<StudentVO, List<RequestCourseVO>> rcListMap = new LinkedHashMap<>();

		for (int i = 0; i < studentList.size(); i++) {
			rcvo.setStudent_no(studentList.get(i).getStudent_no());
			requestCourseList = rcService.reCourseSelectByNo(rcvo);
			rcListMap.put(studentList.get(i), requestCourseList);
		}

		model.addAttribute("rcListMap", rcListMap);

		return "client/mypage/mypageParent";
	}

	/* 학생 mypage */
	@RequestMapping(value = "/studentMypage", method = RequestMethod.GET)
	public String studentMypage(@ModelAttribute("RequestCourseVO") RequestCourseVO rcvo, Model model,
			HttpSession session) {

		StudentVO svo = (StudentVO) session.getAttribute("Login");

		rcvo.setStudent_no(svo.getStudent_no());

		List<RequestCourseVO> requestCourseList = rcService.reCourseSelectByNo(rcvo);

		model.addAttribute("rcList", requestCourseList);

		return "client/mypage/mypageStudent";

	}

	/*
	 * 마이페이지 버튼 클릭시 post방식으로 로그인한 학부모 번호 가져와서 db들러서 학부모 번호로 학생list 뽑음 list없으면 이거 있으면
	 * 저거
	 */

	/* 정보수정 버튼 클릭시 비밀번호 체크 페이지 */
	@RequestMapping(value = "/modifyCheckPw", method = RequestMethod.POST)
	public ModelAndView modifyCheckPw(Model model) {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("client/mypage/modifyCheckPw");

		return mav;
	}

	/* 비밀번호 확인 버튼 클릭시 처리 */
	@RequestMapping(value = "/modifyinfo", method = RequestMethod.POST)
	public ModelAndView userModify(@ModelAttribute("ParentVO") ParentVO pvo, @ModelAttribute("StudentVO") StudentVO svo,
			HttpSession session) {

		ModelAndView mav = new ModelAndView();

		UserLoginVO uLogin = (UserLoginVO) session.getAttribute("Login");

		if (uLogin == null) {
			mav.setViewName("client/member/login");
			return mav;
		}

		if (uLogin.getUserId().contains("@")) {

			ParentVO parentLogin = loginService.loginSelect(pvo.getUserId(), pvo.getUserPw());

			if (parentLogin != null) {

				ParentVO vo = parentJoinService.parentSelect(uLogin.getUserId());
				mav.addObject("parent", vo);
				mav.setViewName("client/member/modifyParentInfo");

				return mav;
			}
		} else {

			StudentVO studentLogin = loginService.loginSelectS(svo.getUserId(), svo.getUserPw());

			if (studentLogin != null) {

				StudentVO vo = studentJoinService.studentSelect(uLogin.getUserId());
				mav.addObject("student", vo);
				mav.setViewName("client/member/modifyStudentInfo");

				return mav;
			}
		}

		mav.setViewName("client/mypage/modifyCheckPw");
		mav.addObject("msg", "fail");

		return mav;

	}

	/* 학부모 수정완료 버튼 클릭시 처리 */
	@RequestMapping(value = "/parentModifyInfo", method = RequestMethod.POST)
	public ModelAndView parentModifyInfo(@ModelAttribute("ParentVO") ParentVO pvo, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		UserLoginVO login = (UserLoginVO) session.getAttribute("Login");

		if (login == null) {
			mav.setViewName("client/member/login");
			return mav;
		}

		pvo.setUserId(login.getUserId());
		
		ParentVO vo = parentJoinService.parentSelect(pvo.getUserId());
		
		if (parentJoinService.parentUpdate(pvo)) {
			mav.setViewName("redirect:/login/logout");
			return mav;
		} else {
			mav.addObject("Login", vo);
			mav.setViewName("client/member/modifyParentInfo");
			return mav;
		}
	}

	/* 학부모 탈퇴 버튼 클릭시 탈퇴여부 체크 */
	@ResponseBody
	@RequestMapping(value = "/parentWithdrawCheck", method = RequestMethod.POST)
	public String parentWithdrawCheck(@ModelAttribute("ParentVO") ParentVO pvo) {

		String result = null;

		result = rcService.withdrawCheck(pvo) + "";

		return result;

	}

	/* 학부모 탈퇴버튼 클릭시 탈퇴 처리 */
	@RequestMapping(value = "/parentWithdraw", method = RequestMethod.POST)
	public String parentWithdraw(@ModelAttribute("ParentVO") ParentVO pvo, HttpSession session) {

		ParentVO vo = (ParentVO) session.getAttribute("Login");

		pvo.setParent_no(vo.getParent_no());

		parentJoinService.parentWithdraw(pvo);
		studentJoinService.studentWithdraw(pvo);

		return "redirect:/login/logout";
	}

	/* 학생 수정완료 버튼 클릭시 처리 */
	@RequestMapping(value = "/studentModifyInfo", method = RequestMethod.POST)
	public ModelAndView studentModifyInfo(@ModelAttribute("StudentVO") StudentVO svo, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		UserLoginVO login = (UserLoginVO) session.getAttribute("Login");

		if (login == null) {
			mav.setViewName("client/member/login");
			return mav;
		}

		svo.setUserId(login.getUserId());
		StudentVO vo = studentJoinService.studentSelect(svo.getUserId());
		
		if (studentJoinService.studentUpdate(svo)) {
			mav.setViewName("redirect:/login/logout");
			return mav;
		} else {
			mav.addObject("Login", vo);
			mav.setViewName("client/member/modifyStudentInfo");
			return mav;
		}

	}

	/* 학부모 mypage 수강 신청 취소 버튼 클릭시 처리 */
	@RequestMapping(value = "/applyCancel", method = RequestMethod.POST)
	public String applyCancel(@ModelAttribute RequestCourseVO rcvo) {

		int result = 0;

		result = rcService.requestCourseDelete(rcvo.getRequestcourse_no());

		return "redirect:/mypage/parentMypage";

	}

	/* 학부모 mypage 수강취소, 수강포기 버튼 클릭시 처리 */
	@RequestMapping(value = "/courseCancel", method = RequestMethod.POST)
	public ModelAndView courseCancel(@ModelAttribute RequestCourseVO rcvo) {

		ModelAndView mav = new ModelAndView();
		RequestCourseVO vo = new RequestCourseVO();

		vo = rcService.reCourseSelectOne(rcvo);

		/*
		 * 시작전 전액, 1/3전: 2/3반환, 1/2전: 1/2반환, 1/2후: 반환x
		 */

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			/* 오늘날짜 구하기 */
			String today = formatter.format(new Date()); // 오늘날짜

			Date todayDate = formatter.parse(today); // 오늘날짜 변환

			/* 날짜차이 구하기 */
			Date beginDate = formatter.parse(vo.getCourse_startdate()); // 강의 시작 날짜 변환
			Date endDate = formatter.parse(vo.getCourse_enddate()); // 강의 종료 날짜 변환

			long coursePeriod = endDate.getTime() - beginDate.getTime(); // 교습기간
			long beginToToday = todayDate.getTime() - beginDate.getTime(); // 경과기간

			long coursePeriodDays = coursePeriod / (24 * 60 * 60 * 1000); // 시간, 분, 초, 밀리초-(1/1000)초
			long beginToTodayDays = beginToToday / (24 * 60 * 60 * 1000); // 일수

			long day1per3 = (long) Math.floor(coursePeriodDays / 3);
			long half = (long) Math.floor(coursePeriodDays / 2);

			/*System.out.println("가격확인 all : " + vo.getRequestcourse_payamount());
			System.out.println("가격확인 2/3 : " + Math.round(vo.getRequestcourse_payamount() * 2 / 3 / 1000) * 1000);
			System.out.println("가격확인 1/2 : " + Math.round(vo.getRequestcourse_payamount() / 2 / 1000) * 1000);*/

			if (beginToTodayDays <= 0) {
				// System.out.println("교습 시작 전 또는 당일 : 전액 환불");
				vo.setRequestcourse_refundcharge(vo.getRequestcourse_payamount());
				beginToTodayDays = 0;
			} else if (beginToTodayDays < day1per3) {
				// System.out.println("1/3선 전 : 2/3환불");
				vo.setRequestcourse_refundcharge(Math.round(vo.getRequestcourse_payamount() * 2 / 3 / 1000) * 1000);
			} else if (beginToTodayDays < half) {
				// System.out.println("1/2선 전 : 1/2환불");
				vo.setRequestcourse_refundcharge(Math.round(vo.getRequestcourse_payamount() / 2 / 1000) * 1000);
			} else {
				// System.out.println("1/2선 후 : 환불x");
				vo.setRequestcourse_refundcharge(0);
			}
			beginToTodayDays = beginToTodayDays + 1;
			vo.setPass_day(beginToTodayDays + "");

			/* 계산하기 위해 long값으로 변환한 데이터를 다시 날짜 객체로 변환하기 위한 방법 */
			/*
			 * Date curret = new Date(); long ttl = curret.getTime();
			 * System.out.println(ttl);
			 * 
			 * Date today2 = new Date(ttl); String str = formatter.format(today2);
			 * System.out.println("쨔잔 : " + str);
			 */

		} catch (ParseException e) {
			e.printStackTrace();
		}

		mav.addObject("refundData", vo);
		mav.setViewName("client/courseApply/courseRefund");

		return mav;
	}

	/* 환불신청버튼 클릭시 처리 */
	@RequestMapping(value = "/refundApply", method = RequestMethod.POST)
	public String refundApply(@ModelAttribute RequestCourseVO rcvo) {

		rcService.refundApply(rcvo);

		return "redirect:/mypage/parentMypage";
	}

	/** 파일 다운로드 **/
	@RequestMapping(value = "/download")
	public ModelAndView download(String course_plan, HttpSession session) {
		// 파일 객체 생성
		File download = new File("C:\\downLoad\\registCourse\\" + course_plan);
		// 출력 할 뷰 이름과 데이터의 이름을 설정하고
		// 데이터를 설정
		ModelAndView mav = new ModelAndView();
		UserLoginVO uLogin = (UserLoginVO) session.getAttribute("Login");
		if (uLogin == null) {
			mav.setViewName("client/member/login");
			return mav;
		}
		if (uLogin.getUserId().contains("@")) {
			if (download.isFile()) {
				return new ModelAndView("download", "downloadFile", download);
			} else {
				mav.setViewName("redirect:/mypage/parentMypage");
				return mav;
			}
		} else {
			if (download.isFile()) {
				return new ModelAndView("download", "downloadFile", download);
			} else {
				mav.setViewName("redirect:/mypage/studentMypage");
				return mav;
			}
		}

	}

	// 환불 리스트 체크
	@ResponseBody
	@RequestMapping(value = "/refundCheck", method = RequestMethod.GET)
	public int check(HttpSession session) {
		
		int result = 0;
		
		ParentVO uvo = new ParentVO();
		
		uvo = (ParentVO) session.getAttribute("Login");
		
		if (uvo != null) {
			result = 1;
			return result;
		} else {
			return result;
		}
	}

	// 환불 리스트
	@RequestMapping(value = "/refund", method = RequestMethod.GET)
	public String refundList(@ModelAttribute RequestCourseVO rvo, HttpSession session, Model model) {

		ParentVO uvo = new ParentVO();

		uvo = (ParentVO) session.getAttribute("Login");

		if (uvo != null) {

			List<RequestCourseVO> refundList = rcService.refundList(uvo);

			model.addAttribute("refundList", refundList);
			return "client/refundList/refundList";
		} else {
			return "redirect:/login/login";
		}

	}

}
