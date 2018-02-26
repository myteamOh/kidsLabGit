package com.kidslab.teacher.registcourse.vo;

import org.springframework.web.multipart.MultipartFile;

public class RegistCourseVO {
	private int course_no;
	private int teacher_no;
	private String course_summary;
	private String course_name;
	private String course_subject;
	private String course_level;

	// 파일 업로드를 위한 속성
	private String course_plan; // 실제서버에 저장한 파일명
	private MultipartFile file; // 첨부 파일

	public RegistCourseVO() {
		super();
	}

	public RegistCourseVO(int course_no, int teacher_no, String course_summary, String course_name,
			String course_subject, String course_level, String course_plan, MultipartFile file) {
		super();
		this.course_no = course_no;
		this.teacher_no = teacher_no;
		this.course_summary = course_summary;
		this.course_name = course_name;
		this.course_subject = course_subject;
		this.course_level = course_level;
		this.course_plan = course_plan;
		this.file = file;
	}

	public int getCourse_no() {
		return course_no;
	}

	public void setCourse_no(int course_no) {
		this.course_no = course_no;
	}

	public int getTeacher_no() {
		return teacher_no;
	}

	public void setTeacher_no(int teacher_no) {
		this.teacher_no = teacher_no;
	}

	public String getCourse_summary() {
		return course_summary;
	}

	public void setCourse_summary(String course_summary) {
		this.course_summary = course_summary;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_subject() {
		return course_subject;
	}

	public void setCourse_subject(String course_subject) {
		this.course_subject = course_subject;
	}

	public String getCourse_level() {
		return course_level;
	}

	public void setCourse_level(String course_level) {
		this.course_level = course_level;
	}

	public String getCourse_plan() {
		return course_plan;
	}

	public void setCourse_plan(String course_plan) {
		this.course_plan = course_plan;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
