package com.kidslab.client.studentjoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.parentjoin.dao.ParentJoinDao;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.client.studentjoin.dao.StudentJoinDao;
import com.kidslab.common.util.OpenCrypt;
import com.kidslab.common.util.Util;

@Service
@Transactional
public class StudentJoinServiceImpl implements StudentJoinService {

	@Autowired
	private StudentJoinDao studentJoinDao;

	@Autowired
	private ParentJoinDao parentJoinDao;

	@Override
	public StudentVO studentSelect(String userId) {
		StudentVO vo = studentJoinDao.studentSelect(userId);
		return vo;
	}

	/* 1은 사용가능 2는 사용불가능 */
	@Override
	public int studentIdConfirm(String userId) {

		int confirmResult;

		if (studentJoinDao.studentSelect(userId) == null) {
			confirmResult = 1;
		} else {
			confirmResult = 2;
		}

		return confirmResult;
	}

	/* 학생 등록 */
	@Override
	public int studentInsert(StudentVO svo) {

		int securityCode = 0;

		try {
			UserSecurity security = new UserSecurity();

			security.setUserId(svo.getUserId());
			
			security.setSalt(Util.getRandomString());

			securityCode = parentJoinDao.securityInsert(security);

			if (securityCode == 1) {
				svo.setUserPw(new String(OpenCrypt.getSHA256(svo.getUserPw(), security.getSalt())));
				studentJoinDao.studentInsert(svo);
				return 1;
			} else {
				return 2;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			return 2;
		}

	}

	/*학생 정보 수정*/
	@Override
	public boolean studentUpdate(StudentVO svo) {
		
		try {
			if(!svo.getUserPw().isEmpty()) {
				UserSecurity security = parentJoinDao.securitySelect(svo.getUserId());
				svo.setUserPw(new String(OpenCrypt.getSHA256(svo.getUserPw(), security.getSalt())));
			}
			studentJoinDao.studentUpdate(svo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
