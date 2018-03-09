package com.kidslab.client.inquiry.dao;

import java.util.List;

import com.kidslab.client.inquiry.vo.InquiryVO;

public interface InquiryDAO {
	// 1:1 문의 목록 구현
	public List<InquiryVO> inquiryList();
	
	// 페이징 추가
/*	public int inquiryListCnt(InquiryVO inquiryVO);*/
	
	// 1:1 문의 등록
	public int inquiryInsert(InquiryVO inquiryVO);
	
	// 1:1 문의 상세보기 
	public InquiryVO inquiryDetail(InquiryVO inquiryVO);
	
	// 1:1 문의 수정
	public int inquiryUpdate(InquiryVO inquiryVO);
	
	// 1:1 문의 삭제
	public int inquiryDelete(int inquiry_no);
}
