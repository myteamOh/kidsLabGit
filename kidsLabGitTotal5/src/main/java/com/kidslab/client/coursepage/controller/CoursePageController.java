package com.kidslab.client.coursepage.controller;

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
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.common.file.FileUploadUtil;
import com.kidslab.common.page.Paging;

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

		CourseVO vo = coursePageService.selectCourse(cvo);

		cdvo.setCourse_no(cvo.getCourse_no());

		cdvo.setCoursedata_status("공지사항");
		List<CourseDataVO> courseDataNoticeList = coursePageService.homeCourseDataList(cdvo);

		cdvo.setCoursedata_status("자료실");
		List<CourseDataVO> courseDataDataList = coursePageService.homeCourseDataList(cdvo);

		session.setAttribute("cNum", cvo.getCourse_no());
		mav.addObject("course", vo);
		mav.addObject("cdNoticeList", courseDataNoticeList);
		mav.addObject("cdDataList", courseDataDataList);

		mav.setViewName("client/coursePage/coursePageHome");

		return mav;
	}

	/* mainpage 호출 */
	@RequestMapping(value = "/courseboardhome", method = RequestMethod.POST)
	public String coursePageHome(@ModelAttribute("CourseVO") CourseVO cvo, Model model) {

		logger.info("강의페이지 홈!");

		CourseVO vo = coursePageService.selectCourse(cvo);

		model.addAttribute("course", vo);

		return "client/coursePage/coursePageHome";
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
		logger.info("total = " + total);

		List<CourseDataVO> cdvoList = coursePageService.courseDataList(cdvo);

		mav.addObject("total", total);
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
		svo = (StudentVO) session.getAttribute("Login");

		String nameNid = svo.getUserName() + "(" + svo.getUserId() + ")";

		System.out.println(nameNid);
		System.out.println(vo.getCoursedata_writer());

		model.addAttribute("loginInfo", nameNid);
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
	public int coursePageDelete(@ModelAttribute("CourseDataVO") CourseDataVO cdvo) {

		logger.info("글삭제다해");

		int result = 0;

		result = coursePageService.coursePageDelete(cdvo.getCoursedata_no());

		System.out.println(result);

		return result;
	}

}
