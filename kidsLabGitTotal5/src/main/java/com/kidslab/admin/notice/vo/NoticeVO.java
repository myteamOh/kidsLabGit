package com.kidslab.admin.notice.vo;

import com.kidslab.common.vo.CommonVO;

public class NoticeVO extends CommonVO {

	private int notice_no;
	private int admin_no;
	private String notice_title;
	private String notice_content;
	private String notice_registerdate;

	public NoticeVO() {
		super();
	}

	public NoticeVO(int notice_no, int admin_no, String notice_title, String notice_content,
			String notice_registerdate) {
		super();
		this.notice_no = notice_no;
		this.admin_no = admin_no;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_registerdate = notice_registerdate;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public int getAdmin_no() {
		return admin_no;
	}

	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getNotice_registerdate() {
		return notice_registerdate;
	}

	public void setNotice_registerdate(String notice_registerdate) {
		this.notice_registerdate = notice_registerdate;
	}

}
