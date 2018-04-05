package com.kidslab.teacher.gallery.controller;

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

import com.kidslab.common.file.FileUploadUtil;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;
import com.kidslab.teacher.gallery.service.TeacherGalleryService;
import com.kidslab.teacher.gallery.vo.TeacherGalleryVO;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherGalleryController {
	Logger logger = Logger.getLogger(TeacherGalleryController.class);

	@Autowired
	private TeacherGalleryService teacherGalleryService;

	/********************************
	 * 갤러리 목록 구현
	 ********************************/
	@RequestMapping(value = "/gallery/galleryList")
	public String galleryList(@ModelAttribute TeacherGalleryVO tgvo, Model model) {

	
		// 페이지 세팅
		Paging.setPage(tgvo);
	
		// 전체 레코드 수 구현
		int total = teacherGalleryService.galleryListCnt(tgvo);


		// 글번호 재설정
		int count = total - (Util.nvl(tgvo.getPage()) - 1) * Util.nvl(tgvo.getPageSize());

		List<TeacherGalleryVO> galleryList = teacherGalleryService.galleryList(tgvo);

		model.addAttribute("galleryList", galleryList);
		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("galleryData", tgvo);

		return "teacher/gallery/galleryList";
	}

	/**************************************
	 * 갤러리 등록 폼 출력
	 **************************************/
	@RequestMapping(value = "/gallery/galleryInsertForm")
	public String galleryRegistForm() {
		return "teacher/gallery/galleryInsertForm";
	}

	/**************************************
	 * 갤러리 등록 구현
	 **************************************/
	@RequestMapping(value = "/gallery/galleryInsert", method = RequestMethod.POST)
	public String galleryInsert(@ModelAttribute TeacherGalleryVO tgvo, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException, Exception {

		int result = 0;
		String url = "";

		if (tgvo.getFile() != null) {
			String gallery_file = FileUploadUtil.fileUpload(tgvo.getFile(), request, "gallery");
			tgvo.setGallery_file(gallery_file);

			String gallery_thumb = FileUploadUtil.makeThumbnail(gallery_file, request);
			tgvo.setGallery_thumb(gallery_thumb);

		}
		result = teacherGalleryService.galleryInsert(tgvo);

		if (result == 1) {
			url = "/teacher/gallery/galleryList";
		} else {
			model.addAttribute("code", 1);
			url = "/teacher/gallery/galleryInsertForm";
		}
		return "redirect:" + url;
	}

	/**************************************
	 * 갤러리 상세보기 구현
	 **************************************/
	@RequestMapping(value = "/gallery/galleryDetail", method = RequestMethod.GET)
	public String galleryDetail(@ModelAttribute TeacherGalleryVO tgvo, Model model) {

		TeacherGalleryVO detail = new TeacherGalleryVO();
		detail = teacherGalleryService.galleryDetail(tgvo);

		if (detail != null && (!detail.equals(""))) {
			detail.setGallery_content(detail.getGallery_content().toString().replaceAll("\n", "<br>"));
		}

		model.addAttribute("detail", detail);

		return "teacher/gallery/galleryDetail";

	}

	/**************************************
	 * 갤러리 수정 폼 출력
	 **************************************/
	@RequestMapping(value = "/gallery/galleryUpdateForm")
	public String galleryUpdate(@ModelAttribute TeacherGalleryVO tgvo, Model model) {

		TeacherGalleryVO updateData = new TeacherGalleryVO();
		updateData = teacherGalleryService.galleryDetail(tgvo);

		model.addAttribute("updateData", updateData);
		return "teacher/gallery/galleryUpdateForm";
	}

	/*******************************
	 * 갤러리 수정 구현
	 * 
	 * @throws Exception
	 *******************************/
	@RequestMapping(value = "/gallery/galleryUpdate", method = RequestMethod.POST)
	public String galleryUpdate(@ModelAttribute TeacherGalleryVO tgvo, HttpServletRequest request) throws Exception {
		logger.info("galleryUpdate 호출 성공");

		int result = 0;
		String url = "";
		String g_file = "";

		if (!tgvo.getFile().isEmpty()) {

			if (!tgvo.getGallery_file().isEmpty()) {
				FileUploadUtil.fileDelete(tgvo.getGallery_file(), request);
			}

			g_file = FileUploadUtil.fileUpload(tgvo.getFile(), request, "gallery");

			String thumbName = FileUploadUtil.makeThumbnail(g_file, request);
			tgvo.setGallery_file(g_file);
			tgvo.setGallery_thumb(thumbName);

		} else {
			tgvo.setGallery_file("");
		}

		result = teacherGalleryService.galleryUpdate(tgvo);

		if (result == 1) {
			// url = "/gallery/galleryList"; // 수정 후 목록으로 이동
			// 아래 url은 수정 후 상세페이지 이동
			url = "/teacher/gallery/galleryDetail?gallery_no=" + tgvo.getGallery_no() + "&page=" + tgvo.getPage()
					+ "&pageSize=" + tgvo.getPageSize();
		}
		return "redirect:" + url;
	}

	/*******************************
	 * 갤러리 삭제
	 *******************************/
	@RequestMapping(value = "/gallery/galleryDelete")
	public String galleryDelete(@ModelAttribute TeacherGalleryVO tgvo, HttpServletRequest request) throws IOException {

		// 아래 변수에는 입력 성공에 대한 상태 값 담습니다. (1 or 0)
		int result = 0;
		String url = "";

		if (!tgvo.getGallery_file().isEmpty()) {
			FileUploadUtil.fileDelete(tgvo.getGallery_file(), request);
		}

		result = teacherGalleryService.galleryDelete(tgvo.getGallery_no());

		if (result == 1) {

			url = "/teacher/gallery/galleryList?page=" + tgvo.getPage() + "&pageSize=" + tgvo.getPageSize();

		} else {

			url = "/teacher/gallery/galleryDetail?gallery_no=" + tgvo.getGallery_no() + "&page=" + tgvo.getPage()
					+ "&pageSize=" + tgvo.getPageSize();

		}

		return "redirect:" + url;
	}

}
