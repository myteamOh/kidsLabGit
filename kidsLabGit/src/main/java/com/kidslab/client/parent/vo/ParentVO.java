package com.kidslab.client.parent.vo;

public class ParentVO {

	private int parent_no; // 학부모 회원번호
	private String parent_id; // 학부모 아이디
	private String parent_password; // 학부모 비밀번호
	private String parent_name; // 학부모 이름
	private int parent_phone; // 학부모 연락처
	private String sms_agree; // sms 동의
	private String email_agree; // email 동의
	private String kakao_agree; // 카카오톡 동의
	private String know_route; // 알게된 경로
	private String address; // 주소

	public ParentVO() {
		super();
	}

	public ParentVO(String parent_name, int parent_phone, String address, String kakao_agree, String sms_agree,
			String email_agree) {
		super();
		this.parent_name = parent_name;
		this.parent_phone = parent_phone;
		this.address = address;
		this.kakao_agree = kakao_agree;
		this.sms_agree = sms_agree;
		this.email_agree = email_agree;
	}

	public ParentVO(int parent_no, String parent_id, String parent_password, String parent_name, int parent_phone,
			String address, String kakao_agree, String sms_agree, String email_agree, String know_route) {
		super();
		this.parent_no = parent_no;
		this.parent_id = parent_id;
		this.parent_password = parent_password;
		this.parent_name = parent_name;
		this.parent_phone = parent_phone;
		this.address = address;
		this.kakao_agree = kakao_agree;
		this.sms_agree = sms_agree;
		this.email_agree = email_agree;
		this.know_route = know_route;
	}

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getParent_password() {
		return parent_password;
	}

	public void setParent_password(String parent_password) {
		this.parent_password = parent_password;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public int getParent_phone() {
		return parent_phone;
	}

	public void setParent_phone(int parent_phone) {
		this.parent_phone = parent_phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getKakao_agree() {
		return kakao_agree;
	}

	public void setKakao_agree(String kakao_agree) {
		this.kakao_agree = kakao_agree;
	}

	public String getSms_agree() {
		return sms_agree;
	}

	public void setSms_agree(String sms_agree) {
		this.sms_agree = sms_agree;
	}

	public String getEmail_agree() {
		return email_agree;
	}

	public void setEmail_agree(String email_agree) {
		this.email_agree = email_agree;
	}

	public String getKnow_route() {
		return know_route;
	}

	public void setKnow_route(String know_route) {
		this.know_route = know_route;
	}

	@Override
	public String toString() {
		return "ParentVO [parent_no=" + parent_no + ", parent_id=" + parent_id + ", parent_password=" + parent_password
				+ ", parent_name=" + parent_name + ", parent_phone=" + parent_phone + ", address=" + address
				+ ", kakao_agree=" + kakao_agree + ", sms_agree=" + sms_agree + ", email_agree=" + email_agree
				+ ", know_route=" + know_route + "]";
	}

}
