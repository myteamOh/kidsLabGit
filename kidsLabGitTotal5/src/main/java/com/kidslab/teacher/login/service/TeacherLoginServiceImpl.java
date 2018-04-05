package com.kidslab.teacher.login.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.jointeacher.dao.TeacherDao;
import com.kidslab.admin.jointeacher.vo.TeacherSecurity;
import com.kidslab.common.util.OpenCrypt;
import com.kidslab.teacher.login.dao.TeacherLoginDao;
import com.kidslab.teacher.login.vo.TeacherLoginVO;

@Service
@Transactional
public class TeacherLoginServiceImpl implements TeacherLoginService {

	Logger logger = Logger.getLogger(TeacherLoginServiceImpl.class);

	@Autowired
	private TeacherLoginDao teacherLoginDao;

	@Autowired
	private TeacherDao teacherDao;

	@Override
	public TeacherLoginVO userIdSelect(String teacher_id) {
		return teacherLoginDao.userIdSelect(teacher_id);
	}

	@Override
	public TeacherLoginVO loginSelect(String teacher_id, String teacher_password) {

		TeacherLoginVO vo = null;
		TeacherSecurity sec = teacherDao.securitySelect(teacher_id);

		if (sec != null) {
			teacher_password = new String(OpenCrypt.getSHA256(teacher_password, sec.getSalt()));

			TeacherLoginVO lvo = new TeacherLoginVO();
			lvo.setTeacher_id(teacher_id);
			lvo.setTeacher_password(teacher_password);

			vo = teacherLoginDao.loginSelect(lvo);
		}

		return vo;
	}

	@Override
	public int loginHistoryInsert(TeacherLoginVO lvo) {
	
		int result;
	
		if (userIdSelect(lvo.getTeacher_id()) == null) {
			result = 1;
		} else {
			TeacherLoginVO vo = loginHistorySelect(lvo.getTeacher_id());
			if (vo == null) {
				teacherLoginDao.loginHistoryInsert(lvo);
			}
			result = 2;
		}
	
		return result;
	}

	@Override
	public int loginHistoryUpdate(TeacherLoginVO lvo) {
		return teacherLoginDao.loginHistoryUpdate(lvo);
	}

	@Override
	public TeacherLoginVO loginHistorySelect(String teacher_id) {
		return teacherLoginDao.loginHistorySelect(teacher_id);
	}

}
