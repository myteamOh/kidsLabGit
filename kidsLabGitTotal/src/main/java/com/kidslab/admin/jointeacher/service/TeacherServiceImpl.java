package com.kidslab.admin.jointeacher.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.jointeacher.dao.TeacherDao;
import com.kidslab.admin.jointeacher.vo.TeacherSecurity;
import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.common.util.OpenCrypt;
import com.kidslab.common.util.Util;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

	Logger logger = Logger.getLogger(TeacherServiceImpl.class);

	@Autowired
	private TeacherDao teacherDao;

	@Override
	public int userIdConfirm(String teacher_id) {
		// TODO Auto-generated method stub
		int result;
		if (teacherDao.teacherSelect(teacher_id) != null) {
			result = 1;
		} else {
			result = 2;
		}
		return result;
	}

	@Override
	public TeacherVO teacherSelect(String teacher_id) {
		// TODO Auto-generated method stub
		TeacherVO vo = teacherDao.teacherSelect(teacher_id);
		return vo;
	}

	@Override
	public int teacherInsert(TeacherVO tvo) {
		// TODO Auto-generated method stub
		int sCode = 2;
		if (teacherDao.teacherSelect(tvo.getTeacher_id()) != null) {
			return 1;
		} else {
			try {
				TeacherSecurity sec = new TeacherSecurity();
				sec.setTeacher_id(tvo.getTeacher_id());
				sec.setSalt(Util.getRandomString());
				sCode = teacherDao.securityInsert(sec);

				if (sCode == 1) {
					tvo.setTeacher_password(new String(OpenCrypt.getSHA256(tvo.getTeacher_password(), sec.getSalt())));
					teacherDao.teacherInsert(tvo);
					return 3;
				} else {
					return 2;
				}
			} catch (RuntimeException e) {
				// TODO: handle exception
				e.printStackTrace();
				return 2;
			}
		}
	}

	@Override
	public boolean teacherUpdate(TeacherVO tvo) {
		// TODO Auto-generated method stub
		try {
			if (!tvo.getTeacher_password().isEmpty()) {
				TeacherSecurity sec = teacherDao.securitySelect(tvo.getTeacher_id());
				tvo.setTeacher_password(new String(OpenCrypt.getSHA256(tvo.getTeacher_password(), sec.getSalt())));
			}
			teacherDao.teacherUpdate(tvo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int teacherDelete(String userId) {
		// TODO Auto-generated method stub
		int mCode, sCode, isSuccessCode = 3;
		try {
			mCode = teacherDao.teacherDelete(userId);
			if (mCode == 1) {
				sCode = teacherDao.securityDelete(userId);
				if (sCode == 1) {
					isSuccessCode = 2;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isSuccessCode = 3;
		}
		return isSuccessCode;
	}

}
