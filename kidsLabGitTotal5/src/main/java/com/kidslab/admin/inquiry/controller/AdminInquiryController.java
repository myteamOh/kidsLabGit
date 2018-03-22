package com.kidslab.admin.inquiry.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.client.inquiry.controller.InquiryController;
import com.kidslab.client.inquiry.service.InquiryService;
import com.kidslab.client.inquiry.vo.InquiryVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminInquiryController {
	Logger logger = Logger.getLogger(InquiryController.class);

	@Autowired
	private InquiryService inquiryService;

	/**************************************************************
	 * 1:1 문의 목록 구현하기
	 **************************************************************/
	@RequestMapping(value = "/inquiry/inquiryList")
	public ModelAndView inquiryList(@ModelAttribute InquiryVO inquiryVO, Model model) throws Exception {
		logger.info("inquiryList 호출 성공");

		List<InquiryVO> inquiryList = inquiryService.adminInquiryList(inquiryVO);

		// ModelAndView - 모델과 뷰
		ModelAndView mav = new ModelAndView();

		mav.addObject("inquiryList", inquiryList); // 저장된 데이터를 mav에 저장
		mav.setViewName("admin/inquiry/inquiryList"); // 뷰를 inquiryList.jsp로 설정

		return mav;
	}

	/**************************************************************
	 * 1:1 문의 상세보기
	 * 
	 * @RequestParam : get/post 방식으로 전달된 변수 1개 HttpSession 세션 객체
	 **************************************************************/
	@RequestMapping(value = "/inquiry/inquiryDetail", method = RequestMethod.GET)
	public ModelAndView inquiryDetail(@RequestParam int inquiry_no, HttpSession session) throws Exception {
		logger.info("inquiryDetail 호출");
		logger.info("inquiry_no : " + inquiry_no);

		// 모델(데이터) + 뷰(화면) 같이 전달하는 객체
		ModelAndView mav = new ModelAndView();
		// 뷰의 이름
		mav.setViewName("admin/inquiry/inquiryDetail");
		// 뷰에 전달할 데이터
		mav.addObject("detail", inquiryService.inquiryDetail(inquiry_no));

		return mav;
	}

	/*******************************************************
	 * 답변 완료시
	 ***************************************************/
	@RequestMapping(value = "/inquiry/inquiryList", method = RequestMethod.POST)
	public String replySuccessUpdate(@ModelAttribute InquiryVO ivo, HttpSession session) throws Exception {
		logger.info("replySuccess 호출");

		logger.info("ivo.getNo : " + ivo.getInquiry_no());

		int result = 0;
		String url = "";

		result = inquiryService.replySuccessUpdate(ivo);

		if (result == 1) {
			url = "inquiryList";
		} else {
			url = "inquiryDetail";
		}

		return "redirect:" + url;

	}
}
