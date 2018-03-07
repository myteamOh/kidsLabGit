package com.kidslab.client.mypage.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

	Logger logger = Logger.getLogger(MypageController.class);

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

}
