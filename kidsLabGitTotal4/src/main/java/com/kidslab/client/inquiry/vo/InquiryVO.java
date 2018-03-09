package com.kidslab.client.inquiry.vo;

import com.kidslab.common.vo.CommonVO;

public class InquiryVO extends CommonVO {

	
	private int inquiry_no;	//1:1���� �۹�ȣ 
	private String inquiry_title;	//1:1���� ����
	private String inquiry_content;	//1:1���� ����
	private String inquiry_registerdate; //1:1���� �����
	private int parent_no; //�� �ۼ����� �θ�ȸ�� ��ȣ
	
	public int getInquiry_no() {
		return inquiry_no;
	}
	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}
	public String getInquiry_title() {
		return inquiry_title;
	}
	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}
	public String getInquiry_content() {
		return inquiry_content;
	}
	public void setInquiry_content(String inquiry_content) {
		this.inquiry_content = inquiry_content;
	}
	public String getInquiry_registerdate() {
		return inquiry_registerdate;
	}
	public void setInquiry_registerdate(String inquiry_registerdate) {
		this.inquiry_registerdate = inquiry_registerdate;
	}
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	
	
	
	
	
	
}
