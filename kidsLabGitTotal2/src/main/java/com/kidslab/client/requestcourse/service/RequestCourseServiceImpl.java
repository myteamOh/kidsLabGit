package com.kidslab.client.requestcourse.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.requestcourse.dao.RequestCourseDao;

@Service
@Transactional
public class RequestCourseServiceImpl implements RequestCourseService {
	
	Logger logger = Logger.getLogger(RequestCourseServiceImpl.class);
	
	@Autowired
	private RequestCourseDao requestCourseDao;

}
