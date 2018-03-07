package com.kidslab.admin.login.vo;

public class AdminLoginVO {
	private String userId = "";
	private String userPw = "";
	private String userNo = "";

	public AdminLoginVO() {
		super();
	}

	public AdminLoginVO(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
	}

	public AdminLoginVO(String userId, String userPw, String userNo) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userNo = userNo;
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

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

}
