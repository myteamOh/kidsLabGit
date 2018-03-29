package com.kidslab.client.coursepage.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;
import com.kidslab.client.coursepage.service.CoursePageService;
import com.kidslab.client.login.vo.UserLoginVO;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.common.file.FileUploadUtil;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;
import com.kidslab.teacher.login.vo.TeacherLoginVO;

@Controller
@RequestMapping(value = "/coursepage")
public class CoursePageController {

	Logger logger = Logger.getLogger(CoursePageController.class);

	@Autowired
	private CoursePageService coursePageService;

	/* 강의 페이지 버튼 클릭시 처리 */
	@RequestMapping(value = "/coursemain", method = RequestMethod.POST)
	public ModelAndView coursePage(@ModelAttribute("CourseVO") CourseVO cvo, HttpSession session) {

		logger.info(cvo.getCourse_no() + "번강의 강의페이지 입장!");

		ModelAndView mav = new ModelAndView();
		CourseDataVO cdvo = new CourseDataVO();
		CourseDataVO cdvodata = new CourseDataVO();

		CourseVO vo = coursePageService.selectCourse(cvo);

		cdvo.setCourse_no(cvo.getCourse_no());

		cdvo.setCoursedata_status("공지사항");
		List<CourseDataVO> courseDataNoticeList = coursePageService.homeCourseDataList(cdvo);

		cdvodata.setCourse_no(cvo.getCourse_no());
		
		cdvodata.setCoursedata_status("자료실");
		List<CourseDataVO> courseDataDataList = coursePageService.homeCourseDataList(cdvodata);
		if (session.getAttribute("cNum") == null) {
			session.setAttribute("cNum", cvo.getCourse_no());
		}
		mav.addObject("course", vo);
		mav.addObject("cdNoticeList", courseDataNoticeList);
		mav.addObject("cdDataList", courseDataDataList);

		mav.setViewName("client/coursePage/coursePageHome");

		return mav;
	}

	/* 게시판처리(글목록) */
	@RequestMapping(value = "/courseboardList", method = RequestMethod.GET)
	public ModelAndView coursePageBoard(@ModelAttribute("CourseDataVO") CourseDataVO cdvo, Model model,
			HttpSession session) {

		logger.info("게시판이다해");

		ModelAndView mav = new ModelAndView();

		cdvo.setCourse_no((int) session.getAttribute("cNum"));

		// 페이지 세팅
		Paging.setPage(cdvo);
		// 전체 레코드 수
		int total = coursePageService.coursePageListCnt(cdvo);

		int count = total - (Util.nvl(cdvo.getPage()) - 1) * Util.nvl(cdvo.getPageSize());

		List<CourseDataVO> cdvoList = coursePageService.courseDataList(cdvo);

		mav.addObject("total", total);
		mav.addObject("count", count);
		mav.addObject("courseboardList", cdvoList);
		mav.addObject("courseboardData", cdvo);

		mav.setViewName("client/coursePage/coursePageBoardList");

		logger.info("page : " + cdvo.getPage());

		return mav;
	}

	/* 글쓰기폼 */
	@RequestMapping(value = "/courseboardWriteForm")
	public String writeForm() {
		logger.info("글쓰기폼이다해");

		return "client/coursePage/coursePageBoardWrite";
	}

	/* 글쓰기처리 */
	@RequestMapping(value = "/courseboardInsert", method = RequestMethod.POST)
	public String coursePageInsert(@ModelAttribute("CourseDataVO") CourseDataVO cdvo, HttpServletRequest request)
			throws IOException {

		logger.info("글입력이다해");

		int result = 0;

		System.out.println(cdvo.getFile());
		System.out.println(cdvo.getStudent_no());

		if (cdvo.getFile() != null) {
			String course_plan = FileUploadUtil.fileUpload(cdvo.getFile(), request, "courseBoard");
			cdvo.setCoursedata_file(course_plan);
		}

		result = coursePageService.coursePageInsert(cdvo);
		System.out.println(result);

		if (result == 1) {
			return "redirect:/coursepage/courseboardList";
		} else {
			return "redirect:/coursepage/courseboardWriteForm";
		}

	}

