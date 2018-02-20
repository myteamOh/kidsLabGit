package com.kidslab.admin.login.service;

import com.kidslab.admin.login.vo.LoginVO;

public interface LoginService {
	public LoginVO userIdSelect(String userId);

	public LoginVO loginSelect(String userId, String userPw);

	public int loginHistoryInsert(LoginVO lvo);

	public int loginHistoryUpdate(LoginVO lvo);

	public LoginVO loginHistorySelect(String userId);

}
