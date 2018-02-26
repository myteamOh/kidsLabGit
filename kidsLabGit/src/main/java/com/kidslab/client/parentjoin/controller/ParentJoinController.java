package com.kidslab.client.parentjoin.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public int parentIdConfirm(@RequestParam("parentId") String parentId) {
		int result = parentJoinService.parentIdConfirm(parentId);
		return result;
	}

	/* 이메일 발송 */
	@ResponseBody
	@RequestMapping(value = "/sendMail.do", method = RequestMethod.POST)
	public String sendMail(HttpSession session, @RequestParam("parentId") String email) {

		int num = new Random().nextInt(1000000);
		String ranNum = String.valueOf(num);

		System.out.println(email);
		System.out.println(ranNum);

		session.setAttribute("parent_id", email);
		session.setAttribute("ranNum", ranNum);

		System.out.println(session.getAttribute("parent_id"));
		System.out.println(session.getAttribute("ranNum"));

		String subject = "회원가입 인증 코드 발급 안내 입니다.";
		StringBuilder content = new StringBuilder();
		content.append("귀하의 인증 코드는 " + ranNum + " 입니다.");

		parentJoinService.sendMail(subject, content.toString(), "kidslab", email);

		return ranNum;
	}

	/* 인증번호 체크 */
	@ResponseBody
	@RequestMapping(value = "/confirmCheck.do", method = RequestMethod.POST)
	public int numberCheck(HttpSession session, @RequestParam("parentId") String email,
			@RequestParam("certification") String certifNum) {
		int check;

		logger.info("인증번호 쳌");

		System.out.println(email);
		System.out.println(certifNum);
		System.out.println(session.getAttribute("parent_id"));
		System.out.println(session.getAttribute("ranNum"));

		if (email == session.getAttribute("parent_id").toString()
				&& certifNum == session.getAttribute("ranNum").toString()) {
			check = 1;
		} else {
			check = 2;
		}

		return check;

	}

}
