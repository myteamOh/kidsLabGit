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
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.common.excel.ListExcelView;
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

		tvo = (TeacherLoginVO) session.getAttribute("teacherLogin");
		
		if (tvo != null) {
	
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

		ModelAndView mav = new ModelAndView();

		mav.setViewName("teacher/mypage/modifyCheckPw");

		return mav;
	}

	// 비밀번호 확인 버튼 클릭 시 처리 수정 창으로 이동
	@RequestMapping(value = "/modifyinfo", method = RequestMethod.POST)
	public ModelAndView teacherModify(@ModelAttribute TeacherVO tvo, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		TeacherLoginVO tLogin = (TeacherLoginVO) session.getAttribute("teacherLogin");

		if (tLogin == null) {
			mav.setViewName("teacher/login/login");
		} else {
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
	
		TeacherVO vo = teacherService.teacherSelect(tvo.getTeacher_id());
		
		result = teacherService.teacherUpdate(tvo);

		if (result == true) {
			mav.setViewName("redirect:/teacher/logout.do");
			return mav;
		}
		mav.addObject("teacherLogin", vo);
		mav.setViewName("teacher/mypage/modifyTeacherInfo");
		return mav;
	}

	// 학생정보 리스트
	@RequestMapping(value = "/student/courseStudentList.do", method = RequestMethod.GET)
	public String courseStudentList(@ModelAttribute TeacherLoginVO tvo, @ModelAttribute CourseVO cvo, Model model,
			HttpSession session) {

		tvo = (TeacherLoginVO) session.getAttribute("teacherLogin");

		if (tvo != null) {

			List<StudentVO> courseStudentList = courseService.courseStudentList(cvo);

			model.addAttribute("courseStudentList", courseStudentList);
			model.addAttribute("course_no", cvo.getCourse_no());
			return "teacher/student/courseStudentList";
		} else {
			return "teacher/login/login";
		}

	}

	/**
	 * 엑셀 다운로드 구현하기 참고 : ListExcelView 클래스에서 맵타입으로 Model에 접근하게 된다.
	 */
	@RequestMapping(value = "/student/studentExcel", method = RequestMethod.GET)
	public ModelAndView boardExcel(@ModelAttribute StudentVO svo, @ModelAttribute CourseVO cvo) {

		List<StudentVO> courseStudentList = courseService.courseStudentList(cvo);

		ModelAndView mv = new ModelAndView(new ListExcelView());
		
		mv.addObject("list", courseStudentList);
		mv.addObject("template", "student.xlsx");
		mv.addObject("file_name", "student");

		return mv;
	}

}
