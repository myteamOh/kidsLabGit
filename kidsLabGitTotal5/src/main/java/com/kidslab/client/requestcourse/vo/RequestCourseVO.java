package com.kidslab.client.requestcourse.vo;

import com.kidslab.admin.course.vo.CourseVO;

public class RequestCourseVO extends CourseVO {

	private int requestcourse_no; // 강의신청 번호
	private String requestcourse_paymethod; // 결제방법
	private int requestcourse_payamount; // 결제금액
	private String requestcourse_paymentdate; // 결제일
	private String requestcourse_paymentstatus; // 결제상태
	private String requestcourse_accountholder; // 예금주
	private String requestcourse_accountnumber; // 계좌번호
	private int requestcourse_refundcharge; // 환불금액
	private String requestcourse_refundbank; // 환불은행
	private int parent_no; // 학부모 번호
	private int student_no; // 학생 번호
	private int course_no; // 강의 번호

	private String student_name; // 학생 이름

	private String bank_and_account; // 선택은행 및 계좌번호

	public RequestCourseVO() {
		super();
	}

	public RequestCourseVO(int requestcourse_no, String requestcourse_paymethod, int requestcourse_payamount,
			String requestcourse_paymentdate, String requestcourse_paymentstatus, String requestcourse_accountholder,
			String requestcourse_accountnumber, int requestcourse_refundcharge, String requestcourse_refundbank,
			int parent_no, int student_no, int course_no, String student_name, String bank_and_account) {
		super();
		this.requestcourse_no = requestcourse_no;
		this.requestcourse_paymethod = requestcourse_paymethod;
		this.requestcourse_payamount = requestcourse_payamount;
		this.requestcourse_paymentdate = requestcourse_paymentdate;
		this.requestcourse_paymentstatus = requestcourse_paymentstatus;
		this.requestcourse_accountholder = requestcourse_accountholder;
		this.requestcourse_accountnumber = requestcourse_accountnumber;
		this.requestcourse_refundcharge = requestcourse_refundcharge;
		this.requestcourse_refundbank = requestcourse_refundbank;
		this.parent_no = parent_no;
		this.student_no = student_no;
		this.course_no = course_no;
		this.student_name = student_name;
		this.bank_and_account = bank_and_account;
	}

	public int getRequestcourse_no() {
		return requestcourse_no;
	}

	public void setRequestcourse_no(int requestcourse_no) {
		this.requestcourse_no = requestcourse_no;
	}

	public String getRequestcourse_paymethod() {
		return requestcourse_paymethod;
	}

	public void setRequestcourse_paymethod(String requestcourse_paymethod) {
		this.requestcourse_paymethod = requestcourse_paymethod;
	}

	public int getRequestcourse_payamount() {
		return requestcourse_payamount;
	}

	public void setRequestcourse_payamount(int requestcourse_payamount) {
		this.requestcourse_payamount = requestcourse_payamount;
	}

	public String getRequestcourse_paymentdate() {
		return requestcourse_paymentdate;
	}

	public void setRequestcourse_paymentdate(String requestcourse_paymentdate) {
		this.requestcourse_paymentdate = requestcourse_paymentdate;
	}

	public String getRequestcourse_paymentstatus() {
		return requestcourse_paymentstatus;
	}

	public void setRequestcourse_paymentstatus(String requestcourse_paymentstatus) {
		this.requestcourse_paymentstatus = requestcourse_paymentstatus;
	}

	public String getRequestcourse_accountholder() {
		return requestcourse_accountholder;
	}

	public void setRequestcourse_accountholder(String requestcourse_accountholder) {
		this.requestcourse_accountholder = requestcourse_accountholder;
	}

	public String getRequestcourse_accountnumber() {
		return requestcourse_accountnumber;
	}

	public void setRequestcourse_accountnumber(String requestcourse_accountnumber) {
		this.requestcourse_accountnumber = requestcourse_accountnumber;
	}

	public int getRequestcourse_refundcharge() {
		return requestcourse_refundcharge;
	}

	public void setRequestcourse_refundcharge(int requestcourse_refundcharge) {
		this.requestcourse_refundcharge = requestcourse_refundcharge;
	}

	public String getRequestcourse_refundbank() {
		return requestcourse_refundbank;
	}

	public void setRequestcourse_refundbank(String requestcourse_refundbank) {
		this.requestcourse_refundbank = requestcourse_refundbank;
	}

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}

	public int getStudent_no() {
		return student_no;
	}

	public void setStudent_no(int student_no) {
		this.student_no = student_no;
	}

	public int getCourse_no() {
		return course_no;
	}

	public void setCourse_no(int course_no) {
		this.course_no = course_no;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getBank_and_account() {
		return bank_and_account;
	}

	public void setBank_and_account(String bank_and_account) {
		this.bank_and_account = bank_and_account;
	}

	@Override
	public String toString() {
		return "RequestCourseVO [requestcourse_no=" + requestcourse_no + ", requestcourse_paymethod="
				+ requestcourse_paymethod + ", requestcourse_payamount=" + requestcourse_payamount
				+ ", requestcourse_paymentdate=" + requestcourse_paymentdate + ", requestcourse_paymentstatus="
				+ requestcourse_paymentstatus + ", requestcourse_accountholder=" + requestcourse_accountholder
				+ ", requestcourse_accountnumber=" + requestcourse_accountnumber + ", requestcourse_refundcharge="
				+ requestcourse_refundcharge + ", requestcourse_refundbank=" + requestcourse_refundbank + ", parent_no="
				+ parent_no + ", student_no=" + student_no + ", course_no=" + course_no + ", student_name="
				+ student_name + "]";
	}

}
