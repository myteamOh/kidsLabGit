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
public class LoginController {

	Logger logger = Logger.getLogger(LoginController.class);

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

				session.setAttribute("login", parentLogin); // parentVO에 대한 정보만 들고있음. id pw 는 없음.
				mav.addObject("data", parentLogin);
				mav.setViewName("client/mypage/mypageParent"); // 로그인 성공시 학부모 마이페이지로. 메인 완성되면 메인으로
				return mav;
			}
		} else {

			StudentVO studentLogin = loginService.loginSelectS(svo.getUserId(), svo.getUserPw());

			if (studentLogin != null) {

				session.setAttribute("login", studentLogin);
				mav.addObject("data", studentLogin);
				mav.setViewName("client/mypage/mypageStudent"); // 로그인 성공시 학생 마이페이지로. 메인 완성되면 메인으로
				return mav;
			}
		}

		mav.setViewName("client/member/login"); // 로그인 실패시 로그인 페이지 그대로.
		mav.addObject("msg", "fail");
		return mav;

	}

	/* 로그아웃 처리 */
	@RequestMapping("/logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {

		session.invalidate();
		session = request.getSession(true);

		return "redirect:/login/login.do"; // 메인페이지로 경로 바꿔주세요.
	}

}
