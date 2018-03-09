package com.kidslab.client.mypage.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kidslab.client.parent.vo.ParentVO;

public class MypageDaoImpl implements MypageDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public ParentVO parentInfo(int parentNum) {
		return session.selectOne("parentInfo", parentNum);
	}

}
