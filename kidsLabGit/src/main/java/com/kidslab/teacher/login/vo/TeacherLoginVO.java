package com.kidslab.teacher.login.vo;

public class TeacherLoginVO extends LoginHistory {
	private String teacher_id = "";
	private String teacher_password = "";
	private String teacher_name = "";

	public TeacherLoginVO() {
		super();
	}

	public TeacherLoginVO(String teacher_id, String teacher_password, String teacher_name) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_password = teacher_password;
		this.teacher_name = teacher_name;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getTeacher_password() {
		return teacher_password;
	}

	public void setTeacher_password(String teacher_password) {
		this.teacher_password = teacher_password;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	@Override
	public String toString() {
		return "TeacherLoginVO [teacher_id=" + teacher_id + ", teacher_password=" + teacher_password + ", teacher_name="
				+ teacher_name + ", getIdx()=" + getIdx() + ", getRetry()=" + getRetry() + ", getLastFailedLogin()="
				+ getLastFailedLogin() + ", getLastSuccessedLogin()=" + getLastSuccessedLogin() + ", getClientIP()="
				+ getClientIP() + "]";
	}

}
