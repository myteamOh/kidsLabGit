package com.kidslab.client.inquiry.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.client.inquiry.dao.InquiryDAO;
import com.kidslab.client.inquiry.vo.InquiryVO;


@Service
@Transactional
public class InquiryServiceImpl implements InquiryService {
	Logger logger = Logger.getLogger(InquiryServiceImpl.class);
	
	@Autowired
	private InquiryDAO inquiryDAO;
	
	
	// 1:1 문의 목록 구현
	@Override
	public List<InquiryVO> inquiryList(InquiryVO inquiryVO) {
		List<InquiryVO> myInquiryList = null;
		
		// 정렬에 대한 기본값 설정 보류
		myInquiryList = inquiryDAO.inquiryList();
		
		return myInquiryList;
	}
	
	
	//전체 레코드 수 구현
/*	@Override
	public int inquiryListCnt(InquiryVO inquiryVO) {
		// TODO Auto-generated method stub
		return inquiryDAO.inquiryListCnt(inquiryVO);
	}*/
	
	
	
	// 1:1 문의 등록
	@Override
	public int inquiryInsert(InquiryVO inquiryVO) {
		int result = 0;
		try {
			result = inquiryDAO.inquiryInsert(inquiryVO);
		} catch (Exception e) {
			e.printStackTrace();
			result=0;
		}
		return result;
	}
	
	
	// 1:1 문의 상세보기
	@Override
	public InquiryVO inquiryDetail(InquiryVO inquiryVO) {
		InquiryVO detail = null;
		detail = inquiryDAO.inquiryDetail(inquiryVO);
		return detail;
	}
	
	
	// 1:1 문의 수정
	@Override
	public int inquiryUpdate(InquiryVO inquiryVO) {
		int result = 0;
		try {
			result = inquiryDAO.inquiryUpdate(inquiryVO);
		} catch (Exception e) {
			e.printStackTrace();
			result=0;
		}
		return result;
	}
	
	// 1:1 문의 삭제
	@Override
	public int inquiryDelete(int inquiry_no) {
		int result = 0;
		try {
			result = inquiryDAO.inquiryDelete(inquiry_no);
		} catch (Exception e) {
			e.printStackTrace();
			result=0;
		}
		return result;
	}




}
