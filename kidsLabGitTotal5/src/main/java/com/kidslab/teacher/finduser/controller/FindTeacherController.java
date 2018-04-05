package com.kidslab.teacher.finduser.controller;

import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.teacher.finduser.service.FindTeacherService;

@Controller
@RequestMapping(value = "/teacher")
public class FindTeacherController {

	Logger logger = Logger.getLogger(FindTeacherController.class);

	@Autowired
	private FindTeacherService findTeacherService;

	@Autowired
	private JavaMailSender javaMailSender;

	/* 아이디 비밀번호 찾기 Form 불러오기 */
	@RequestMapping(value = "/login/findTeacher", method = RequestMethod.GET)
	public String findTeacher(Model model) {

		return "teacher/login/findTeacher";
	}

	/* teacher 아이디 찾기 POST */
	@ResponseBody
	@RequestMapping(value = "/login/findTeacher", method = RequestMethod.POST)
	public List<TeacherVO> findTeacherId(@ModelAttribute("TeacherVO") TeacherVO tvo, Model model) {

		List<TeacherVO> findIdCheck = findTeacherService.findTeacherId(tvo.getTeacher_name(), tvo.getTeacher_phone());

		return findIdCheck;
	}

	/* teacher 비밀번호 재발급 POST */
	@ResponseBody
	@RequestMapping(value = "/login/findTeacherPw", method = RequestMethod.POST)
	public int findTeacherPw(@ModelAttribute("TeacherVO") TeacherVO tvo, Model model) {

		int result = 2;

		TeacherVO findPwCheck = findTeacherService.findTeacherPw(tvo.getTeacher_id(), tvo.getTeacher_name());

		if (findPwCheck != null) {

			Random num = new Random();
			StringBuffer buf = new StringBuffer();

			for (int i = 0; i < 8; i++) {
				if (num.nextBoolean()) {
					buf.append((char) ((int) (num.nextInt(26)) + 97)); // true 일시 랜덤 소문자를
				} else {
					buf.append((num.nextInt(10))); // false 일시 랜덤한 숫자를
				}
			}

			String ranNum = buf + "";

			result = findTeacherService.insertTeacherTemporaryPw(tvo, ranNum);

			/* 이메일 전송 */
			String subject = "임시비밀번호 발급 안내 입니다.";
			StringBuilder content = new StringBuilder();
			content.append("귀하의 임시 비밀번호는 " + ranNum + " 입니다.");

			MimeMessage message = javaMailSender.createMimeMessage();

			String from = "kidslab";
			String email = tvo.getTeacher_id();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				helper.setSubject(subject);
				helper.setText(content.toString());
				helper.setFrom(from);
				helper.setTo(email);

				javaMailSender.send(message);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return result; // 오류시 find 창 그대로
			}
			return result; // 임시비밀번호 발급 시 로그인 창으로 이동
		}
		return result; // 데이터 없을 시 find 창 그대로
	}

}
