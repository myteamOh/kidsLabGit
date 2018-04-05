package com.kidslab.teacher.login.vo;

import com.kidslab.common.vo.CommonVO;

public class TeacherLoginVO extends CommonVO {
	private String teacher_id = ""; // 강사 ID
	private String teacher_password = ""; // 강사 PW
	private String teacher_name = ""; // 강사 이름
	private int teacher_no; // 강사 번호

	public TeacherLoginVO() {
		super();
	}

	public TeacherLoginVO(String teacher_id, String teacher_password, String teacher_name, int teacher_no) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_password = teacher_password;
		this.teacher_name = teacher_name;
		this.teacher_no = teacher_no;
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

	public int getTeacher_no() {
		return teacher_no;
	}

	public void setTeacher_no(int teacher_no) {
		this.teacher_no = teacher_no;
	}

	@Override
	public String toString() {
		return "TeacherLoginVO [teacher_id=" + teacher_id + ", teacher_password=" + teacher_password + ", teacher_name="
				+ teacher_name + ", teacher_no=" + teacher_no + ", getIdx()=" + getIdx() + ", getRetry()=" + getRetry()
				+ ", getLastFailedLogin()=" + getLastFailedLogin() + ", getLastSuccessedLogin()="
				+ getLastSuccessedLogin() + ", getClientIP()=" + getClientIP() + "]";
	}

}
