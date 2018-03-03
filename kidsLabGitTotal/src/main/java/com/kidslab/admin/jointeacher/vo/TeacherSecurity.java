package com.kidslab.admin.jointeacher.vo;

public class TeacherSecurity {
	private String teacher_id = "";
	private String salt = "";

	public TeacherSecurity() {
		super();
	}

	public TeacherSecurity(String teacher_id, String salt) {
		super();
		this.teacher_id = teacher_id;
		this.salt = salt;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "TeacherSecurity [teacher_id=" + teacher_id + ", salt=" + salt + "]";
	}

}
