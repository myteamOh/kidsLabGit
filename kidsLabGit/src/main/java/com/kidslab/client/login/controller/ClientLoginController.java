package com.kidslab.client.login.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class ClientLoginController {
	Logger logger = Logger.getLogger(ClientLoginController.class);

	/*
	 * 로그인 Form
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(Model model) {

		logger.info("로그인폼 출력!");

		return "client/member/login";
	}
}
