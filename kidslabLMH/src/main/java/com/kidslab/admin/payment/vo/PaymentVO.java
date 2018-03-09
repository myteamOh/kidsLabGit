package com.kidslab.admin.payment.vo;

import com.kidslab.common.vo.CommonVO;

public class PaymentVO extends CommonVO {
	private int student_no; // 학생번호
	private String student_name; // 학생 이름
	private int parent_no; // 학부모 번호
	private String parent_name; // 학부모 이름
	private int course_no; // 강의 번호
	private String course_name; // 강의 명
	private int requestCourse_no; // 수강신청 번호
	private String requestCourse_payMethod; // 결제방법
	private String requestCourse_payAmount; // 결제금액
	private String requestCourse_paymentDate; // 결제날짜
	private String requestCourse_paymentStatus; // 결제상태
	private String requestCourse_accountHolder; // 예금주
	private String requestCourse_accountNumber; // 환불계좌번호
	private String requestCourse_refundCharge; // 환불금액
	private String requestCourse_refundBank; // 환불계좌은행

	public PaymentVO() {
		super();
	}

	public PaymentVO(int student_no, String student_name, int parent_no, String parent_name, int course_no,
			String course_name, int requestCourse_no, String requestCourse_payMethod, String requestCourse_payAmount,
			String requestCourse_paymentDate, String requestCourse_paymentStatus, String requestCourse_accountHolder,
			String requestCourse_accountNumber, String requestCourse_refundCharge, String requestCourse_refundBank) {
		super();
		this.student_no = student_no;
		this.student_name = student_name;
		this.parent_no = parent_no;
		this.parent_name = parent_name;
		this.course_no = course_no;
		this.course_name = course_name;
		this.requestCourse_no = requestCourse_no;
		this.requestCourse_payMethod = requestCourse_payMethod;
		this.requestCourse_payAmount = requestCourse_payAmount;
		this.requestCourse_paymentDate = requestCourse_paymentDate;
		this.requestCourse_paymentStatus = requestCourse_paymentStatus;
		this.requestCourse_accountHolder = requestCourse_accountHolder;
		this.requestCourse_accountNumber = requestCourse_accountNumber;
		this.requestCourse_refundCharge = requestCourse_refundCharge;
		this.requestCourse_refundBank = requestCourse_refundBank;
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

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public int getCourse_no() {
		return course_no;
	}

	public void setCourse_no(int course_no) {
		this.course_no = course_no;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getRequestCourse_no() {
		return requestCourse_no;
	}

	public void setRequestCourse_no(int requestCourse_no) {
		this.requestCourse_no = requestCourse_no;
	}

	public String getRequestCourse_payMethod() {
		return requestCourse_payMethod;
	}

	public void setRequestCourse_payMethod(String requestCourse_payMethod) {
		this.requestCourse_payMethod = requestCourse_payMethod;
	}

	public String getRequestCourse_payAmount() {
		return requestCourse_payAmount;
	}

	public void setRequestCourse_payAmount(String requestCourse_payAmount) {
		this.requestCourse_payAmount = requestCourse_payAmount;
	}

	public String getRequestCourse_paymentDate() {
		return requestCourse_paymentDate;
	}

	public void setRequestCourse_paymentDate(String requestCourse_paymentDate) {
		this.requestCourse_paymentDate = requestCourse_paymentDate;
	}

	public String getRequestCourse_paymentStatus() {
		return requestCourse_paymentStatus;
	}

	public void setRequestCourse_paymentStatus(String requestCourse_paymentStatus) {
		this.requestCourse_paymentStatus = requestCourse_paymentStatus;
	}

	public String getRequestCourse_accountHolder() {
		return requestCourse_accountHolder;
	}

	public void setRequestCourse_accountHolder(String requestCourse_accountHolder) {
		this.requestCourse_accountHolder = requestCourse_accountHolder;
	}

	public String getRequestCourse_accountNumber() {
		return requestCourse_accountNumber;
	}

	public void setRequestCourse_accountNumber(String requestCourse_accountNumber) {
		this.requestCourse_accountNumber = requestCourse_accountNumber;
	}

	public String getRequestCourse_refundCharge() {
		return requestCourse_refundCharge;
	}

	public void setRequestCourse_refundCharge(String requestCourse_refundCharge) {
		this.requestCourse_refundCharge = requestCourse_refundCharge;
	}

	public String getRequestCourse_refundBank() {
		return requestCourse_refundBank;
	}

	public void setRequestCourse_refundBank(String requestCourse_refundBank) {
		this.requestCourse_refundBank = requestCourse_refundBank;
	}

	@Override
	public String toString() {
		return "PaymentVO [student_no=" + student_no + ", student_name=" + student_name + ", parent_no=" + parent_no
				+ ", parent_name=" + parent_name + ", course_no=" + course_no + ", course_name=" + course_name
				+ ", requestCourse_payMethod=" + requestCourse_payMethod + ", requestCourse_payAmount="
				+ requestCourse_payAmount + ", requestCourse_paymentDate=" + requestCourse_paymentDate
				+ ", requestCourse_paymentStatus=" + requestCourse_paymentStatus + ", requestCourse_accountHolder="
				+ requestCourse_accountHolder + ", requestCourse_accountNumber=" + requestCourse_accountNumber
				+ ", requestCourse_refundCharge=" + requestCourse_refundCharge + ", requestCourse_refundBank="
				+ requestCourse_refundBank + "]";
	}

}
