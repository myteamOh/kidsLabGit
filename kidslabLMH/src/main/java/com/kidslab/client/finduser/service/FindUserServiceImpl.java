package com.kidslab.client.finduser.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.finduser.dao.FindUserDao;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;
import com.kidslab.common.util.OpenCrypt;

@Service
@Transactional
public class FindUserServiceImpl implements FindUserService {

	Logger logger = Logger.getLogger(FindUserServiceImpl.class);

	@Autowired
	private FindUserDao findUserDao;

	/* 학부모 아이디 찾기 */
	@Override
	public List<ParentVO> findParentId(String parentName, String parentPhone) {

		logger.info("학부모 아이디 찾기 service!");

		ParentVO pvo = new ParentVO();

		System.out.println(parentName);
		System.out.println(parentPhone);

		pvo.setParent_name(parentName);
		pvo.setParent_phone(parentPhone);

		List<ParentVO> idList = null;

		idList = findUserDao.findParentId(pvo);
		System.out.println(idList.size());

		return idList;
	}

	/* 학부모 비밀번호 발급전 아이디 이름 체크 */
	@Override
	public ParentVO findParentPw(String parentId, String parentName) {

		logger.info("학부모 비밀번호 설정전 체크!");

		ParentVO pvo = new ParentVO();

		pvo.setUserId(parentId);
		pvo.setParent_name(parentName);

		pvo = findUserDao.matchParentId(pvo);

		return pvo;
	}

	/* 학부모 임시비밀번호 설정 */
	@Override
	public int insertTemporaryPw(ParentVO pvo, String ranNum) {

		logger.info("학부모 임시비밀번호 설정");

		System.out.println(pvo.getUserId());
		System.out.println(pvo.getParent_name());
		System.out.println(ranNum);

		try {
			UserSecurity security = findUserDao.temporarySecuritySelect(pvo.getUserId());
			System.out.println(security.getUserId());
			System.out.println(security.getSalt());

			pvo.setUserPw(new String(OpenCrypt.getSHA256(ranNum, security.getSalt())));

			findUserDao.temporaryPwUpdate(pvo);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();

			return 2;
		}

	}

	/* 학생 아이디 찾기 */
	@Override
	public List<StudentVO> findStudentId(String studentName, String studentBirthday) {

		logger.info("학생 아이디 찾기 service!");

		StudentVO svo = new StudentVO();

		svo.setStudent_name(studentName);
		svo.setStudent_birthday(studentBirthday);

		List<StudentVO> studentList = null;

		studentList = findUserDao.findStudentId(svo);
		System.out.println(studentList.size());

		return studentList;
	}

	/* 학생 정보 체크 */
	@Override
	public StudentVO checkStudentInfo(StudentVO svo) {

		logger.info("학생 정보 체크 service!");

		svo = findUserDao.checkStudentInfo(svo);

		return svo;
	}

	/* 학생 새 비밀번호 입력 */
	@Override
	public int newStudentPwInsert(StudentVO svo) {

		logger.info("학생 새 비밀번호 입력 service!");

		try {
			UserSecurity security = findUserDao.temporarySecuritySelect(svo.getUserId());
			System.out.println(security.getUserId());
			System.out.println(security.getSalt());

			svo.setUserPw(new String(OpenCrypt.getSHA256(svo.getUserPw(), security.getSalt())));

			findUserDao.newStudentPwInsert(svo);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();

			return 2;
		}

	}

}