	// 글 상세보기
	@RequestMapping(value = "/courseboardDetail", method = RequestMethod.GET)
	public String coursePageDetail(@ModelAttribute("CourseDataVO") CourseDataVO cdvo, Model model,
			HttpSession session) {

		logger.info("글상세보기다해");

		CourseDataVO vo = new CourseDataVO();
		vo = coursePageService.coursePageDetail(cdvo);

		StudentVO svo = new StudentVO();
		TeacherLoginVO tvo = new TeacherLoginVO();
		svo = (StudentVO) session.getAttribute("Login");
		tvo = (TeacherLoginVO) session.getAttribute("teacherLogin");

		if (svo != null) {
			String nameNid = svo.getUserName() + "(" + svo.getUserId() + ")";

			System.out.println(nameNid);
			System.out.println(vo.getCoursedata_writer());

			model.addAttribute("loginInfo", nameNid);
		}
		if (tvo != null) {
			String nameNid = tvo.getTeacher_name() + "(" + tvo.getTeacher_id() + ")";

			System.out.println(nameNid);
			System.out.println(vo.getCoursedata_writer());

			model.addAttribute("loginInfo", nameNid);
		}

		model.addAttribute("detail", vo);

		return "client/coursePage/coursePageBoardDetail";
	}

	// 글 수정 폼
	@RequestMapping(value = "/courseboardUpdateForm", method = RequestMethod.GET)
	public String updateForm(@ModelAttribute("CourseDataVO") CourseDataVO cdvo, Model model) {

		logger.info("수정폼이다해");

		CourseDataVO vo = new CourseDataVO();

		vo = coursePageService.coursePageDetail(cdvo);

		model.addAttribute("updateForm", vo);

		return "client/coursePage/coursePageBoardUpdate";

	}

	// 글수정 처리
	@RequestMapping(value = "/courseboardUpdate", method = RequestMethod.POST)
	public String coursePageUpdate(@ModelAttribute("CourseDataVO") CourseDataVO cdvo, Model model,
			HttpServletRequest request) throws IOException {

		logger.info("수정처리다해");

		System.out.println(cdvo.getFile());

		int result = 0;

		if (cdvo.getFile() != null) {
			String course_plan = FileUploadUtil.fileUpload(cdvo.getFile(), request, "courseBoard");
			cdvo.setCoursedata_file(course_plan);
		}

		result = coursePageService.coursePageUpdate(cdvo);

		if (result == 1) {
			return "redirect:/coursepage/courseboardDetail?coursedata_no="
					+ cdvo.getCoursedata_no()/*
												 * + "&page=" + cdvo.getPage() + "&pageSiez=" + cdvo.getPageSize()
												 */;
		} else {
			return "redirect:/coursepage/courseboardUpdateForm?coursedata_no=" + cdvo.getCoursedata_no();
		}

	}

	// 글수정창 취소버튼 처리
	@RequestMapping(value = "/courseboardUpdateCancel", method = RequestMethod.POST)
	public String courseboardUpdatCancel(@ModelAttribute("CourseDataVO") CourseDataVO cdvo) {
		return "redirect:/coursepage/courseboardDetail?coursedata_no=" + cdvo.getCoursedata_no();
	}

	// 글삭제
	@ResponseBody
	@RequestMapping(value = "/courseboardDelete", method = RequestMethod.POST)
	public int coursePageDelete(@ModelAttribute("CourseDataVO") CourseDataVO cdvo, HttpServletRequest request)
			throws IOException {

		logger.info("글삭제다해");
		CourseDataVO deleteData = new CourseDataVO();
		deleteData = coursePageService.coursePageDetail(cdvo);
		logger.info("파일 : " + deleteData.getCoursedata_file());
		int result = 0;
		if (deleteData.getCoursedata_file() != null) {
			FileUploadUtil.fileDelete(deleteData.getCoursedata_file(), request);
		}
		result = coursePageService.coursePageDelete(cdvo.getCoursedata_no());

		System.out.println(result);

		return result;
	}

	/** 파일 다운로드 **/
	@RequestMapping(value = "/download")
	public ModelAndView download(String coursedata_file, HttpSession session) {
		// 파일 객체 생성
		File download = new File("C:\\downLoad\\courseBoard\\" + coursedata_file);
		// 출력 할 뷰 이름과 데이터의 이름을 설정하고
		// 데이터를 설정
		ModelAndView mav = new ModelAndView();
		UserLoginVO uLogin = (UserLoginVO) session.getAttribute("Login");
		TeacherLoginVO tLogin = (TeacherLoginVO) session.getAttribute("teacherLogin");
		if (uLogin == null && tLogin == null) {
			mav.setViewName("client/member/login");
			return mav;
		}
		if (download.isFile()) {
			return new ModelAndView("download", "downloadFile", download);
		} else {
			mav.setViewName("redirect:/coursePage/courseboardList");
			return mav;
		}
	}
}
