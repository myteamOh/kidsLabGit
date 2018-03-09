package com.kidslab.admin.course.vo;

import org.springframework.web.multipart.MultipartFile;

import com.kidslab.common.vo.CommonVO;

public class CourseVO extends CommonVO {
	private int course_no = 0; // 강의번호
	private int teacher_no; // 강사번호
	private String course_summary; // 강의개요
	private String course_name; // 강의명
	private String course_subject; // 강의과목
	private String course_level; // 강의대상
	private String course_time; // 강의시간
	private int course_totalperson; // 강의정원
	private int course_room; // 강의실
	private int course_pay; // 강의료
	private String course_registerdate; // 강의등록일
	private String course_status; // 강의상태
	private String course_startdate; // 강의시작날짜
	private String course_enddate; // 강의종료날짜
	private String teacher_name; // 강사명

	// 파일 업로드를 위한 속성
	private String course_plan; // 실제서버에 저장한 파일명
	private MultipartFile file; // 첨부파일

	public CourseVO() {
		super();
	}

	public CourseVO(int course_no, int teacher_no, String course_summary, String course_name, String course_subject,
			String course_level, String course_time, int course_totalperson, int course_room, int course_pay,
			String course_registerdate, String course_status, String course_startdate, String course_enddate,
			String course_plan) {
		super();
		this.course_no = course_no;
		this.teacher_no = teacher_no;
		this.course_summary = course_summary;
		this.course_name = course_name;
		this.course_subject = course_subject;
		this.course_level = course_level;
		this.course_time = course_time;
		this.course_totalperson = course_totalperson;
		this.course_room = course_room;
		this.course_pay = course_pay;
		this.course_registerdate = course_registerdate;
		this.course_status = course_status;
		this.course_startdate = course_startdate;
		this.course_enddate = course_enddate;
		this.course_plan = course_plan;
	}

	public CourseVO(int course_no, int teacher_no, String course_summary, String course_name, String course_subject,
			String course_level, String course_time, int course_totalperson, int course_room, int course_pay,
			String course_registerdate, String course_status, String course_startdate, String course_enddate,
			String teacher_name, String course_plan, MultipartFile file) {
		super();
		this.course_no = course_no;
		this.teacher_no = teacher_no;
		this.course_summary = course_summary;
		this.course_name = course_name;
		this.course_subject = course_subject;
		this.course_level = course_level;
		this.course_time = course_time;
		this.course_totalperson = course_totalperson;
		this.course_room = course_room;
		this.course_pay = course_pay;
		this.course_registerdate = course_registerdate;
		this.course_status = course_status;
		this.course_startdate = course_startdate;
		this.course_enddate = course_enddate;
		this.teacher_name = teacher_name;
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

	public String getCourse_time() {
		return course_time;
	}

	public void setCourse_time(String course_time) {
		this.course_time = course_time;
	}

	public int getCourse_totalperson() {
		return course_totalperson;
	}

	public void setCourse_totalperson(int course_totalperson) {
		this.course_totalperson = course_totalperson;
	}

	public int getCourse_room() {
		return course_room;
	}

	public void setCourse_room(int course_room) {
		this.course_room = course_room;
	}

	public int getCourse_pay() {
		return course_pay;
	}

	public void setCourse_pay(int course_pay) {
		this.course_pay = course_pay;
	}

	public String getCourse_registerdate() {
		return course_registerdate;
	}

	public void setCourse_registerdate(String course_registerdate) {
		this.course_registerdate = course_registerdate;
	}

	public String getCourse_status() {
		return course_status;
	}

	public void setCourse_status(String course_status) {
		this.course_status = course_status;
	}

	public String getCourse_startdate() {
		return course_startdate;
	}

	public void setCourse_startdate(String course_startdate) {
		this.course_startdate = course_startdate;
	}

	public String getCourse_enddate() {
		return course_enddate;
	}

	public void setCourse_enddate(String course_enddate) {
		this.course_enddate = course_enddate;
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

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	@Override
	public String toString() {
		return "CourseVO [course_no=" + course_no + ", teacher_no=" + teacher_no + ", course_summary=" + course_summary
				+ ", course_name=" + course_name + ", course_subject=" + course_subject + ", course_level="
				+ course_level + ", course_time=" + course_time + ", course_totalperson=" + course_totalperson
				+ ", course_room=" + course_room + ", course_pay=" + course_pay + ", course_registerdate="
				+ course_registerdate + ", course_status=" + course_status + ", course_startdate=" + course_startdate
				+ ", course_enddate=" + course_enddate + ", teacher_name=" + teacher_name + ", course_plan="
				+ course_plan + ", file=" + file + "]";
	}

}
