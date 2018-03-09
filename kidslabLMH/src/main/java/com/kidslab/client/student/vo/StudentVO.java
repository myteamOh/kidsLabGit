package com.kidslab.client.student.vo;

import com.kidslab.client.login.vo.UserLoginVO;

public class StudentVO extends UserLoginVO {

	private int student_no; // 학생 번호
	private String student_name; // 학생 이름
	private String student_gender; // 학생 성별
	private String student_school; // 학생 학교
	private String student_birthday; // 학생 생일
	private String student_reference; // 학생 참고사항
	private String student_evaluation; // 학생 평가
	private String student_registerdate;// 학생 가입일
	private String student_status;// 학생 계정 상태
	private int parent_no; // 학부모 번호

	public StudentVO() {
		super();
	}

	public StudentVO(int student_no, String student_name, String student_gender, String student_school,
			String student_birthday, String student_reference, String student_evaluation, String student_registerdate,
			String student_status, int parent_no) {
		super();
		this.student_no = student_no;
		this.student_name = student_name;
		this.student_gender = student_gender;
		this.student_school = student_school;
		this.student_birthday = student_birthday;
		this.student_reference = student_reference;
		this.student_evaluation = student_evaluation;
		this.student_registerdate = student_registerdate;
		this.student_status = student_status;
		this.parent_no = parent_no;
	}

	public int getStudent_no() {
		return student_no;
	}

	public void setStudent_no(int student_no) {
		this.student_no = student_no;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_gender() {
		return student_gender;
	}

	public void setStudent_gender(String student_gender) {
		this.student_gender = student_gender;
	}

	public String getStudent_school() {
		return student_school;
	}

	public void setStudent_school(String student_school) {
		this.student_school = student_school;
	}

	public String getStudent_birthday() {
		return student_birthday;
	}

	public void setStudent_birthday(String student_birthday) {
		this.student_birthday = student_birthday;
	}

	public String getStudent_reference() {
		return student_reference;
	}

	public void setStudent_reference(String student_reference) {
		this.student_reference = student_reference;
	}

	public String getStudent_evaluation() {
		return student_evaluation;
	}

	public void setStudent_evaluation(String student_evaluation) {
		this.student_evaluation = student_evaluation;
	}

	public String getStudent_registerdate() {
		return student_registerdate;
	}

	public void setStudent_registerdate(String student_registerdate) {
		this.student_registerdate = student_registerdate;
	}

	public String getStudent_status() {
		return student_status;
	}

	public void setStudent_status(String student_status) {
		this.student_status = student_status;
	}

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}

	@Override
	public String toString() {
		return "StudentVO [student_no=" + student_no + ", student_name=" + student_name + ", student_gender="
				+ student_gender + ", student_school=" + student_school + ", student_birthday=" + student_birthday
				+ ", student_reference=" + student_reference + ", student_evaluation=" + student_evaluation
				+ ", student_registerdate=" + student_registerdate + ", student_status=" + student_status
				+ ", parent_no=" + parent_no + "]";
	}

}
