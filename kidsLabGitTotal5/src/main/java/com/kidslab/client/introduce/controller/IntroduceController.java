package com.kidslab.client.introduce.controller;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/client")
public class IntroduceController {
	Logger logger = Logger.getLogger(IntroduceController.class);

	/**************************************************************
	 * KIDSLAB 회사 소개
	 **************************************************************/
	@RequestMapping(value = "/introduce/lab")
	public String introduceLab() {
		logger.info("/inquiry/lab 호출 성공");
		return "client/introduce/lab"; // lab.jsp로 이동
	}

	/**************************************************************
	 * KIDSLAB 회사 소개
	 **************************************************************/
	@RequestMapping(value = "/introduce/teacher")
	public String introduceTeacher() {
		logger.info("/introduce/teacher 호출 성공");
		return "client/introduce/teacher"; // teacher.jsp로 이동
	}

	/**************************************************************
	 * KIDSLAB 회사 소개
	 **************************************************************/
	@RequestMapping(value = "/introduce/waytocome")
	public String introduceWaytocome() {
		logger.info("/introduce/waytocome 호출 성공");
		return "client/introduce/waytocome"; // waytocome.jsp로 이동
	}

	/**************************************************************
	 * KIDSLAB 회사 소개
	 **************************************************************/
	@RequestMapping(value = "/introduce/cource")
	public String inquiryWriteForm() {
		logger.info("/introduce/cource 호출 성공");
		return "client/introduce/cource"; // cource.jsp로 이동
	}

}
