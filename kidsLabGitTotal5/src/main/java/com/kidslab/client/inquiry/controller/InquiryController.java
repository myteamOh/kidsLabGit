package com.kidslab.client.inquiry.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.kidslab.client.inquiry.service.InquiryService;
import com.kidslab.client.inquiry.vo.InquiryVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;

@Controller
@RequestMapping(value = "/inquiry")
public class InquiryController {
	Logger logger = Logger.getLogger(InquiryController.class);

	@Autowired
	private InquiryService inquiryService;

	/**************************************************************
	 * 1:1 문의 목록 구현하기
	 **************************************************************/
	@RequestMapping(value = "/inquiryList")
	public ModelAndView inquiryList(@ModelAttribute InquiryVO inquiryVO, Model model, HttpSession session)
			throws Exception {
		logger.info("inquiryList 호출 성공");
		
		// Login한 parent회원 정보를 저장 
		ParentVO pvo = (ParentVO) session.getAttribute("Login");
		logger.info("parent_no : " + pvo.getParent_no());
		
		// list 등록할 때 인터페이스에 정보를 내보냄
		inquiryVO.setParent_no(pvo.getParent_no());
		List<InquiryVO> inquiryList = inquiryService.inquiryList(inquiryVO);

		// mav 생성
		ModelAndView mav = new ModelAndView();

		mav.addObject("inquiryList", inquiryList); // 저장된 데이터를 mav에 저장
		mav.setViewName("inquiry/inquiryList"); // 뷰를 inquiryList.jsp로 설정

		return mav;
	}

	/**************************************************************
	 * 1:1 문의 등록 폼 출력
	 **************************************************************/
	@RequestMapping(value = "/inquiryWriteForm")
	public String inquiryWriteForm() {
		logger.info("/inquiry/inquiryWriteForm 호출 성공");
		return "inquiry/inquiryWriteForm"; // inquiryWriteForm.jsp로 이동
	}

	/**************************************************************
	 * 1:1 문의 등록 구현하기
	 **************************************************************/
	@RequestMapping(value = "/inquiryInsert", method = RequestMethod.POST)
	public String inquiryInsert(@ModelAttribute InquiryVO inquiryVO, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException, Exception {
		logger.info("inquiryInsert 호출 성공");
		logger.info("Parent_no : " + inquiryVO.getParent_no());
		logger.info("inquiry_title : " + inquiryVO.getInquiry_title());
		logger.info("inquiry_content : " + inquiryVO.getInquiry_content());
		int result = 0;
		String url = "";

		result = inquiryService.inquiryInsert(inquiryVO);
		if (result == 1) {
			url = "/inquiry/inquiryList";
		} else {
			model.addAttribute("code", 1);

			// session에 저장된 parent_id를 writer에 저장

			url = "/inquiry/inquiryWriteForm";
		}
		return "redirect:" + url;
	}

	/*
	 * public String inquiryInsert(@ModelAttribute InquiryVO inquiryVO) throws
	 * Exception { inquiryService.inquiryInsert(inquiryVO); return
	 * "redirect:/inquiryList.do"; }
	 */

	/**************************************************************
	 * 1:1 문의 상세보기
	 * 
	 * @RequestParam : get/post 방식으로 전달된 변수 1개 HttpSession 세션 객체
	 **************************************************************/
	@RequestMapping(value = "/inquiryDetail", method = RequestMethod.GET)
	public ModelAndView inquiryDetail(@RequestParam int inquiry_no, HttpSession session) throws Exception {
		logger.info("inquiryDetail 호출");
		logger.info("inquiry_no" + inquiry_no);
		/*
		 * logger.info("inquiry_no = " + inquiryVO.getInquiry_no());
		 * 
		 * InquiryVO detail = new InquiryVO(); detail =
		 * inquiryService.inquiryDetail(inquiryVO);
		 * 
		 * if (detail != null && (!detail.equals(""))) {
		 * detail.setInquiry_content(detail.getInquiry_content().toString().replaceAll(
		 * "\n", "<br>")); } model.addAttribute("detail", detail); return
		 * "inquiey/inquiryDetail";
		 */

		// 모델(데이터) + 뷰(화면) 같이 전달하는 객체
		ModelAndView mav = new ModelAndView();
		// 뷰의 이름
		mav.setViewName("inquiry/inquiryDetail");
		// 뷰에 전달할 데이터
		mav.addObject("idto", inquiryService.inquiryDetail(inquiry_no));

		return mav;
	}

	/**************************************************************
	 * 
	 * @param :
	 *            InquiryVO
	 * 
	 **************************************************************/
	@RequestMapping(value = "/inquiryUpdate", method = RequestMethod.POST)
	public String inquiryUpdate(@ModelAttribute InquiryVO inquiryVO) throws Exception {

		logger.info("inquiryUpdate 호출 성공");

		/*
		 * int result = 0; String url = "";
		 * 
		 * result = inquiryService.inquiryUpdate(inquiryVO); if (result == 1) {
		 * 
		 * url = "/inquiry/inquiryDetail.do?inquiry_no=" + inquiryVO.getInquiry_no(); }
		 * else { url = "/inquiry/inquiryUpdateForm.do??inquiry_no=" +
		 * inquiryVO.getInquiry_no(); }
		 * 
		 * return "redirect:" + url;
		 */
		inquiryService.inquiryUpdate(inquiryVO);
		return "redirect:/inquiry/inquiryList";

	}

	/**************************************************************
	 * 1:1 문의 삭제
	 * 
	 * @throws IOException
	 **************************************************************/
	@RequestMapping(value = "/inquiryDelete")
	/*
	 * public String inquiryDelete(@ModelAttribute InquiryVO inquiryVO) {
	 * logger.info("inquiryDelete 호출");
	 * 
	 * int result = 0; String url = "";
	 * 
	 * result = inquiryService.inquiryDelete(inquiryVO.getInquiry_no());
	 * 
	 * if (result == 1) { url = "/inquiry/inquiryList.do"; } else { url =
	 * "/inquiry/inquiryDetail.do?inquiry_no=" + inquiryVO.getInquiry_no(); } return
	 * "redirect:" + url; }
	 */
	public String inquiryDelete(@RequestParam int inquiry_no) throws Exception {

		logger.info("inquiryDelete 호출 성공");

		inquiryService.inquiryDelete(inquiry_no);
		return "redirect:/inquiry/inquiryList";

	}
}
