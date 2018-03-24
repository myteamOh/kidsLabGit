package com.kidslab.client.faq.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.admin.faq.service.FaqService;
import com.kidslab.admin.faq.vo.FaqVO;
import com.kidslab.admin.notice.vo.NoticeVO;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;

@Controller
@RequestMapping(value = "/client")
public class ClientFaqController {

	Logger logger = Logger.getLogger(ClientFaqController.class);

	@Autowired
	private FaqService faqService;

	// Faq 목록
	@RequestMapping(value = "/faq/faqList")
	public String faqList(@ModelAttribute FaqVO fvo, Model model) {
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

		return "client/faq/faqList";
	}

	// Faq 상세보기
	@RequestMapping(value = "/faq/faqDetail", method = RequestMethod.GET)
	public String faqDetail(@ModelAttribute FaqVO fvo, Model model) {

		logger.info("faqDetail 호출~");
		logger.info("faq_no = " + fvo.getFaq_no());

		FaqVO detail = new FaqVO();
		detail = faqService.faqDetail(fvo);

		model.addAttribute("detail", detail);

		return "client/faq/faqDetail";
	}
}
