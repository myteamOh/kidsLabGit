package com.kidslab.admin.payment.controller;

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

import com.kidslab.admin.payment.service.PaymentService;
import com.kidslab.admin.payment.vo.PaymentVO;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;

@Controller
@RequestMapping(value = "/admin")
public class PaymentController {

	Logger logger = Logger.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	/************************
	 * 결제 목록
	 ************************/
	@RequestMapping(value = "/payment/paymentList", method = RequestMethod.GET)
	public String paymentList(@ModelAttribute PaymentVO pvo, Model model) {
		logger.info("paymentList 호출 성공");

		// 페이지 세팅
		Paging.setPage(pvo);
		logger.info("start : " + pvo.getStart_row());
		logger.info("end : " + pvo.getEnd_row());
		logger.info("keyword : " + pvo.getKeyword());
		logger.info("search : " + pvo.getSearch());
		// 전체 레코드 수 구현
		int total = paymentService.paymentListCnt(pvo);

		logger.info("total = " + total);

		// 글번호 재설정
		int count = total - (Util.nvl(pvo.getPage()) - 1) * Util.nvl(pvo.getPageSize());
		logger.info("count = " + count);
		logger.info("payment_status : " + pvo.getRequestCourse_paymentStatus());
		logger.info("page : " + pvo.getPage());
		logger.info("pageSize : " + pvo.getPageSize());
		List<PaymentVO> paymentList = paymentService.paymentList(pvo);

		model.addAttribute("paymentList", paymentList);
		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("paymentData", pvo);

		return "admin/payment/paymentList";

	}

	/**************************************
	 * 결제 확인 및 수정 폼
	 *************************************/
	@RequestMapping(value = "/payment/paymentUpdate", method = RequestMethod.GET)
	public String updateForm(@ModelAttribute PaymentVO pvo, Model model) {

		logger.info("updateForm 호출");
		logger.info("pvo.getDate : " + pvo.getRequestCourse_paymentDate());

		PaymentVO updateData = new PaymentVO();

		updateData = paymentService.paymentDetail(pvo);

		model.addAttribute("updateData", updateData);

		return "admin/payment/paymentUpdate";
	}

	/***************************************
	 * 결제 수정 및 등록
	 ***************************************/
	@RequestMapping(value = "/payment/paymentUpdate", method = RequestMethod.POST)
	public String paymentUpdate(@ModelAttribute PaymentVO pvo, HttpServletRequest request)
			throws IllegalStateException, SQLException {

		logger.info("paymentUpdate 호출 성공");

		int result = 0;

		String url = "";
		result = paymentService.paymentUpdate(pvo);
		logger.info(result);

		if (result == 1) {
			url = "paymentList";
		}
		return "redirect:" + url;
	}
}
