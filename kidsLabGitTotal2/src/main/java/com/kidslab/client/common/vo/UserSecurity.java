package com.kidslab.client.common.vo;

public class UserSecurity {
	private String userId;	// 사용자 ID
	private String salt;	// 암호화

	public UserSecurity() {
		super();
	}

	public UserSecurity(String userId, String salt) {
		super();
		this.userId = userId;
		this.salt = salt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "ParentSecurity [userId=" + userId + ", salt=" + salt + "]";
	}

}
