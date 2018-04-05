package com.kidslab.client.finduser.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kidslab.client.finduser.service.FindUserService;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

@Controller
@RequestMapping(value = "/member")
public class FindUserController {

	Logger logger = Logger.getLogger(FindUserController.class);

	@Autowired
	private FindUserService findUserService;

	@Autowired
	private JavaMailSender javaMailSender;

	/* 아이디 비밀번호찾기 Form */
	@RequestMapping(value = "/finduser", method = RequestMethod.GET)
	public String findUser(Model model) {
		return "client/member/findUser";
	}

	/* 학부모 아이디 찾기 처리 */
	@ResponseBody
	@RequestMapping(value = "/findparentid", method = RequestMethod.POST)
	public ModelAndView findParentId(@ModelAttribute("ParentVO") ParentVO pvo, Model model) {

		ModelAndView mav = new ModelAndView();

		// 입력한 학부모 이름과 전화번호로 해당하는 아이디 list로 가져오기
		List<ParentVO> findIdCheck = findUserService.findParentId(pvo.getParent_name(), pvo.getParent_phone());

		// list 가 있다면 if문 실행
		if (findIdCheck.size() > 0) {

			model.addAttribute("parentIdList", findIdCheck);

			mav.addObject("list", "success");
			mav.setViewName("client/member/findUser");
			return mav;
		}

		mav.addObject("list", "fail");
		mav.setViewName("client/member/findUser");

		return mav;

	}

	/* 학부모 비밀번호 재발급 처리 */
	@ResponseBody
	@RequestMapping(value = "/findparentpw", method = RequestMethod.POST)
	public int findParentPw(@ModelAttribute("ParentVO") ParentVO pvo, Model model) {

		int result = 2;

		// 아이디와 이름으로 정보 있는지 체크
		ParentVO findPwCheck = findUserService.findParentPw(pvo.getUserId(), pvo.getParent_name());

		// 해당하는 정보가 있다면 다음 if문 실행
		if (findPwCheck != null) {

			/* 랜덤변수 임시비밀번호 발생 */
			Random num = new Random();
			StringBuffer buf = new StringBuffer();

			// 8자리 랜덤 임시비밀번호 발생
			for (int i = 0; i < 8; i++) {
				if (num.nextBoolean()) {
					buf.append((char) ((int) (num.nextInt(26)) + 97)); // true 일시 랜덤한 소문자를
				} else {
					buf.append((num.nextInt(10))); // false 일시 랜덤한 숫자를
				}
			}

			String ranNum = buf + ""; // 랜덤 숫자, 영문자 저장

			/* 임시비밀번호를 새비밀번호로 설정 */
			result = findUserService.insertTemporaryPw(pvo, ranNum);

			/* 이메일 전송 */
			String subject = "임시비밀번호 발급 안내 입니다.";
			StringBuilder content = new StringBuilder();
			content.append("귀하의 임시 비밀번호는 " + ranNum + " 입니다.");

			// JavaMailSender메소드 보낼메세지 설정
			MimeMessage message = javaMailSender.createMimeMessage();

			String from = "kidslab";
			String email = pvo.getUserId(); // 받을사람

			// 이메일 전송
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				helper.setSubject(subject);
				helper.setText(content.toString());
				helper.setFrom(from);
				helper.setTo(email);

				javaMailSender.send(message);

			} catch (MessagingException e) {
				e.printStackTrace();
				return result; // 오류시 find창 그대로
			}

			return result; // 임시비밀번호 발급시 로그인 창으로 이동.
		}

		return result; // 데이터 없을시 find창 그대로

	}

	/* 학생 아이디 찾기 처리 */
	@ResponseBody
	@RequestMapping(value = "/findstudentid", method = RequestMethod.POST)
	public ModelAndView findStudentId(@ModelAttribute("StudentVO") StudentVO svo, Model model) {

		ModelAndView mav = new ModelAndView();

		// 입력한 학생 이름과 생년월일에 해당하는 아이디 가져옴
		List<StudentVO> findIdCheck = findUserService.findStudentId(svo.getStudent_name(), svo.getStudent_birthday());

		// list가 있다면 다음 if문 실행
		if (findIdCheck.size() > 0) {

			model.addAttribute("studentIdList", findIdCheck);

			mav.addObject("studnetList", "success");
			mav.setViewName("client/member/findUser");
			return mav;
		}

		mav.addObject("studentList", "fail");
		mav.setViewName("client/member/findUser");

		return mav;
	}

	/* 학생 정보 check 메소드 */
	@ResponseBody
	@RequestMapping(value = "/checkstudentinfo", method = RequestMethod.POST)
	public int checkStudentInfo(@ModelAttribute("StudentVO") StudentVO svo, HttpSession session) {

		int result = 0;

		if (findUserService.checkStudentInfo(svo) != null) {
			session.setAttribute("studentIdCheck", svo.getUserId());
			session.setAttribute("studentNameCheck", svo.getStudent_name());
			result = 1;
		} else {
			result = 2;
		}

		return result;
	}

	/* 학생 새 비밀번호 설정 Form */
	@RequestMapping(value = "/newstudentpw", method = RequestMethod.GET)
	public String newStudentPw(Model model) {

		logger.info("학생 새 비밀번호설정 폼 출력!");

		return "client/member/pwChange";
	}

	/* 학생 새 비밀번호 설정 처리 */
	@ResponseBody
	@RequestMapping(value = "/newstudentpw", method = RequestMethod.POST)
	public int newStudentPwInsert(@ModelAttribute("StudentVO") StudentVO svo, HttpSession session) {

		int result = 0;

		svo.setUserId(session.getAttribute("studentIdCheck").toString());
		svo.setStudent_name(session.getAttribute("studentNameCheck").toString());

		result = findUserService.newStudentPwInsert(svo);

		return result;
	}

}
