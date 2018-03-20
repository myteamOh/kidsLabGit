package com.kidslab.client.login.vo;

import com.kidslab.common.vo.CommonVO;

public class UserLoginVO extends CommonVO {

	private String userId; // 사용자 ID
	private String userPw; // 사용자 PW
	private String userName; // 사용자 이름

	public UserLoginVO() {
		super();
	}

	public UserLoginVO(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserLoginVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + "]";
	}

}
