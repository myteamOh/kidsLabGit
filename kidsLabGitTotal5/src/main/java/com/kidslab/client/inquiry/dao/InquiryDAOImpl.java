package com.kidslab.client.inquiry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.reply.vo.ReplyVO;
import com.kidslab.client.inquiry.vo.InquiryVO;

@Repository("inquiry")
public class InquiryDAOImpl implements InquiryDAO {

	@Autowired
	private SqlSession session;

	// 1:1 문의 목록 구현
	@Override
	public List<InquiryVO> inquiryList(InquiryVO inquiryVO) throws Exception {

		return session.selectList("inquiry.inquiryList");
	}

	// 전체 레코드 건수 구현
	/*
	 * @Override public int inquiryListCnt(InquiryVO inquiryVO) { return
	 * (Integer)session.selectOne("inquiryListCnt"); }
	 */

	// 1:1 문의 등록
	@Override
	public int inquiryInsert(InquiryVO inquiryVO) throws Exception {
		return session.insert("inquiryInsert", inquiryVO);
	}

	// 1:1 문의 상세보기
	@Override
	public InquiryVO inquiryDetail(int inquiry_no) throws Exception {
		return session.selectOne("inquiry.inquiryDetail", inquiry_no);
	}

	// 1:1 문의 수정
	@Override
	public void inquiryUpdate(InquiryVO inquiryVO) throws Exception {
		session.update("inquiry.inquiryUpdate", inquiryVO);
	}

	// 1:1 문의 삭제
	@Override
	public void inquiryDelete(int inquiry_no) throws Exception {
		session.delete("inquiry.inquiryDelete", inquiry_no);
	}

	@Override
	public List<InquiryVO> adminInquiryList(InquiryVO inquiryVO) {
		// TODO Auto-generated method stub
		return session.selectList("noticeInquiryList", inquiryVO);
	}

	@Override
	public int replySuccessUpdate(InquiryVO ivo) {
		// TODO Auto-generated method stub
		return session.update("replySuccessUpdate", ivo);
	}

}
