package com.kidslab.teacher.registcourse.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.teacher.registcourse.dao.RegistCourseDao;
import com.kidslab.teacher.registcourse.vo.RegistCourseVO;

@Service
@Transactional
public class RegistCourseServiceImpl implements RegistCourseService {

	Logger logger = Logger.getLogger(RegistCourseServiceImpl.class);

	@Autowired
	private RegistCourseDao registCourseDao;

	// 강의 등록 구현
	@Override
	public int registCourseInsert(RegistCourseVO rvo) {
	
		int result = 0;
	
		try {
			result = registCourseDao.registCourseInsert(rvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

}
