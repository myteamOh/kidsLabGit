package com.kidslab.client.studentjoin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.parentjoin.dao.ParentJoinDao;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.client.studentjoin.dao.StudentJoinDao;
import com.kidslab.common.util.OpenCrypt;
import com.kidslab.common.util.Util;

@Service
@Transactional
public class StudentJoinServiceImpl implements StudentJoinService {
	
	Logger logger = Logger.getLogger(StudentJoinServiceImpl.class);

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
	
	/*학부모 마이페이지 학생리스트 뽑기*/
	@Override
	public List<StudentVO> studentList(int parentNum) {
		
		logger.info("학생 리스트 뽑기");
		
		List<StudentVO> studentList = null;
		
		studentList = studentJoinDao.studentList(parentNum);
		
		return studentList;
	}
	
	/*학생 탈퇴 처리*/
	@Override
	public int studentWithdraw(ParentVO pvo) {
		
		logger.info("학생 탈퇴 처리");
		
		int result = studentJoinDao.studentWithdraw(pvo);
		
		return result;
	}

}
