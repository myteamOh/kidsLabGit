package com.kidslab.client.inquiry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.client.inquiry.vo.InquiryVO;


@Repository("inquiryDAO")
public class InquiryDAOImpl implements InquiryDAO {
	
	@Autowired
	private SqlSession session;
	
	
	// 1:1 문의 목록 구현
	@Override
	public List<InquiryVO> inquiryList() {
		return session.selectList("inquiryList");
	}
	
	// 전체 레코드 건수 구현
/*	@Override
	public int inquiryListCnt(InquiryVO inquiryVO) {
		return (Integer)session.selectOne("inquiryListCnt");
	}*/

	// 1:1 문의 등록
	@Override
	public int inquiryInsert(InquiryVO inquiryVO) {
		return (Integer)session.insert("inquiryInsert", inquiryVO);
	}
	
	// 1:1 문의 상세보기
	@Override
	public InquiryVO inquiryDetail(InquiryVO inquiryVO) {
		return (InquiryVO)session.selectOne("inquiryDetail", inquiryVO);
	}
	
	// 1:1���� ���� ����
	@Override
	public int inquiryUpdate(InquiryVO inquiryVO) {
		return (Integer)session.update("inquiryUpdate", inquiryVO);
	}
	
	// 1:1���� ���� ����
	@Override
	public int inquiryDelete(int inquiry_no) {
		return(Integer) session.delete("inquiryDelete", inquiry_no);
	}

}
