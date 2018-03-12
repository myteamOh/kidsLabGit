package com.kidslab.client.inquiry.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.client.inquiry.service.InquiryService;
import com.kidslab.client.inquiry.vo.InquiryVO;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;

@Controller
@RequestMapping(value = "/inquiry")
public class InquiryController {
	Logger logger = Logger.getLogger(InquiryController.class);

	@Autowired
	private InquiryService inquiryService;

	/**************************************************************
	 * 1:1 문의 등록 폼 출력
	 **************************************************************/
	@RequestMapping(value = "/inquiryWriteForm.do")
	public String inquiryWriteForm() {
		logger.info("/inquiry/inquiryWriteForm 호출 성공");
		return "inquiry/inquiryWriteForm";
	}

	/**************************************************************
	 * 1:1 문의 등록 구현하기
	 **************************************************************/
	@RequestMapping(value = "/inquiryInsert.do", method = RequestMethod.POST)
	public String inquiryInsert(@ModelAttribute InquiryVO inquiryVO, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {
		logger.info("inquiryInsert 호출 성공");

		int result = 0;
		String url = "";

		result = inquiryService.inquiryInsert(inquiryVO);
		if (result == 1) {
			url = "/inquiry/inquiryList";
		} else {
			model.addAttribute("code", 1);
			url = "/inquiry/inquiryInsert";
		}
		return "redirect:" + url;
	}

	/**************************************************************
	 * 1:1 문의 목록 구현하기
	 **************************************************************/
	@RequestMapping(value = "/inquiryList.do", method = RequestMethod.GET)
	public String inquiryList(@ModelAttribute InquiryVO inquiryVO, Model model) {
		logger.info("inquiryList 호출 성공");

		// 페이지 세팅
		/*
		 * Paging.setPage(inquiryVO); logger.info("start : " +
		 * inquiryVO.getStart_row()); logger.info("end : " + inquiryVO.getEnd_row());
		 */

		// 전체 레코드 수 구현
		/* int total = inquiryService.inquiryListCnt(inquiryVO); */

		/* logger.info("total = " + total); */

		// 글번호 재설정
		/*
		 * int count = total - (Util.nvl(inquiryVO.getPage()) - 1) *
		 * Util.nvl(inquiryVO.getPageSize()); logger.info("count = " + count);
		 * logger.info("page : " + inquiryVO.getPage()); logger.info("search : " +
		 * inquiryVO.getSearch());
		 */

		List<InquiryVO> inquiryList = inquiryService.inquiryList(inquiryVO);
		model.addAttribute("inquiryList", inquiryList);
		/*
		 * model.addAttribute("count", count); model.addAttribute("total", total);
		 */

		model.addAttribute("data", inquiryVO);

		return "inquiry/inquiryList";
	}

	/**************************************************************
	 * 1:1 문의 상세보기
	 **************************************************************/
	@RequestMapping(value = "/inquiryDetail.do", method = RequestMethod.GET)
	public String inquiryDetail(@ModelAttribute InquiryVO inquiryVO, Model model) {
		logger.info("inquiryDetail 호출");
		logger.info("inquiry_no = " + inquiryVO.getInquiry_no());

		InquiryVO detail = new InquiryVO();
		detail = inquiryService.inquiryDetail(inquiryVO);

		if (detail != null && (!detail.equals(""))) {
			detail.setInquiry_content(detail.getInquiry_content().toString().replaceAll("\n", "<br>"));
		}
		model.addAttribute("detail", detail);
		return "inquiry/inquiryDetail";
	}

	/**************************************************************
	 * 
	 * 
	 * @param :
	 *            InquiryVO
	 * 
	 **************************************************************/
	@RequestMapping(value = "/inquiryUpdate.do", method = RequestMethod.POST)
	public String inquiryUpdate(@ModelAttribute InquiryVO inquiryVO, HttpServletRequest request)
			throws IllegalStateException, IOException, SQLException {

		logger.info("inquiryUpdate 호출 성공");

		int result = 0;
		String url = "";

		result = inquiryService.inquiryUpdate(inquiryVO);
		if (result == 1) {

			url = "/inquiry/inquiryDetail.do?inquiry_no=" + inquiryVO.getInquiry_no();
		} else {
			url = "/inquiry/inquiryUpdateForm.do??inquiry_no=" + inquiryVO.getInquiry_no();
		}

		return "redirect:" + url;
	}

	/**************************************************************
	 * 1:1 문의 삭제
	 * 
	 * @throws IOException
	 **************************************************************/
	@RequestMapping(value = "/inquiryDelete.do")
	public String inquiryDelete(@ModelAttribute InquiryVO inquiryVO) {
		logger.info("inquiryDelete 호출");

		int result = 0;
		String url = "";

		result = inquiryService.inquiryDelete(inquiryVO.getInquiry_no());

		if (result == 1) {
			url = "/inquiry/inquiryList.do";
		} else {
			url = "/inquiry/inquiryDetail.do?inquiry_no=" + inquiryVO.getInquiry_no();
		}
		return "redirect:" + url;
	}

}
