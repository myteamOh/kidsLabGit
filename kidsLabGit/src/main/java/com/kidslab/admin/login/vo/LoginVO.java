package com.kidslab.admin.login.vo;

public class LoginVO {
	private String userId = "";
	private String userPw = "";

	public LoginVO() {
		super();
	}

	public LoginVO(String userId, String userPw) {
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

}
