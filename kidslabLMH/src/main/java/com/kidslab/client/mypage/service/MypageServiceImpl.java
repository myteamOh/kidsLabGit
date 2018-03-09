package com.kidslab.client.mypage.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.kidslab.client.mypage.dao.MypageDao;
import com.kidslab.client.parent.vo.ParentVO;

public class MypageServiceImpl implements MypageService {

	Logger logger = Logger.getLogger(MypageServiceImpl.class);
	
	@Autowired
	private MypageDao mypageDao;
	
	@Override
	public ParentVO parentModify(int parentNum) {
		
		ParentVO pvo = null;
		
		pvo = mypageDao.parentInfo(parentNum);
		
		return pvo;
	}

}
