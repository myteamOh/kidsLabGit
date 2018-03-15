package com.kidslab.client.notice.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.admin.notice.service.NoticeService;
import com.kidslab.admin.notice.vo.NoticeVO;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;

@Controller
@RequestMapping(value = "/notice")

public class ClientNoticeController {

	Logger logger = Logger.getLogger(ClientNoticeController.class);

	@Autowired
	private NoticeService noticeService;

	/*****************************************
	 * 글 목록
	 *****************************************/
	@RequestMapping(value = "/noticeList")
	public String noticeList(@ModelAttribute NoticeVO nvo, Model model) {

		logger.info("client용 noticeList 호출");

		// 페이지 세팅
		Paging.setPage(nvo);

		// 전체 레코드 수
		int total = noticeService.noticeListCnt(nvo);
		logger.info("total = " + total);

		// 글번호 재설정
		int count = total - (Util.nvl(nvo.getPage()) - 1) * Util.nvl(nvo.getPageSize());
		logger.info("count = " + count);

		List<NoticeVO> noticeList = noticeService.noticeList(nvo);

		model.addAttribute("noticeList", noticeList);

		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("noticeData", nvo);
		logger.info("page : " + nvo.getPage());

		return "client/notice/noticeList";

	}

	/***********************************
	 * 글 상세보기
	 ***********************************/
	@RequestMapping(value = "/noticeDetail", method = RequestMethod.GET)
	public String noticeDetail(@ModelAttribute NoticeVO nvo, Model model) {

		logger.info("client 용 noticeDetail 호출 ");

		logger.info("notice_no = " + nvo.getNotice_no());

		NoticeVO detail = new NoticeVO();
		detail = noticeService.noticeDetail(nvo);

		model.addAttribute("detail", detail);

		return "client/notice/noticeDetail";
	}

}
