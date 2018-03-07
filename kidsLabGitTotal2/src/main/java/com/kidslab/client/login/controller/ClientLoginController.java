package com.kidslab.client.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.client.login.service.LoginService;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

@Controller
@RequestMapping(value = "/login")
public class ClientLoginController {

	Logger logger = Logger.getLogger(ClientLoginController.class);

	@Autowired
	private LoginService loginService;

	/*
	 * 로그인 Form
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginForm() {

		logger.info("로그인폼 출력!");

		return "client/member/login";
	}

	/* 로그인 처리 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute("ParentVO") ParentVO pvo, @ModelAttribute("StudentVO") StudentVO svo,
			HttpSession session, HttpServletRequest request) {

		logger.info("로그인 처리!");

		ModelAndView mav = new ModelAndView();

		if (pvo.getUserId().contains("@")) {

			ParentVO parentLogin = loginService.loginSelect(pvo.getUserId(), pvo.getUserPw());

			if (parentLogin != null) {

				session.setAttribute("Login", parentLogin); // parentVO에 대한 정보만 들고있음. id pw 는 없음.
				mav.addObject("data", parentLogin);
				mav.setViewName("client/mypage/mypageParent");
				return mav;
			}
		} else {

			StudentVO studentLogin = loginService.loginSelectS(svo.getUserId(), svo.getUserPw());

			if (studentLogin != null) {

				session.setAttribute("Login", studentLogin);
				mav.addObject("data", studentLogin);
				mav.setViewName("client/mypage/mypageStudent");
				return mav;
			}
		}

		mav.setViewName("client/member/login");
		mav.addObject("msg", "fail");
		mav.addObject("data", pvo);
		return mav;

	}

}
