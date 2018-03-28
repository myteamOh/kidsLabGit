package com.kidslab.admin.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.login.service.AdminLoginService;
import com.kidslab.admin.login.vo.AdminLoginVO;

@Controller
@RequestMapping("/admin")
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	AdminLoginService adminLoginService;

	/*****************************************
	 * 로그인 화면 보여주기 위한 메소드
	 ********************************************/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.info("login.do get 호출 성공");
		return "admin/login/login";
	}

	/*****************************************
	 * 로그인 처리 메소드 참고 : 로그인 실패시 처리 내용 포함.
	 ********************************************/
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute("LoginVO") AdminLoginVO vo, HttpSession session) {
		logger.info("admin/loginCheck.do POST 호출 성공");
		ModelAndView mav = new ModelAndView();

		String userId = vo.getUserId();

		System.out.println(userId);

		AdminLoginVO loginCheckResult = adminLoginService.loginSelect(vo.getUserId(), vo.getUserPw());

		if (loginCheckResult == null) {
			mav.addObject("msg", "fail");
			mav.setViewName("admin/login/login");
			return mav;
		} else {
			session.setAttribute("adminLogin", loginCheckResult);
			mav.setViewName("redirect:/admin/payment/paymentList");
			return mav;
		}

	}

	/**************************************
	 * 로그아웃 처리 메소드
	 **************************************/
	@RequestMapping("/logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		session = request.getSession(true);
		return "redirect:/admin/login";
	}
}
