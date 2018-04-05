package com.kidslab.teacher.registcourse.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kidslab.common.file.FileUploadUtil;
import com.kidslab.teacher.registcourse.service.RegistCourseService;
import com.kidslab.teacher.registcourse.vo.RegistCourseVO;

@Controller
@RequestMapping(value = "/teacher")
public class RegistCourseController {
	Logger logger = Logger.getLogger(RegistCourseController.class);

	@Autowired
	private RegistCourseService registCourseService;

	/**************************************
	 * 강의등록 폼 출력
	 **************************************/
	@RequestMapping(value = "/course/registForm")
	public String writeForm() {
			return "teacher/course/registForm";
	}

	/**************************************
	 * 강의등록 구현하기
	 **************************************/
	@RequestMapping(value = "/course/registCourse", method = RequestMethod.POST)
	public String registCourseInsert(@ModelAttribute RegistCourseVO rvo, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {

	
		// 확인 후 주석 처리
		// logger.info("fileName : " + rvo.getFile().getOriginalFilename());
		// logger.info("course_name : " + rvo.getCourse_name());

		int result = 0;
		String url = "";

		if (rvo.getFile() != null) {
			String course_plan = FileUploadUtil.fileUpload(rvo.getFile(), request, "registCourse");
			rvo.setCourse_plan(course_plan);
		}
		result = registCourseService.registCourseInsert(rvo);

		if (result == 1) {
			url = "/teacher/login";
		} else {
			model.addAttribute("code", 1);
			url = "/teacher/course/registForm";
		}
		return "redirect:" + url;
	}

}
