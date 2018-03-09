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

	/* 학부모 mypage */
	@RequestMapping(value = "/parentMypage.do", method = RequestMethod.GET)
	public String parentMypage(Model model) {

		logger.info("학부모 mypage 호출");

		return "client/mypage/mypageParent";
	}

	/*
	 * 마이페이지 버튼 클릭시 post방식으로 로그인한 학부모 번호 가져와서 db들러서 학부모 번호로 학생list 뽑음 list없으면 이거 있으면
	 * 저거
	 */

	/* 정보수정 버튼 클릭시 비밀번호 체크 페이지 */
	@RequestMapping(value = "/modifyCheckPw.do", method = RequestMethod.GET)
	public String modifyCheckPw() {

		logger.info("비밀번호 확인창 호출!");

		return "client/mypage/modifyCheckPw";
	}

	/* 비밀번호 확인 버튼 클릭시 처리 */
	/* 정보수정 버튼 클릭시 처리 */
	@RequestMapping(value = "/modifyinfo.do", method = RequestMethod.POST)
	public ModelAndView userModify(@ModelAttribute("ParentVO") ParentVO pvo, @ModelAttribute("StudentVO") StudentVO svo,
			HttpSession session) {

		logger.info("비밀번호 확인!");

		ModelAndView mav = new ModelAndView();

		UserLoginVO uLogin = (UserLoginVO) session.getAttribute("Login");

		if (uLogin == null) {
			mav.setViewName("login/login.do");
			return mav;
		}

		logger.info("각 수정창으로!");

		if (uLogin.getUserId().contains("@")) {
			ParentVO vo = parentJoinService.parentSelect(uLogin.getUserId());
			mav.addObject("parent", vo);
			mav.setViewName("client/member/modifyParentInfo");
		} else {
			StudentVO vo = studentJoinService.studentSelect(uLogin.getUserId());
			mav.addObject("student", vo);
			mav.setViewName("client/member/modifyStudentInfo");
		}

		return mav;

	}

	/* 학생 mypage */
	@RequestMapping(value = "/studentMypage.do", method = RequestMethod.GET)
	public String studentMypage(Model model) {

		logger.info("학생 mypage 호출");

		return "client/mypage/mypageStudent";

	}

}
