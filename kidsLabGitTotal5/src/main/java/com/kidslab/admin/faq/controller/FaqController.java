package com.kidslab.admin.faq.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.admin.faq.service.FaqService;
import com.kidslab.admin.faq.vo.FaqVO;
import com.kidslab.admin.login.vo.AdminLoginVO;
import com.kidslab.admin.notice.vo.NoticeVO;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;

@Controller
@RequestMapping(value = "/admin")
public class FaqController {

	Logger logger = Logger.getLogger(FaqController.class);

	@Autowired
	private FaqService faqService;

	// Faq 목록
	@RequestMapping(value = "/faq/faqList")
	public String faqList(@ModelAttribute FaqVO fvo, Model model, HttpSession session) {

		AdminLoginVO vo = new AdminLoginVO();
		vo = (AdminLoginVO) session.getAttribute("adminLogin");
		if (vo == null) {
			return "redirect:/admin/login";
		}
		logger.info("faqList 호출~~");
		// 페이지 세팅 (추가)
		Paging.setPage(fvo);

		// 전체 레코드 수 (추가)
		int total = faqService.faqListCnt(fvo);
		logger.info("total = " + total);

		// 글번호 재설정 (추가)
		int count = total - (Util.nvl(fvo.getPage()) - 1) * Util.nvl(fvo.getPageSize());
		logger.info("count = " + count);

		List<FaqVO> faqList = faqService.faqList(fvo);

		model.addAttribute("faqList", faqList);

		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("faqData", fvo);
		logger.info("page : " + fvo.getPage());

		return "admin/faq/faqList";
	}

	// Faq 쓰기 폼 출력
	@RequestMapping(value = "/faq/writeForm")
	public String writeForm() {
		logger.info("writeForm 호출!~~");

		return "admin/faq/writeForm";
	}

	// Faq 쓰기 POST
	@RequestMapping(value = "/faq/faqInsert", method = RequestMethod.POST)
	public String faqInsert(@ModelAttribute FaqVO fvo, Model model, HttpServletRequest request)
			throws IllegalStateException {
		logger.info("faqInsert POST 호출");
		Object[] cookies = request.getCookies();
		HashMap<String, String> cookiesMap = new HashMap<String, String>();
		Cookie[] cookies1 = request.getCookies();
		String cookies_Key_Values = "";
		if(cookies1 != null) {
			for(int i =0; i < cookies1.length; i++) {
				cookies_Key_Values = cookies_Key_Values + cookies1[i].getName() + " : " + cookies1[i].getValue() + "\n"; 
			}
			 
		}
		
		
		int result = 0;
		String url = "";

		result = faqService.faqInsert(fvo);

		if (result == 1) {
			url = "faqList";
		} else {
			model.addAttribute("code", 1);
			url = "writeForm";
		}
		return "redirect:" + url;
	}

	// Faq 상세보기
	@RequestMapping(value = "/faq/faqDetail", method = RequestMethod.GET)
	public String faqDetail(@ModelAttribute FaqVO fvo, Model model) {

		logger.info("faqDetail 호출~");
		logger.info("faq_no = " + fvo.getFaq_no());

		FaqVO detail = new FaqVO();
		detail = faqService.faqDetail(fvo);

		model.addAttribute("detail", detail);

		return "admin/faq/faqDetail";
	}

	// Faq 수정 폼 출력
	@RequestMapping(value = "/faq/updateForm")
	public String updateForm(@ModelAttribute FaqVO fvo, Model model) {
		logger.info("updateForm 호출");

		logger.info("faq_no = " + fvo.getFaq_no());

		FaqVO updateData = new FaqVO();
		updateData = faqService.faqDetail(fvo);

		model.addAttribute("updateData", updateData);

		return "admin/faq/updateForm";
	}

	// Faq 수정
	@RequestMapping(value = "/faq/faqUpdate", method = RequestMethod.POST)
	public String faqUpdate(@ModelAttribute FaqVO fvo, HttpServletRequest request) throws IllegalStateException {
		logger.info("faqUpdate Post 호출");

		int result = 0;
		String url = "";
		logger.info("faq_title : " + fvo.getFaq_title());
		logger.info("faq_type : " + fvo.getFaq_type());
		logger.info("faq_content : " + fvo.getFaq_content());
		logger.info("page : " + fvo.getPage());
		result = faqService.faqUpdate(fvo);
		logger.info("result : " + result);

		if (result == 1) {
			url = "/admin/faq/faqDetail?faq_no=" + fvo.getFaq_no() + "&page=" + fvo.getPage() + "&pageSize="
					+ fvo.getPageSize();
		}

		return "redirect:" + url;
	}

	// Faq 삭제
	@RequestMapping(value = "/faq/faqDelete", method = RequestMethod.POST)
	public String noticeDelete(@ModelAttribute FaqVO fvo, HttpServletRequest request) {

		logger.info("faqDelete 호출 성공");

		int result = 0;
		String url = "";

		result = faqService.faqDelete(fvo.getFaq_no());

		if (result == 1) {
			url = "/admin/faq/faqList.do?page=" + fvo.getPage() + "&pageSize=" + fvo.getPageSize();
		} else {
			url = "/admin/faq/faqDetail.do?faq_no=" + fvo.getFaq_no() + "&page=" + fvo.getPage() + "&pageSize="
					+ fvo.getPageSize();
		}
		return "redirect:" + url;
	}
}
