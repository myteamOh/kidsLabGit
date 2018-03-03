package com.kidslab.client.parentjoin.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.parentjoin.dao.ParentJoinDao;
import com.kidslab.common.util.OpenCrypt;
import com.kidslab.common.util.Util;

@Service
@Transactional
public class ParentJoinServiceImpl implements ParentJoinService {

	@Autowired
	ParentJoinDao parentJoinDao;

	@Autowired
	JavaMailSender javaMailSender;

	public ParentVO parentSelect(String userId) {
		ParentVO vo = parentJoinDao.parentSelect(userId);
		return vo;
	}

	/* 학부모 아이디 중복확인. 1 = 가입가능, 2 = 사용중이거나 탈퇴된 아이디 */
	@Override
	public int parentIdConfirm(String userId) {

		int result;

		if (parentJoinDao.parentSelect(userId) == null) {
			result = 1;
		} else {
			result = 2;
		}

		return result;
	}

	/* 메일 발송 */
	@Override
	public String sendMail(HttpSession session, String from, String email) {

		int num = new Random().nextInt(1000000);
		String ranNum = String.valueOf(num);

		System.out.println(email);
		System.out.println(ranNum);

		session.setAttribute("parentId", email);
		session.setAttribute("ranNum", ranNum);

		System.out.println(session.getAttribute("parentId"));
		System.out.println(session.getAttribute("ranNum"));

		String subject = "회원가입 인증 코드 발급 안내 입니다.";
		StringBuilder content = new StringBuilder();
		content.append("귀하의 인증 코드는 " + ranNum + " 입니다.");

		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setSubject(subject);
			helper.setText(content.toString());
			helper.setFrom(from);
			helper.setTo(email);

			javaMailSender.send(message);

			return ranNum;

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return null;

	}

	/* 인증번호 확인 */
	@Override
	public int numberCheck(HttpSession session, String email, String certifNum) {

		int check;

		System.out.println(email);
		System.out.println(certifNum);
		System.out.println(session.getAttribute("parentId"));
		System.out.println(session.getAttribute("ranNum"));

		if (email.equals(session.getAttribute("parentId").toString())
				&& certifNum.equals(session.getAttribute("ranNum").toString())) {
			check = 1;
		} else {
			check = 2;
		}

		return check;
	}

	/* 회원가입 처리 */
	@Override
	public int parentInsert(ParentVO pvo) {

		int securityCode = 2;

		System.out.println(pvo.getParent_smsagree());
		System.out.println(pvo.getParent_emailagree());
		System.out.println(pvo.getParent_kakaoagree());

		// checkbox value 변경 on - Y, null - N
		if (pvo.getParent_smsagree() != null) {
			pvo.setParent_smsagree("Y");
		} else {
			pvo.setParent_smsagree("N");
		}

		if (pvo.getParent_emailagree() != null) {
			pvo.setParent_emailagree("Y");
		} else {
			pvo.setParent_emailagree("N");
		}

		if (pvo.getParent_kakaoagree() != null) {
			pvo.setParent_kakaoagree("Y");
		} else {
			pvo.setParent_kakaoagree("N");
		}

		try {
			UserSecurity security = new UserSecurity();

			security.setUserId(pvo.getUserId());

			security.setSalt(Util.getRandomString());

			securityCode = parentJoinDao.securityInsert(security);

			if (securityCode == 1) {
				pvo.setUserPw(new String(OpenCrypt.getSHA256(pvo.getUserPw(), security.getSalt())));
				parentJoinDao.parentInsert(pvo);
				return 1;
			} else {
				return 2;
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			return 2;
		}

	}

}
