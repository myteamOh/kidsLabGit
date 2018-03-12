package com.kidslab.client.parentjoin.dao;

import com.kidslab.client.common.vo.UserSecurity;
import com.kidslab.client.parent.vo.ParentVO;

public interface ParentJoinDao {

	public ParentVO parentSelect(String userId);

	public int parentInsert(ParentVO pvo);

	public UserSecurity securitySelect(String userId);

	public int securityInsert(UserSecurity set);

	public int securityDelete(String userId);

	public int parentUpdate(ParentVO pvo);

}
