package com.kidslab.admin.faq.vo;

import com.kidslab.common.vo.CommonVO;

public class FaqVO extends CommonVO {
	private int faq_no;
	private int admin_no;
	private String faq_title;
	private String faq_type;
	private String faq_content;

	public FaqVO() {
		super();
	}

	public FaqVO(int faq_no, int admin_no, String faq_title, String faq_type, String faq_content) {
		super();
		this.faq_no = faq_no;
		this.admin_no = admin_no;
		this.faq_title = faq_title;
		this.faq_type = faq_type;
		this.faq_content = faq_content;
	}

	public int getFaq_no() {
		return faq_no;
	}

	public void setFaq_no(int faq_no) {
		this.faq_no = faq_no;
	}

	public int getAdmin_no() {
		return admin_no;
	}

	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}

	public String getFaq_title() {
		return faq_title;
	}

	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}

	public String getFaq_type() {
		return faq_type;
	}

	public void setFaq_type(String faq_type) {
		this.faq_type = faq_type;
	}

	public String getFaq_content() {
		return faq_content;
	}

	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}

}
