package com.kidslab.client.parent.vo;

public class ParentSecurity {
	private String userId;
	private String salt;

	public ParentSecurity() {
		super();
	}

	public ParentSecurity(String userId, String salt) {
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
		return "MemberSecurity [userId=" + userId + ", salt=" + salt + "]";
	}

}
