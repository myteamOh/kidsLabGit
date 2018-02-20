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
	 * �α��� ȭ�� �����ֱ� ���� �޼ҵ�
	 ********************************************/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.info("login.do get ȣ�� ����");
		return "admin/login/login";
	}

	/*****************************************
	 * �α��� ó�� �޼ҵ� ���� : �α��� ���н� ó�� ���� ����.
	 ********************************************/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginProc(@ModelAttribute("LoginVO") LoginVO lvo, HttpSession session,
			HttpServletRequest request) {
		logger.info("login.do post ȣ�� ����");
		ModelAndView mav = new ModelAndView();

		String userId = lvo.getUserId();
		return mav;
	}
}
