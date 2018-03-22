package com.kidslab.admin.reply.vo;

public class ReplyVO {
	private int inquiry_reply_no; // 1:1문의 답글번호
	private String inquiry_reply_content; // 1:1문의 답글 내용
	private String inquiry_reply_registerdate; // 1:1 문의 작성 날짜
	private int inquiry_no; // 1:1 문의 번호
	private int admin_no; // 관리자 번호

	public ReplyVO() {
		super();
	}

	public ReplyVO(int inquiry_reply_no, String inquiry_reply_content, String inquiry_reply_registerdate,
			int inquiry_no, int admin_no) {
		super();
		this.inquiry_reply_no = inquiry_reply_no;
		this.inquiry_reply_content = inquiry_reply_content;
		this.inquiry_reply_registerdate = inquiry_reply_registerdate;
		this.inquiry_no = inquiry_no;
		this.admin_no = admin_no;
	}

	public int getInquiry_reply_no() {
		return inquiry_reply_no;
	}

	public void setInquiry_reply_no(int inquiry_reply_no) {
		this.inquiry_reply_no = inquiry_reply_no;
	}

	public String getInquiry_reply_content() {
		return inquiry_reply_content;
	}

	public void setInquiry_reply_content(String inquiry_reply_content) {
		this.inquiry_reply_content = inquiry_reply_content;
	}

	public String getInquiry_reply_registerdate() {
		return inquiry_reply_registerdate;
	}

	public void setInquiry_reply_registerdate(String inquiry_reply_registerdate) {
		this.inquiry_reply_registerdate = inquiry_reply_registerdate;
	}

	public int getInquiry_no() {
		return inquiry_no;
	}

	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}

	public int getAdmin_no() {
		return admin_no;
	}

	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}

}
