package com.kidslab.admin.login.controller;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.login.service.LoginService;
import com.kidslab.admin.login.vo.LoginVO;

@Controller
@RequestMapping("/admin")
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);

	@Inject
	LoginService loginService;

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
	public ModelAndView loginCheck(@ModelAttribute("LoginVO") LoginVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String userId = vo.getUserId();
		System.out.println(userId);
		if (userId.equals(loginService.loginCheck(vo))) {
			// 로그인 성공
			// main.jsp로 이동
			mav.setViewName("admin/login/loginSuccess");
			mav.addObject("msg", "success");
			session.setAttribute("adminLogin", userId);
		} else {
			// 로그인 실패
			// login.jsp로 이동
			mav.setViewName("admin/login/login");
			mav.addObject("msg", "failure");
		}
		return mav;
	}
}
