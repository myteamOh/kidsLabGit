package com.kidslab.client.parentjoin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kidslab.client.parent.vo.ParentVO;

public class ParentJoinDaoImpl implements ParentJoinDao {

	@Autowired
	private SqlSession session;

	@Override
	public ParentVO parentSelect(String parentId) {
		return (ParentVO) session.selectOne("parentSelect", parentId);
	}

}