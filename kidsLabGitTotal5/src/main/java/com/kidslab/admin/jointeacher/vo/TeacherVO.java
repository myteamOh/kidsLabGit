package com.kidslab.admin.jointeacher.vo;

import org.springframework.web.multipart.MultipartFile;

import com.kidslab.teacher.login.vo.TeacherLoginVO;

public class TeacherVO extends TeacherLoginVO {
	private String oldUserPw;
	private String teacher_phone;
	private String teacher_photo;
	private String teacher_thumb;
	private String teacher_registerDate;
	private String teacher_status;
	private MultipartFile file;

	public TeacherVO() {
		super();
	}

	public TeacherVO(String oldUserPw, String teacher_phone, String teacher_photo, String teacher_thumb,
			String teacher_registerDate, String teacher_status, MultipartFile file) {
		super();
		this.oldUserPw = oldUserPw;
		this.teacher_phone = teacher_phone;
		this.teacher_photo = teacher_photo;
		this.teacher_thumb = teacher_thumb;
		this.teacher_registerDate = teacher_registerDate;
		this.teacher_status = teacher_status;
		this.file = file;
	}

	public String getOldUserPw() {
		return oldUserPw;
	}

	public void setOldUserPw(String oldUserPw) {
		this.oldUserPw = oldUserPw;
	}

	public String getTeacher_phone() {
		return teacher_phone;
	}

	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}

	public String getTeacher_photo() {
		return teacher_photo;
	}

	public void setTeacher_photo(String teacher_photo) {
		this.teacher_photo = teacher_photo;
	}

	public String getTeacher_thumb() {
		return teacher_thumb;
	}

	public void setTeacher_thumb(String teacher_thumb) {
		this.teacher_thumb = teacher_thumb;
	}

	public String getTeacher_registerDate() {
		return teacher_registerDate;
	}

	public void setTeacher_registerDate(String teacher_registerDate) {
		this.teacher_registerDate = teacher_registerDate;
	}

	public String getTeacher_status() {
		return teacher_status;
	}

	public void setTeacher_status(String teacher_status) {
		this.teacher_status = teacher_status;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
