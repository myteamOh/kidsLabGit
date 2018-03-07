package com.kidslab.client.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.login.dao.LoginDao;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.parentjoin.dao.ParentJoinDao;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.common.util.OpenCrypt;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ParentJoinDao pDao;

	@Autowired
	private LoginDao loginDao;

	/* 학부모 로그인 */
	@Override
	public ParentVO loginSelect(String userId, String userPw) {

		ParentVO vo = null;

		UserSecurity parentSecurity = pDao.securitySelect(userId);

		if (parentSecurity != null) {

			userPw = new String(OpenCrypt.getSHA256(userPw, parentSecurity.getSalt()));

			ParentVO pvo = new ParentVO();
			pvo.setUserId(userId);
			pvo.setUserPw(userPw);

			vo = loginDao.loginSelect(pvo);

		}

		return vo;
	}

	/* 학생 로그인 */
	@Override
	public StudentVO loginSelectS(String userId, String userPw) {

		StudentVO vo = null;

		UserSecurity studentSecurity = pDao.securitySelect(userId);

		if (studentSecurity != null) {
			userPw = new String(OpenCrypt.getSHA256(userPw, studentSecurity.getSalt()));

			StudentVO svo = new StudentVO();
			svo.setUserId(userId);
			svo.setUserPw(userPw);

			vo = loginDao.loginSelectS(svo);

		}

		return vo;
	}

}
