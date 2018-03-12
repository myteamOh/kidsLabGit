package com.kidslab.admin.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping(value = "/admin")
public class NoticeController {

	Logger logger = Logger.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

	/*****************************************
	 * 글 목록
	 *****************************************/
	@RequestMapping(value = "/notice/noticeList")
	public String noticeList(@ModelAttribute NoticeVO nvo, Model model) {
		logger.info("noticeList 호출~~");

		// 페이지 세팅 (추가)
		Paging.setPage(nvo);

		// 전체 레코드 수 (추가)
		int total = noticeService.noticeListCnt(nvo);
		logger.info("total = " + total);

		// 글번호 재설정 (추가)
		int count = total - (Util.nvl(nvo.getPage()) - 1) * Util.nvl(nvo.getPageSize());
		logger.info("count = " + count);

		List<NoticeVO> noticeList = noticeService.noticeList(nvo);

		model.addAttribute("noticeList", noticeList);

		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("noticeData", nvo);
		logger.info("page : " + nvo.getPage());

		return "admin/notice/noticeList";
	}

	/*******************************
	 * 글 쓰기 폼 출력
	 *******************************/
	@RequestMapping(value = "/notice/writeForm")
	public String writeForm() {
		logger.info("writeForm 호출!~~");

		return "admin/notice/writeForm";
	}

	/**********************************
	 * 글쓰기 POST
	 **********************************/
	@RequestMapping(value = "/notice/noticeInsert", method = RequestMethod.POST)
	public String noticeInsert(@ModelAttribute NoticeVO nvo, Model model, HttpServletRequest request)
			throws IllegalStateException {

		logger.info("noticeInsert 호출 ~!~!");
		logger.info("admin_no : " + nvo.getAdmin_no());
		logger.info("notice_content : " + nvo.getNotice_content());
		logger.info("notice_title : " + nvo.getNotice_title());
		int result = 0;
		String url = "";

		result = noticeService.noticeInsert(nvo);

		if (result == 1) {
			url = "noticeList";
		} else {
			model.addAttribute("code", 1);
			url = "writeForm";
		}

		return "redirect:" + url;
	}

	/***********************************
	 * 글 상세보기
	 ***********************************/
	@RequestMapping(value = "/notice/noticeDetail", method = RequestMethod.GET)
	public String noticeDetail(@ModelAttribute NoticeVO nvo, Model model) {

		logger.info("noticeDetail 호출 성공");

		logger.info("notice_no = " + nvo.getNotice_no());

		NoticeVO detail = new NoticeVO();
		detail = noticeService.noticeDetail(nvo);

		model.addAttribute("detail", detail);

		return "admin/notice/noticeDetail";
	}

	/*********************************
	 * 글 수정 폼 출력
	 *********************************/
	@RequestMapping(value = "/notice/updateForm")
	public String updateForm(@ModelAttribute NoticeVO nvo, Model model) {
		logger.info("updateForm 호출~~!~!~");

		logger.info("notice_no = " + nvo.getNotice_no());

		NoticeVO updateData = new NoticeVO();
		updateData = noticeService.noticeDetail(nvo);

		model.addAttribute("updateData", updateData);

		return "admin/notice/updateForm";
	}

	/**********************************
	 * 글 수정 POST
	 **********************************/
	@RequestMapping(value = "/notice/noticeUpdate", method = RequestMethod.POST)
	public String noticeUpdate(@ModelAttribute NoticeVO nvo, HttpServletRequest request) throws IllegalStateException {
		logger.info("noticeUpdate POST 호출 성공~~~~");

		int result = 0;
		String url = "";

		result = noticeService.noticeUpdate(nvo);
		logger.info("result : " + result);
		if (result == 1) {
			url = "/admin/notice/noticeDetail.do?notice_no=" + nvo.getNotice_no() + "&page=" + nvo.getPage()
					+ "&pageSize=" + nvo.getPageSize();
		}

		return "redirect:" + url;

	}

	/*****************************************
	 * 글 삭제
	 *****************************************/
	@RequestMapping(value = "/notice/noticeDelete", method = RequestMethod.POST)
	public String noticeDelete(@ModelAttribute NoticeVO nvo, HttpServletRequest request) {

		logger.info("noticeDelete 호출 성공");

		int result = 0;
		String url = "";

		result = noticeService.noticeDelete(nvo.getNotice_no());

		if (result == 1) {
			url = "/admin/notice/noticeList.do?page=" + nvo.getPage() + "&pageSize=" + nvo.getPageSize();
		} else {
			url = "/admin/notice/noticeDetail.do?notice_no=" + nvo.getNotice_no() + "&page=" + nvo.getPage()
					+ "&pageSize=" + nvo.getPageSize();
		}
		return "redirect:" + url;
	}
}
