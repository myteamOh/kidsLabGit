package com.kidslab.client.parentjoin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.parent.vo.ParentVO;

@Repository("parentJoinDao")
public class ParentJoinDaoImpl implements ParentJoinDao {

	@Autowired
	private SqlSession session;

	@Override
	public ParentVO parentSelect(String userId) {
		return (ParentVO) session.selectOne("parentSelect", userId);
	}

	@Override
	public int parentInsert(ParentVO pvo) {
		return session.insert("parentInsert", pvo);
	}
	
	@Override
	public int parentUpdate(ParentVO pvo) {
		return session.update("parentUpdate", pvo);
	}

	@Override
	public UserSecurity securitySelect(String userId) {
		return (UserSecurity) session.selectOne("com.kidslab.client.parentjoin.dao.ParentJoinDao.securitySelect",
				userId);
	}

	@Override
	public int securityInsert(UserSecurity set) {
		return session.insert("com.kidslab.client.parentjoin.dao.ParentJoinDao.securityInsert", set);
	}

	@Override
	public int securityDelete(String userId) {
		return session.delete("com.kidslab.client.parentjoin.dao.ParentJoinDao.securityDelete", userId);
	}
	
	@Override
	public int parentWithdraw(ParentVO pvo) {
		return session.update("parentWithdraw", pvo);	
	}

}
