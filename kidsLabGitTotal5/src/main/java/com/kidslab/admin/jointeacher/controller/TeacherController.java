package com.kidslab.admin.jointeacher.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.jointeacher.service.TeacherService;
import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.admin.login.vo.AdminLoginVO;
import com.kidslab.common.file.FileUploadUtil;

@Controller
@RequestMapping(value = "/admin")
public class TeacherController {
	Logger logger = Logger.getLogger(TeacherController.class);

	@Autowired
	private TeacherService teacherService;

	/*****************************
	 * 회원 가입 폼
	 ****************************/
	@RequestMapping(value = "/teacher/join.do", method = RequestMethod.GET)
	public String joinForm(Model model) {
		logger.info("join.do get 방식에 의한 메소드 호출 성공");
		return "admin/teacher/joinTeacher";
	}

	/*******************************
	 * 사용자 아이디 중복 체크 메소드
	 *****************************/
	@ResponseBody
	@RequestMapping(value = "/teacher/userIdConfirm.do", method = RequestMethod.POST)
	public String userIdConfirm(@RequestParam("teacher_id") String teacher_id) {
		logger.info("userIdConfirm.do 메소드 호출 성공");
		System.out.println(teacher_id);
		int result = teacherService.userIdConfirm(teacher_id);
		return result + "";
	}

	/******************************
	 * 회원 가입 처리
	 *******************************/
	@RequestMapping(value = "/teacher/join.do", method = RequestMethod.POST)
	public ModelAndView teacherInsert(@ModelAttribute TeacherVO tvo, HttpServletRequest request) throws Exception {

		logger.info("join.do post 방식에 의한 메소드 호출 성공");
		ModelAndView mav = new ModelAndView();
		logger.info("file name : " + tvo.getFile().getOriginalFilename());
		int result = 0;

		if (tvo.getFile() != null) {
			String fileName = FileUploadUtil.fileUpload(tvo.getFile(), request, "teacher");
			tvo.setTeacher_photo(fileName);

			String thumbName = FileUploadUtil.makeThumbnail(fileName, request);
			tvo.setTeacher_thumb(thumbName);
		}

		result = teacherService.teacherInsert(tvo);

		switch (result) {
		case 1:
			mav.addObject("errCode", 1); // teacherId already exist
			mav.setViewName("admin/teacher/joinTeacher");
			break;
		case 3:
			mav.addObject("errCode", 3);
			mav.setViewName("admin/member/teacherList");
			// success to add new member; move to page
			break;
		default:
			mav.addObject("errCode", 2); // failed to add new member
			mav.setViewName("admin/teacher/joinTeacher");
			break;
		}
		return mav;
	}

	/*************************
	 * 회원 수정
	 ***************************/
	@RequestMapping(value = "/teacher/modify.do", method = RequestMethod.POST)
	public ModelAndView teacherModify(HttpSession session) {
		logger.info("modify.do get 방식에 의한 메서드 호출 성공");
		ModelAndView mav = new ModelAndView();

		AdminLoginVO login = (AdminLoginVO) session.getAttribute("adminLogin");
		if (login == null) {
			mav.setViewName("admin/login/login");
			return mav;
		}
		return mav;
	}
}
