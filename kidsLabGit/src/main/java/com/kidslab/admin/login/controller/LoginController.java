package com.kidslab.admin.login.controller;

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

import com.kidslab.admin.login.vo.LoginVO;

@Controller
@RequestMapping("/admin")
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);

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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginProc(@ModelAttribute("LoginVO") LoginVO lvo, HttpSession session,
			HttpServletRequest request) {
		logger.info("login.do post 호출 성공");
		ModelAndView mav = new ModelAndView();

		String userId = lvo.getUserId();
		return mav;
	}
}
