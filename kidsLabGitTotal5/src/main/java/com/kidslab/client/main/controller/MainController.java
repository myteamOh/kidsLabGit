package com.kidslab.client.main.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.admin.notice.service.NoticeService;
import com.kidslab.admin.notice.vo.NoticeVO;

@Controller
public class MainController {
	Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		List<NoticeVO> mainNoticeList = noticeService.mainNoticeList();
		model.addAttribute("mainNoticeList", mainNoticeList);

		return "index";
	}
}
