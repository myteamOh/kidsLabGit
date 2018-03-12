package com.kidslab.client.mypage.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.client.login.service.LoginService;
import com.kidslab.client.login.vo.UserLoginVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.parentjoin.service.ParentJoinService;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.client.studentjoin.service.StudentJoinService;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

	Logger logger = Logger.getLogger(MypageController.class);

	@Autowired
	private ParentJoinService parentJoinService;

	@Autowired
	private StudentJoinService studentJoinService;

	@Autowired
	private LoginService loginService;

	/* 학부모 mypage */
	@RequestMapping(value = "/parentMypage.do", method = RequestMethod.GET)
	public String parentMypage(Model model) {

		logger.info("학부모 mypage 호출");

		return "client/mypage/mypageParent";
	}

	/* 학생 mypage */
	@RequestMapping(value = "/studentMypage.do", method = RequestMethod.GET)
	public String studentMypage(Model model) {

		logger.info("학생 mypage 호출");

		return "client/mypage/mypageStudent";

	}

	/*
	 * 마이페이지 버튼 클릭시 post방식으로 로그인한 학부모 번호 가져와서 db들러서 학부모 번호로 학생list 뽑음 list없으면 이거 있으면
	 * 저거
	 */

	/* 정보수정 버튼 클릭시 비밀번호 체크 페이지 */
	@RequestMapping(value = "/modifyCheckPw.do", method = RequestMethod.GET)
	public String modifyCheckPw(Model model) {

		logger.info("비밀번호 확인창 호출!");

		return "client/mypage/modifyCheckPw";
	}

	/* 비밀번호 확인 버튼 클릭시 처리 */
	@RequestMapping(value = "/modifyinfo.do", method = RequestMethod.POST)
	public ModelAndView userModify(@ModelAttribute("ParentVO") ParentVO pvo, @ModelAttribute("StudentVO") StudentVO svo,
			HttpSession session) {

		logger.info("비밀번호 확인!");

		ModelAndView mav = new ModelAndView();

		UserLoginVO uLogin = (UserLoginVO) session.getAttribute("Login");

		if (uLogin == null) {
			mav.setViewName("client/member/login");
			return mav;
		}

		if (uLogin.getUserId().contains("@")) {

			logger.info("각 수정창으로!");

			ParentVO parentLogin = loginService.loginSelect(pvo.getUserId(), pvo.getUserPw());

			if (parentLogin != null) {

				ParentVO vo = parentJoinService.parentSelect(uLogin.getUserId());
				mav.addObject("parent", vo);
				mav.setViewName("client/member/modifyParentInfo");

				return mav;
			}
		} else {

			logger.info("각 수정창으로!");

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
	@RequestMapping(value = "/parentModifyInfo.do", method = RequestMethod.POST)
	public ModelAndView parentModifyInfo(@ModelAttribute("ParentVO") ParentVO pvo, HttpSession session) {

		logger.info("학부모 정보 수정");

		ModelAndView mav = new ModelAndView();

		UserLoginVO login = (UserLoginVO) session.getAttribute("Login");
		
		System.out.println(pvo.getUserPw());

		if (login == null) {
			mav.setViewName("client/member/login");
			return mav;
		}

		pvo.setUserId(login.getUserId());
		ParentVO vo = parentJoinService.parentSelect(pvo.getUserId());
		if (parentJoinService.parentUpdate(pvo)) {
			mav.setViewName("redirect:/login/logout.do");
			return mav;
		} else {
			mav.addObject("Login", vo);
			mav.setViewName("client/member/modifyParentInfo");
			return mav;
		}
	}

	/* 학부모 탈퇴 버튼 클릭시 처리 */

	/* 학생 수정완료 버튼 클릭시 처리 */
	@RequestMapping(value = "/studentModifyInfo.do", method = RequestMethod.POST)
	public ModelAndView studentModifyInfo(@ModelAttribute("StudentVO") StudentVO svo, HttpSession session) {

		logger.info("학생 정보 수정");

		ModelAndView mav = new ModelAndView();

		UserLoginVO login = (UserLoginVO) session.getAttribute("Login");

		if (login == null) {
			mav.setViewName("client/member/login");
			return mav;
		}

		svo.setUserId(login.getUserId());
		StudentVO vo = studentJoinService.studentSelect(svo.getUserId());
		if (studentJoinService.studentUpdate(svo)) {
			mav.setViewName("redirect:/login/logout.do");
			return mav;
		} else {
			mav.addObject("Login", vo);
			mav.setViewName("client/member/modifyStudentInfo");
			return mav;
		}

	}

}
