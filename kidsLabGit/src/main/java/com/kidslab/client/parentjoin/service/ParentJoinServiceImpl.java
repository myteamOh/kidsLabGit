package com.kidslab.client.parentjoin.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.parentjoin.dao.ParentJoinDao;

@Service
@Transactional
public class ParentJoinServiceImpl implements ParentJoinService {

	@Autowired
	ParentJoinDao parentJoinDao;

	@Autowired
	JavaMailSender javaMailSender;

	/* 학부모 아이디 중복확인. 1 = 가입가능, 2 = 사용중이거나 탈퇴된 아이디 */
	@Override
	public int parentIdConfirm(String parentId) {

		int result;

		if (parentJoinDao.parentSelect(parentId) == null) {
			result = 1;
		} else {
			result = 2;
		}

		return result;
	}

	/* 메일 발송 */
	@Override
	public void sendMail(String subject, String content, String from, String email) {

		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setSubject(subject);
			helper.setText(content);
			helper.setFrom(from);
			helper.setTo(email);

			javaMailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}