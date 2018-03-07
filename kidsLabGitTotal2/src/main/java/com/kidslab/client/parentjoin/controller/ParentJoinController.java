package com.kidslab.client.parentjoin.controller;

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

import com.kidslab.client.login.vo.UserLoginVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.parentjoin.service.ParentJoinService;

@Controller
@RequestMapping(value = "/member")
public class ParentJoinController {

	Logger logger = Logger.getLogger(ParentJoinController.class);

	@Autowired
	private ParentJoinService parentJoinService;

	/*
	 * 약관동의 폼
	 */
	@RequestMapping(value = "/agree.do", method = RequestMethod.GET)
	public String agreeForm(Model model) {
		logger.info("약관동의 페이지 호출!");
		return "client/member/agree";
	}

	/*
	 * 학부모 회원가입 폼
	 */
	@RequestMapping(value = "/parentjoin.do", method = RequestMethod.GET)
	public String paretJoinForm(Model model) {
		logger.info("회원가입폼 오픈!");
		return "client/member/parentJoin";
	}

	/* 학부모 아이디 중복체크 메소드 */
	@ResponseBody
	@RequestMapping(value = "/parentIdConfirm.do", method = RequestMethod.POST)
	public int parentIdConfirm(@RequestParam("userId") String userId) {
		logger.info("아이디 중복확인!");
		int result = parentJoinService.parentIdConfirm(userId);
		return result;
	}

	/* 이메일 발송 */
	@ResponseBody
	@RequestMapping(value = "/sendMail.do", method = RequestMethod.POST)
	public String sendMail(HttpSession session, @RequestParam("userId") String email) {
		logger.info("인증번호 발송!");
		String ranNum;
		ranNum = parentJoinService.sendMail(session, "kidslab", email);
		return ranNum;
	}

	/* 인증번호 체크 */
	@ResponseBody
	@RequestMapping(value = "/confirmCheck.do", method = RequestMethod.POST)
	public int numberCheck(HttpSession session, @RequestParam("userId") String email,
			@RequestParam("certification") String certifNum) {
		logger.info("인증번호 체크!");
		int check;
		check = parentJoinService.numberCheck(session, email, certifNum);
		return check;

	}

	/* 회원가입 처리 */
	@RequestMapping(value = "/parentjoin.do", method = RequestMethod.POST)
	public ModelAndView parentInsert(@ModelAttribute ParentVO pvo) {

		logger.info("회원가입 처리 시도!");
		ModelAndView mav = new ModelAndView();

		int result = 0;

		result = parentJoinService.parentInsert(pvo);

		System.out.println(result);

		if (result == 1) {
			mav.setViewName("client/member/login"); // 회원가입 성공시 로그인 페이지로
			mav.addObject("resultCode", result);
		} else if (result == 2) {
			mav.setViewName("client/member/parentJoin"); // 실패시
			mav.addObject("resultCode", result);
		} else {
			mav.setViewName("home"); // 그밖
			mav.addObject("resultCode", result);
		}

		return mav;

	}

	/* 회원 수정 form */
	@RequestMapping(value = "/modifyparentinfo.do", method = RequestMethod.POST)
	public ModelAndView parentModify(HttpSession session) {

		logger.info("modifyparent get 방식 폼 호출");

		ModelAndView mav = new ModelAndView();

		UserLoginVO login = (UserLoginVO) session.getAttribute("login");

		ParentVO vo = parentJoinService.parentSelect(login.getUserId());
		mav.addObject("parent", vo);
		mav.setViewName("client/member/modifyParentInfo");

		return mav;

	}

}
