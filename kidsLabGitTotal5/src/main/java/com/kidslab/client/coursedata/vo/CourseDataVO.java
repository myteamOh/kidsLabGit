package com.kidslab.client.coursedata.vo;

import com.kidslab.admin.course.vo.CourseVO;

public class CourseDataVO extends CourseVO {

	private int coursedata_no; // 강의관련 게시글번호
	private String coursedata_title; // 글제목
	private String coursedata_writer; // 작성자
	private String coursedata_content; // 내용
	private String coursedata_registerdate; // 작성일
	private String coursedata_file; // 파일명
	private String coursedata_status; // 작성타입
	private int course_no; // 강의번호

	public CourseDataVO() {
		super();
	}

	public CourseDataVO(int coursedata_no, String coursedata_title, String coursedata_writer, String coursedata_content,
			String coursedata_registerdate, String coursedata_file, String coursedata_status, int course_no) {
		super();
		this.coursedata_no = coursedata_no;
		this.coursedata_title = coursedata_title;
		this.coursedata_writer = coursedata_writer;
		this.coursedata_content = coursedata_content;
		this.coursedata_registerdate = coursedata_registerdate;
		this.coursedata_file = coursedata_file;
		this.coursedata_status = coursedata_status;
		this.course_no = course_no;
	}

	public int getCoursedata_no() {
		return coursedata_no;
	}

	public void setCoursedata_no(int coursedata_no) {
		this.coursedata_no = coursedata_no;
	}

	public String getCoursedata_title() {
		return coursedata_title;
	}

	public void setCoursedata_title(String coursedata_title) {
		this.coursedata_title = coursedata_title;
	}

	public String getCoursedata_writer() {
		return coursedata_writer;
	}

	public void setCoursedata_writer(String coursedata_writer) {
		this.coursedata_writer = coursedata_writer;
	}

	public String getCoursedata_content() {
		return coursedata_content;
	}

	public void setCoursedata_content(String coursedata_content) {
		this.coursedata_content = coursedata_content;
	}

	public String getCoursedata_registerdate() {
		return coursedata_registerdate;
	}

	public void setCoursedata_registerdate(String coursedata_registerdate) {
		this.coursedata_registerdate = coursedata_registerdate;
	}

	public String getCoursedata_file() {
		return coursedata_file;
	}

	public void setCoursedata_file(String coursedata_file) {
		this.coursedata_file = coursedata_file;
	}

	public String getCoursedata_status() {
		return coursedata_status;
	}

	public void setCoursedata_status(String coursedata_status) {
		this.coursedata_status = coursedata_status;
	}

	public int getCourse_no() {
		return course_no;
	}

	public void setCourse_no(int course_no) {
		this.course_no = course_no;
	}

	@Override
	public String toString() {
		return "CourseDataVO [coursedata_no=" + coursedata_no + ", coursedata_title=" + coursedata_title
				+ ", coursedata_writer=" + coursedata_writer + ", coursedata_content=" + coursedata_content
				+ ", coursedata_registerdate=" + coursedata_registerdate + ", coursedata_file=" + coursedata_file
				+ ", coursedata_status=" + coursedata_status + ", course_no=" + course_no + "]";
	}

}
