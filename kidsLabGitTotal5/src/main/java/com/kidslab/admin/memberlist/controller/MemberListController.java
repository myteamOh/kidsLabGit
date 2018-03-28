package com.kidslab.admin.memberlist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.admin.login.vo.AdminLoginVO;
import com.kidslab.admin.memberlist.service.MemberListService;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.common.page.Paging;
import com.kidslab.common.util.Util;

@Controller
@RequestMapping(value = "/admin")
public class MemberListController {

	Logger logger = Logger.getLogger(MemberListController.class);

	@Autowired
	private MemberListService memberListService;

	/***********************************
	 * 학생 리스트
	 ***********************************/
	@RequestMapping(value = "/member/studentList")
	public String studentList(@ModelAttribute StudentVO svo, Model model, HttpSession session) {
		AdminLoginVO vo = new AdminLoginVO();
		vo = (AdminLoginVO) session.getAttribute("adminLogin");
		if (vo == null) {
			return "redirect:/admin/login";
		}
		logger.info("studentList 호출");

		Paging.setPage(svo);

		// 전체 레코드 수 (추가)
		int total = memberListService.studentListCnt(svo);
		logger.info("total = " + total);

		int count = total - (Util.nvl(svo.getPage()) - 1) * Util.nvl(svo.getPageSize());
		logger.info("count = " + count);

		List<StudentVO> studentList = memberListService.studentList(svo);

		model.addAttribute("studentList", studentList);

		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("studentData", svo);
		logger.info("page : " + svo.getPage());
		return "admin/member/studentList";

	}

	/***********************************
	 * 학부모 리스트
	 ***********************************/
	@RequestMapping(value = "/member/parentList")
	public String parentList(@ModelAttribute StudentVO svo, Model model, HttpSession session) {
		AdminLoginVO vo = new AdminLoginVO();
		vo = (AdminLoginVO) session.getAttribute("adminLogin");
		if (vo == null) {
			return "redirect:/admin/login";
		}
		logger.info("parentList 호출");

		Paging.setPage(svo);

		// 전체 레코드 수 (추가)
		int total = memberListService.parentListCnt(svo);
		logger.info("total = " + total);

		int count = total - (Util.nvl(svo.getPage()) - 1) * Util.nvl(svo.getPageSize());
		logger.info("count = " + count);

		List<StudentVO> parentList = memberListService.parentList(svo);

		model.addAttribute("parentList", parentList);

		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("parentData", svo);
		logger.info("page : " + svo.getPage());
		return "admin/member/parentList";

	}

	/***********************************
	 * 강사 리스트
	 ***********************************/
	@RequestMapping(value = "/member/teacherList")
	public String teacherList(@ModelAttribute TeacherVO tvo, Model model, HttpSession session) {
		AdminLoginVO vo = new AdminLoginVO();
		vo = (AdminLoginVO) session.getAttribute("adminLogin");
		if (vo == null) {
			return "redirect:/admin/login";
		}
		logger.info("teacherList 호출");

		Paging.setPage(tvo);

		// 전체 레코드 수 (추가)
		int total = memberListService.teacherListCnt(tvo);
		logger.info("total = " + total);

		int count = total - (Util.nvl(tvo.getPage()) - 1) * Util.nvl(tvo.getPageSize());
		logger.info("count = " + count);

		List<TeacherVO> teacherList = memberListService.teacherList(tvo);
		model.addAttribute("teacherList", teacherList);

		model.addAttribute("count", count);
		model.addAttribute("total", total);

		model.addAttribute("teacherData", tvo);
		logger.info("page : " + tvo.getPage());
		return "admin/teacher/teacherList";

	}

	/*****************************************************************
	 * 학부모 가입 경로별 파이차트, 학년 별 인원 컬럼 바차트, 총 매출 라인차트
	 ******************************************************************/
	@ResponseBody
	@RequestMapping(value = "/member/joinRootList")
	public ModelAndView rootList(@ModelAttribute TeacherVO tvo, Model model, HttpSession session) {

		logger.info("joinRootList 호출");

		ModelAndView mav = new ModelAndView();
		AdminLoginVO vo = new AdminLoginVO();
		vo = (AdminLoginVO) session.getAttribute("adminLogin");
		if (vo == null) {
			mav.setViewName("redirect:/admin/login");
			return mav;
		}
		// Pie 차트
		Map<String, Integer> joinRootList = new HashMap<>();
		// Bar 차트
		Map<String, Integer> columnChart = new HashMap<>();
		List<RequestCourseVO> lineChart = memberListService.paymentStatsList();
		// db에서 column name = key, value = value
		joinRootList = memberListService.joinRootList();
		columnChart = memberListService.studentAgeList();
		// 콤마가 size보다 작을경우에만 찍기위한 변수
		int i = 0;
		// script에서 파이차트에 사용할 문장 만들기
		String str = "['알게된 경로' , 'count'] ,";
		for (Map.Entry<String, Integer> result : joinRootList.entrySet()) {
			str += "['" + result.getKey() + "'," + result.getValue() + "]";
			i++;
			if (i < joinRootList.size()) {
				str += ",";
			}
		}

		String strColumnChart;
		strColumnChart = "['학년', '인원수', { role: \"style\" }],";
		int j = 0;
		for (Map.Entry<String, Integer> result : columnChart.entrySet()) {
			strColumnChart += "['" + result.getKey() + "'," + result.getValue() + ",'#b87333']";
			j++;
			if (j < columnChart.size()) {
				strColumnChart += ",";
			}
		}
		// [1, 37.8, 80.8, 41.8]
		String strLineChart = "";
		for (int k = 0; k < lineChart.size(); k++) {
			logger.info("확인");
			if (lineChart.get(i).getRequestcourse_refundcomplete() == null) {
				strLineChart += "[new Date('20" + lineChart.get(k).getRequestcourse_paycompletedate().substring(0, 2)
						+ "','"
						+ (Integer.parseInt(lineChart.get(k).getRequestcourse_paycompletedate().substring(3, 5)) - 1)
						+ "'),";
			} else {
				strLineChart += "[new Date('20" + lineChart.get(k).getRequestcourse_refundcomplete().substring(0, 2)
						+ "','"
						+ (Integer.parseInt(lineChart.get(k).getRequestcourse_refundcomplete().substring(3, 5)) - 1)
						+ "'),";
			}
			strLineChart += lineChart.get(k).getRequestcourse_payamount() + ","
					+ lineChart.get(k).getRequestcourse_refundcharge() + ",";
			strLineChart += lineChart.get(k).getMargin() + "]";
			if (k < lineChart.size() - 1) {
				strLineChart += ",";
			}
			logger.info("확인밑에");
			logger.info(strLineChart);
		}
		mav.addObject("lineChart", strLineChart);
		mav.addObject("barChart", strColumnChart);
		mav.addObject("rootList", str);
		mav.setViewName("admin/chart/memberChart");
		return mav;

	}

}
