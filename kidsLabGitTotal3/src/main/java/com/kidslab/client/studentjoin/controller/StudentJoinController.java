package com.kidslab.client.studentjoin.controller;

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

import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.client.studentjoin.service.StudentJoinService;

@Controller
@RequestMapping(value = "/member")
public class StudentJoinController {

	Logger logger = Logger.getLogger(StudentJoinController.class);

	@Autowired
	private StudentJoinService studentJoinService;

	/* 자녀 추가 page */
	@RequestMapping(value = "/studentjoin.do", method = RequestMethod.GET)
	public String studentJoin(Model model) {

		logger.info("자녀추가 form");

		return "client/member/studentJoin";

	}

	/* 중복체크 */
	@ResponseBody
	@RequestMapping(value = "studentIdConfirm.do", method = RequestMethod.POST)
	public int sutdentIdConfirm(@RequestParam("userId") String userId) {
		logger.info("학생 아이디 중복확인!");
		int result = studentJoinService.studentIdConfirm(userId);
		return result;
	}

	/* 자녀 추가 처리 */
	@RequestMapping(value = "/studentjoin.do", method = RequestMethod.POST)
	public ModelAndView studentInsert(@ModelAttribute StudentVO svo) {

		logger.info("자녀 추가 처리");

		ModelAndView mav = new ModelAndView();

		int result = 0;

		result = studentJoinService.studentInsert(svo);

		System.out.println(result);

		if (result == 1) {
			mav.addObject("resultCode", 1);
			mav.setViewName("client/mypage/mypageParent"); // 회원가입 성공시 학부모 마이 페이지로
		} else if (result == 2) {
			mav.addObject("resultCode", 2);
			mav.setViewName("client/member/studentJoin"); // 실패시
		} else {
			mav.addObject("resultCode", 3);
			mav.setViewName("home"); // 그밖
		}

		return mav;
	}

}
