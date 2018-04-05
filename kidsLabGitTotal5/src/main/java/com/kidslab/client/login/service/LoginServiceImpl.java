package com.kidslab.client.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.login.dao.LoginDao;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.parentjoin.dao.ParentJoinDao;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.client.studentjoin.dao.StudentJoinDao;
import com.kidslab.common.util.OpenCrypt;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ParentJoinDao pDao;
	
	@Autowired
	private StudentJoinDao sDao;

	@Autowired
	private LoginDao loginDao;

	/* 학부모 로그인 */
	@Override
	public ParentVO loginSelect(String userId, String userPw) {

		ParentVO vo = null;
		ParentVO selectParent = new ParentVO();

		UserSecurity parentSecurity = pDao.securitySelect(userId);

		if (parentSecurity != null) {

			// 비밀번호 암호화
			userPw = new String(OpenCrypt.getSHA256(userPw, parentSecurity.getSalt()));

			ParentVO pvo = new ParentVO();
			pvo.setUserId(userId);
			pvo.setUserPw(userPw);

			vo = loginDao.loginSelect(pvo);
			if (vo != null) {
				selectParent = pDao.parentSelect(vo.getUserId());
			} else {
				return vo;
			}
		} else {
			return vo;
		}

		return selectParent;
	}

	/* 학생 로그인 */
	@Override
	public StudentVO loginSelectS(String userId, String userPw) {

		StudentVO vo = null;
		StudentVO selectStudent = new StudentVO();
				
		UserSecurity studentSecurity = pDao.securitySelect(userId);

		if (studentSecurity != null) {
			userPw = new String(OpenCrypt.getSHA256(userPw, studentSecurity.getSalt()));

			StudentVO svo = new StudentVO();
			svo.setUserId(userId);
			svo.setUserPw(userPw);

			vo = loginDao.loginSelectS(svo);
			
			if (vo != null) {
				selectStudent = sDao.studentSelect(vo.getUserId());
			} else {
				return vo;
			}

		} else  {
			return vo;
		}

		return selectStudent;
	}

}
