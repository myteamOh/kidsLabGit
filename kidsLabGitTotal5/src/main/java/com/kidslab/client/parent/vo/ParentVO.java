package com.kidslab.client.parent.vo;

import com.kidslab.client.login.vo.UserLoginVO;

public class ParentVO extends UserLoginVO {

	private int parent_no; // 학부모 회원번호
	private String parent_oldPassword; // 학부모 전비밀번호
	private String parent_name; // 학부모 이름
	private String parent_phone; // 학부모 연락처
	private String parent_smsagree; // sms 동의
	private String parent_emailagree; // email 동의
	private String parent_kakaoagree; // 카카오톡 동의
	private String parent_knowroute; // 알게된 경로
	private String parent_address; // 주소
	private String parent_registerdate; // 가입 날짜
	private String parent_status; // 회원 상태

	public ParentVO() {
		super();
	}

	public ParentVO(int parent_no, String parent_oldPassword, String parent_name, String parent_phone,
			String parent_smsagree, String parent_emailagree, String parent_kakaoagree, String parent_knowroute,
			String parent_address, String parent_registerdate, String parent_status) {
		super();
		this.parent_no = parent_no;
		this.parent_oldPassword = parent_oldPassword;
		this.parent_name = parent_name;
		this.parent_phone = parent_phone;
		this.parent_smsagree = parent_smsagree;
		this.parent_emailagree = parent_emailagree;
		this.parent_kakaoagree = parent_kakaoagree;
		this.parent_knowroute = parent_knowroute;
		this.parent_address = parent_address;
		this.parent_registerdate = parent_registerdate;
		this.parent_status = parent_status;
	}

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}

	public String getParent_oldPassword() {
		return parent_oldPassword;
	}

	public void setParent_oldPassword(String parent_oldPassword) {
		this.parent_oldPassword = parent_oldPassword;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getParent_phone() {
		return parent_phone;
	}

	public void setParent_phone(String parent_phone) {
		this.parent_phone = parent_phone;
	}

	public String getParent_smsagree() {
		return parent_smsagree;
	}

	public void setParent_smsagree(String parent_smsagree) {
		this.parent_smsagree = parent_smsagree;
	}

	public String getParent_emailagree() {
		return parent_emailagree;
	}

	public void setParent_emailagree(String parent_emailagree) {
		this.parent_emailagree = parent_emailagree;
	}

	public String getParent_kakaoagree() {
		return parent_kakaoagree;
	}

	public void setParent_kakaoagree(String parent_kakaoagree) {
		this.parent_kakaoagree = parent_kakaoagree;
	}

	public String getParent_knowroute() {
		return parent_knowroute;
	}

	public void setParent_knowroute(String parent_knowroute) {
		this.parent_knowroute = parent_knowroute;
	}

	public String getParent_address() {
		return parent_address;
	}

	public void setParent_address(String parent_address) {
		this.parent_address = parent_address;
	}

	public String getParent_registerdate() {
		return parent_registerdate;
	}

	public void setParent_registerdate(String parent_registerdate) {
		this.parent_registerdate = parent_registerdate;
	}

	public String getParent_status() {
		return parent_status;
	}

	public void setParent_status(String parent_status) {
		this.parent_status = parent_status;
	}

	@Override
	public String toString() {
		return "ParentVO [parent_no=" + parent_no + ", parent_oldPassword=" + parent_oldPassword + ", parent_name="
				+ parent_name + ", parent_phone=" + parent_phone + ", parent_smsagree=" + parent_smsagree
				+ ", parent_emailagree=" + parent_emailagree + ", parent_kakaoagree=" + parent_kakaoagree
				+ ", parent_knowroute=" + parent_knowroute + ", parent_address=" + parent_address
				+ ", parent_registerdate=" + parent_registerdate + ", parent_status=" + parent_status + "]";
	}

}
