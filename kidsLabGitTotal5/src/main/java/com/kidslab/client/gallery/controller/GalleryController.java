package com.kidslab.client.gallery.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;
import com.kidslab.teacher.gallery.service.TeacherGalleryService;
import com.kidslab.teacher.gallery.vo.TeacherGalleryVO;

@Controller
@RequestMapping(value = "/client")
public class GalleryController {
	Logger logger = Logger.getLogger(GalleryController.class);

	@Autowired
	private TeacherGalleryService teacherGalleryService;

	/********************************
	 * 갤러리 목록 구현
	 ********************************/
	@RequestMapping(value = "/gallery/galleryList")
	public String galleryList(@ModelAttribute TeacherGalleryVO tgvo, Model model) {

		logger.info("galleryList 호출 성공");

		// 페이지 세팅
		Paging.setPage(tgvo);
		logger.info("start : " + tgvo.getStart_row());
		logger.info("end : " + tgvo.getEnd_row());

		// 전체 레코드 수 구현
		int total = teacherGalleryService.galleryListCnt(tgvo);

		logger.info("total = " + total);

		// 글번호 재설정
		int count = total - (Util.nvl(tgvo.getPage()) - 1) * Util.nvl(tgvo.getPageSize());
		logger.info("count = " + count);
		logger.info("page : " + tgvo.getPage());
		logger.info("search : " + tgvo.getPage());

		List<TeacherGalleryVO> galleryList = teacherGalleryService.galleryList(tgvo);

		model.addAttribute("galleryList", galleryList);
		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("galleryData", tgvo);

		return "client/gallery/galleryList";
	}

	/**************************************
	 * 갤러리 상세보기 구현
	 **************************************/
	@RequestMapping(value = "/gallery/galleryDetail", method = RequestMethod.GET)
	public String galleryDetail(@ModelAttribute TeacherGalleryVO tgvo, Model model) {
		logger.info("galleryDetail 호출 성공");
		logger.info("gallery_no = " + tgvo.getGallery_no());

		TeacherGalleryVO detail = new TeacherGalleryVO();
		detail = teacherGalleryService.galleryDetail(tgvo);

		if (detail != null && (!detail.equals(""))) {
			detail.setGallery_content(detail.getGallery_content().toString().replaceAll("\n", "<br>"));

		}

		model.addAttribute("detail", detail);

		return "client/gallery/galleryDetail";

	}

}
