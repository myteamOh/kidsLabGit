package com.kidslab.teacher.finduser.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.jointeacher.vo.TeacherSecurity;
import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.common.util.OpenCrypt;
import com.kidslab.teacher.finduser.dao.FindTeacherDao;

@Service
@Transactional
public class FindTeacherServiceImpl implements FindTeacherService {

	Logger logger = Logger.getLogger(FindTeacherServiceImpl.class);

	@Autowired
	private FindTeacherDao findTeacherDao;

	// teacher 아이디 찾기
	@Override
	public List<TeacherVO> findTeacherId(String teacher_name, String teacher_phone) {
		
		TeacherVO tvo = new TeacherVO();

		tvo.setTeacher_name(teacher_name);
		tvo.setTeacher_phone(teacher_phone);

		List<TeacherVO> idList = null;
		idList = findTeacherDao.findTeacherId(tvo);

		return idList;
	}

	// teacher 비밀번호 발급 전 아이디 이름 체크
	@Override
	public TeacherVO findTeacherPw(String teacher_id, String teacher_name) {
	
		TeacherVO tvo = new TeacherVO();

		tvo.setTeacher_id(teacher_id);
		tvo.setTeacher_name(teacher_name);

		tvo = findTeacherDao.matchTeacherId(tvo);

		return tvo;
	}

	// teacher 임시비밀번호 설정
	@Override
	public int insertTeacherTemporaryPw(TeacherVO tvo, String ranNum) {
	
		int result = 2;
		
		try {
			TeacherSecurity security = findTeacherDao.temporaryTeacherSecuritySelect(tvo.getTeacher_id());
			tvo.setTeacher_password(new String(OpenCrypt.getSHA256(ranNum, security.getSalt())));

			findTeacherDao.temporaryTeacherPwUpdate(tvo);

			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			result = 2;
		}
		return result;
	}

}
