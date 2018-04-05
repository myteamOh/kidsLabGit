package com.kidslab.teacher.login.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.teacher.login.service.TeacherLoginService;
import com.kidslab.teacher.login.vo.TeacherLoginVO;

@Controller
@RequestMapping("/teacher")
public class TeacherLoginController {
	Logger logger = Logger.getLogger(TeacherLoginController.class);

	@Autowired
	private TeacherLoginService teacherLoginService;

	/***********************************
	 * 로그인 화면 보여주기 위한 메소드
	 ***********************************/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {

		TeacherLoginVO tvo = (TeacherLoginVO) session.getAttribute("teacherLogin");
	
		if (tvo != null) {
			return "teacher/mypage/teacherMypage";
		}
		return "teacher/login/login";
	}

	/****************************************
	 * 로그인 처리 메소드 참고 : 로그인 실패시 처리 내용 포함
	 *****************************************/
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginProc(@ModelAttribute("TeacherLoginVO") TeacherLoginVO lvo, HttpSession session,
			HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		String userId = lvo.getTeacher_id();

		int resultData = teacherLoginService.loginHistoryInsert(lvo);

		if (resultData == 1) {
			mav.addObject("errCode", 1);
			mav.setViewName("teacher/login/login");
			return mav;
		} else {
			TeacherLoginVO vo = teacherLoginService.loginHistorySelect(userId);

			// 로그인 시도횟수가 5회가 넘으면 30초간 로그인 잠금
			if (vo.getRetry() >= 5) {
				if (new Date().getTime() - vo.getLastFailedLogin() < 30000) {
					mav.addObject("errCode", 6); // 30초동안 로그인잠금 알림
					mav.setViewName("teacher/login/login");
					return mav;
				} else {
					vo.setRetry(0);
	
					vo.setLastFailedLogin(0);
					
					teacherLoginService.loginHistoryUpdate(vo);
				}
			}
		
			TeacherLoginVO loginCheckResult = teacherLoginService.loginSelect(lvo.getTeacher_id(),
					lvo.getTeacher_password());

			// 로그인이 틀리면, 로그인 시도횟수를 1증가 시키고,
			// 로그인 실패 시간을 DB에 업데이트 한다.
			if (loginCheckResult == null) {
				vo.setRetry(vo.getRetry() + 1);
				vo.setLastFailedLogin(new Date().getTime());
				
				teacherLoginService.loginHistoryUpdate(vo);

				mav.addObject("retry", vo.getRetry());
				mav.addObject("errCode", 1);
				mav.setViewName("teacher/login/login");
				return mav;
			}
			// 로그인이 성공하면, 로그인 시도횟수를 0으로 reset,
			// 마지막으로 로그인 실패 시간 0으로 reset
			// 성공한 클라이언트 IP를 DB에 업데이트, 로그인 성공시간 DB에 업데이트
			else {
				vo.setRetry(0);
				vo.setLastFailedLogin(0);
				vo.setLastSuccessedLogin(0);
				vo.setClientIP(request.getRemoteAddr());
				teacherLoginService.loginHistoryUpdate(vo);

				session.setAttribute("teacherLogin", loginCheckResult);
				mav.setViewName("redirect:/");
				return mav;
			}
		}
	}

	/**************************************
	 * 로그아웃 처리 메소드
	 **************************************/
	@RequestMapping("/logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		session = request.getSession(true);
		return "redirect:/teacher/login";
	}
}
