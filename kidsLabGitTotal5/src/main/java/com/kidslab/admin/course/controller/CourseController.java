package com.kidslab.admin.course.controller;

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

import com.kidslab.admin.course.service.CourseService;
import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.common.file.FileUploadUtil;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;

@Controller
@RequestMapping(value = "/admin")
public class CourseController {

	Logger logger = Logger.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	/********************************
	 * 강의목록 구현하기
	 ********************************/
	@RequestMapping(value = "/course/courseList", method = RequestMethod.GET)
	public String courseList(@ModelAttribute CourseVO cvo, Model model) {

		logger.info("courseList 호출 성공");

		// 페이지 세팅
		Paging.setPage(cvo);
		logger.info("start : " + cvo.getStart_row());
		logger.info("end : " + cvo.getEnd_row());

		// 전체 레코드 수 구현
		int total = courseService.courseListCnt(cvo);

		logger.info("total = " + total);

		// 글번호 재설정
		int count = total - (Util.nvl(cvo.getPage()) - 1) * Util.nvl(cvo.getPageSize());
		logger.info("count = " + count);
		logger.info("course_status : " + cvo.getCourse_status());
		logger.info("page : " + cvo.getPage());
		logger.info("search : " + cvo.getSearch());
		List<CourseVO> courseList = courseService.courseList(cvo);

		model.addAttribute("courseList", courseList);
		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("courseData", cvo);

		return "admin/course/courseList";
	}

	/********************************
	 * 글 수정 및 등록 폼 출력
	 ********************************/
	@RequestMapping(value = "/course/courseUpdate", method = RequestMethod.GET)
	public String updateForm(@ModelAttribute CourseVO cvo, Model model) {

		logger.info("updateForm 호출!!");

		logger.info("course_no = " + cvo.getCourse_no());

		CourseVO updateData = new CourseVO();
		updateData = courseService.courseDetail(cvo);
		logger.info("time = " + updateData.getCourse_time());
		String day;
		String hour;
		logger.info(updateData.getCourse_time());
		if (updateData.getCourse_time().equals("미정")) {
			day = "월요일";
			hour = "15:00 ~ 16:45";
			logger.info("날짜 : " + day);
			logger.info("시간 : " + hour);
		} else {
			day = updateData.getCourse_time().substring(0, 3);
			hour = updateData.getCourse_time().substring(4, 17);
			logger.info("날짜 : " + day);
			logger.info("시간 : " + hour);
		}

		model.addAttribute("updateData", updateData);
		model.addAttribute("day", day);
		model.addAttribute("hour", hour);

		return "admin/course/courseUpdate";
	}

	/********************************
	 * 글 수정 및 등록
	 ********************************/
	@RequestMapping(value = "/course/courseUpdate", method = RequestMethod.POST)
	public String courseUpdate(@ModelAttribute CourseVO cvo, HttpServletRequest request)
			throws IllegalStateException, IOException, SQLException {

		logger.info("courseUpdate 호출 성공");

		int result = 0;
		String url = "";
		String course_plan = "";

		if (!cvo.getFile().isEmpty()) {
			logger.info("file = " + cvo.getFile().getOriginalFilename());
			if (!cvo.getCourse_plan().isEmpty()) {
				FileUploadUtil.fileDelete(cvo.getCourse_plan(), request);
			}
			course_plan = FileUploadUtil.fileUpload(cvo.getFile(), request, "registCourse");
			cvo.setCourse_plan(course_plan);
		} else {
			logger.info("첨부파일 업음");
			cvo.setCourse_plan("");
		}
		logger.info("course_plan = " + cvo.getCourse_plan());
		result = courseService.courseUpdate(cvo);
		logger.info(result);

		if (result == 1) {
			url = "courseList";
		}

		return "redirect:" + url;
	}

	/*******************************
	 * 강의 삭제
	 *******************************/
	@RequestMapping(value = "/course/courseDelete")
	public String courseDelete(@ModelAttribute CourseVO cvo, HttpServletRequest request) throws IOException {
		logger.info("courseDelete 호출!!!");
		logger.info("번호 : " + cvo.getCourse_no());
		CourseVO deleteData = new CourseVO();
		deleteData = courseService.courseDetail(cvo);
		logger.info("파일 : " + deleteData.getCourse_plan());
		int result = 0;
		String url = "";

		if (!deleteData.getCourse_plan().isEmpty()) {
			FileUploadUtil.fileDelete(deleteData.getCourse_plan(), request);
		}

		result = courseService.courseDelete(cvo.getCourse_no());

		if (result == 1) {
			url = "courseList?page=" + cvo.getPage() + "&pageSize=" + cvo.getPageSize();
		} else {
			logger.info("삭제 불가능");
		}

		return "redirect:" + url;

	}

}
