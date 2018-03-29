package com.kidslab.client.parentjoin.controller;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.parentjoin.service.ParentJoinService;

@Controller
@RequestMapping(value = "/member")
public class ParentJoinController {

	Logger logger = Logger.getLogger(ParentJoinController.class);

	@Autowired
	private ParentJoinService parentJoinService;

	@Autowired
	private JavaMailSender javaMailSender;
	
	/*
	 * 약관동의 폼
	 */
	@RequestMapping(value = "/agree", method = RequestMethod.GET)
	public String agreeForm(Model model) {
		logger.info("약관동의 페이지 호출!");
		return "client/member/agree";
	}

	/*
	 * 학부모 회원가입 폼
	 */
	@RequestMapping(value = "/parentjoin", method = RequestMethod.GET)
	public String paretJoinForm(Model model) {
		logger.info("회원가입폼 오픈!");
		return "client/member/parentJoin";
	}

	/* 학부모 아이디 중복체크 메소드 */
	@ResponseBody
	@RequestMapping(value = "/parentIdConfirm", method = RequestMethod.POST)
	public int parentIdConfirm(@RequestParam("userId") String userId) {
		logger.info("아이디 중복확인!");
		int result = parentJoinService.parentIdConfirm(userId);
		return result;
	}

	/* 이메일 발송 */
	@ResponseBody
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(HttpSession session, @RequestParam("userId") String email) {
		
		/* 6자리 랜덤 번호 발생 */
		Random num = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			buf.append((num.nextInt(10)));
		}
		
		String ranNum = buf + "";

		// 입력했던 아이디로 받은 인증번호인지 체크하기위해 번호와 입력한 이메일을 모두 session으로 남김.
		session.setAttribute("parentId", email);
		session.setAttribute("ranNum", ranNum);

		/* 메일에 보낼 메세지 */
		String subject = "회원가입 인증 코드 발급 안내 입니다.";
		StringBuilder content = new StringBuilder();
		content.append("귀하의 인증 코드는 " + ranNum + " 입니다.");

		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setSubject(subject); // 메일 제목
			helper.setText(content.toString()); // 보낼 내용
			helper.setFrom("kidslab"); // 보내는사람
			helper.setTo(email); // 받는사람

			// 메일보내기
			javaMailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		// 난수를 반환
		return ranNum;
	}

	/* 인증번호 체크 */
	@ResponseBody
	@RequestMapping(value = "/confirmCheck", method = RequestMethod.POST)
	public int numberCheck(HttpSession session, @RequestParam("userId") String email,
			@RequestParam("certification") String certifNum) {
		logger.info("인증번호 체크!");
		int check;
		check = parentJoinService.numberCheck(session, email, certifNum);
		return check;

	}

	/* 회원가입 처리 */
	@RequestMapping(value = "/parentjoin", method = RequestMethod.POST)
	public ModelAndView parentInsert(@ModelAttribute ParentVO pvo) {

		logger.info("회원가입 처리 시도!");
		ModelAndView mav = new ModelAndView();

		int result = 0;

		result = parentJoinService.parentInsert(pvo);

		if (result == 1) {
			mav.setViewName("client/member/login"); // 회원가입 성공시 로그인 페이지로
			mav.addObject("resultCode", result);
		} else if (result == 2) {
			mav.setViewName("client/member/parentJoin"); // 실패시
			mav.addObject("resultCode", result);
		} 

		return mav;

	}

}
