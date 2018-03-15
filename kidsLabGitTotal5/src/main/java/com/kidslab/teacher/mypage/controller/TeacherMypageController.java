package com.kidslab.teacher.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.course.service.CourseService;
import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.admin.jointeacher.service.TeacherService;
import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.common.file.FileUploadUtil;
import com.kidslab.teacher.login.service.TeacherLoginService;
import com.kidslab.teacher.login.vo.TeacherLoginVO;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherMypageController {

	Logger logger = Logger.getLogger(TeacherMypageController.class);

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private TeacherLoginService teacherLoginService;

	@Autowired
	private CourseService courseService;

	// Teacher mypage
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String teacherMypage(@ModelAttribute TeacherLoginVO tvo, Model model, HttpSession session) {

		logger.info("teacher Mypage 호출");

		logger.info("teacher_no : " + tvo.getTeacher_no());

		tvo = (TeacherLoginVO) session.getAttribute("teacherLogin");
		if (tvo != null) {
			logger.info("teacher_no : " + tvo.getTeacher_no());

			List<CourseVO> teacherCourseList = courseService.teacherCourseList(tvo);

			model.addAttribute("teacherCourseList", teacherCourseList);

			return "teacher/mypage/teacherMypage";
		} else {
			return "teacher/login/login";
		}

	}

	// 정보수정 버튼 클릭 시 비밀번호 체크
	@RequestMapping(value = "/modifyCheckPw", method = RequestMethod.POST)
	public ModelAndView modifyCheckPw(Model model) {

		logger.info("비밀번호 확인 호출");

		ModelAndView mav = new ModelAndView();

		mav.setViewName("teacher/mypage/modifyCheckPw");

		return mav;
	}

	// 비밀번호 확인 버튼 클릭 시 처리 수정 창으로 이동
	@RequestMapping(value = "/modifyinfo", method = RequestMethod.POST)
	public ModelAndView teacherModify(@ModelAttribute TeacherVO tvo, HttpSession session) {
		logger.info("비밀번호 확인 완료");

		ModelAndView mav = new ModelAndView();
		TeacherLoginVO tLogin = (TeacherLoginVO) session.getAttribute("teacherLogin");

		if (tLogin == null) {
			mav.setViewName("teacher/login/login");
		} else {
			logger.info("수정창 이동");
			TeacherLoginVO teacherLogin = teacherLoginService.loginSelect(tvo.getTeacher_id(),
					tvo.getTeacher_password());
			if (teacherLogin != null) {
				TeacherVO teacher = teacherService.teacherSelect(tvo.getTeacher_id());
				mav.addObject("teacher", teacher);
				mav.setViewName("teacher/mypage/modifyTeacherInfo");
			} else {
				mav.setViewName("teacher/mypage/modifyCheckPw");
				mav.addObject("msg", "fail");
			}
		}
		return mav;

	}

	// 수정완료 버튼 클릭 시 처리
	@RequestMapping(value = "/modifySuccess", method = RequestMethod.POST)
	public ModelAndView teacherModifyInfo(@ModelAttribute TeacherVO tvo, HttpSession session,
			HttpServletRequest request) throws Exception {
		logger.info("완료 버튼 클릭");
		logger.info("tvo_getFile : " + tvo.getFile());
		logger.info("tvo_getPhoto : " + tvo.getTeacher_photo());
		logger.info("id : " + tvo.getTeacher_id());
		logger.info("phone : " + tvo.getTeacher_phone());
		boolean result = false;
		ModelAndView mav = new ModelAndView();
		TeacherLoginVO tLogin = (TeacherLoginVO) session.getAttribute("teacherLogin");
		if (tLogin == null) {
			mav.setViewName("teacher/login/login");
			return mav;
		}

		if (!tvo.getFile().isEmpty()) {
			String fileName = FileUploadUtil.fileUpload(tvo.getFile(), request, "teacher");
			tvo.setTeacher_photo(fileName);

			String thumbName = FileUploadUtil.makeThumbnail(fileName, request);
			tvo.setTeacher_thumb(thumbName);
		}

		tvo.setTeacher_id(tLogin.getTeacher_id());
		logger.info("id : " + tvo.getTeacher_id());
		TeacherVO vo = teacherService.teacherSelect(tvo.getTeacher_id());
		logger.info("실행되나");
		result = teacherService.teacherUpdate(tvo);
		logger.info("result : " + result);
		if (result == true) {
			mav.setViewName("redirect:/teacher/logout.do");
			return mav;
		}
		mav.addObject("teacherLogin", vo);
		mav.setViewName("teacher/mypage/modifyTeacherInfo");
		return mav;
	}

}
