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
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {

		logger.info("로그인폼 출력!");

		return "client/member/login";
	}

	/* 로그인 처리 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute("ParentVO") ParentVO pvo, @ModelAttribute("StudentVO") StudentVO svo,
			HttpSession session, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		/* 입력한 아이디가 이메일인지 아닌지를 @로 구분. 학부모 - 이메일, 학생 - 영문숫자조합 */
		if (pvo.getUserId().contains("@")) {

			// 학부모일때 로그인
			ParentVO parentLogin = loginService.loginSelect(pvo.getUserId(), pvo.getUserPw());

			// 입력한 정보가 존재하고 계정상태가 가입중일때 로그인.
			if (parentLogin != null && parentLogin.getParent_status().equals("가입")) {

				session.setAttribute("Login", parentLogin);
				mav.addObject("data", parentLogin);
				mav.setViewName("redirect:/");
				return mav;
			}
		} else {

			// 학생일떄 로그인
			StudentVO studentLogin = loginService.loginSelectS(svo.getUserId(), svo.getUserPw());

			if (studentLogin != null && studentLogin.getStudent_status().equals("가입")) {

				session.setAttribute("Login", studentLogin);
				mav.addObject("data", studentLogin);
				mav.setViewName("redirect:/");
				return mav;
			}
		}

		//
		mav.setViewName("client/member/login");
		mav.addObject("msg", "fail");
		return mav;

	}

	/* 로그아웃 처리 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, HttpServletRequest request) {

		session.invalidate();
		session = request.getSession(true);

		return "redirect:/"; // 메인페이지로 경로 바꿔주세요.
	}

}
