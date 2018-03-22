package com.kidslab.client.inquiry.service;

import java.util.List;

import com.kidslab.admin.reply.vo.ReplyVO;
import com.kidslab.client.inquiry.vo.InquiryVO;

public interface InquiryService {
	// 1:1 문의 목록 구현
	public List<InquiryVO> inquiryList(InquiryVO inquiryVO) throws Exception;

	// 페이징 추가
	/* public int inquiryListCnt(InquiryVO inquiryVO); */

	// 1:1 문의 게시글 작성
	public int inquiryInsert(InquiryVO inquiryVO) throws Exception;

	// 1:1 문의 상세보기
	public InquiryVO inquiryDetail(int inquiry_no) throws Exception;

	// 1:1 문의 수정
	public void inquiryUpdate(InquiryVO inquiryVO) throws Exception;

	// 1:1 문의 삭제
	public void inquiryDelete(int inquiry_no) throws Exception;

	/***************************************
	 * 관리자
	 *******************************************/
	// 1:1 문의 목록
	public List<InquiryVO> adminInquiryList(InquiryVO inquiryVO);

	// 답글 작성 시 제목에 [답변완료] 추가
	public int replySuccessUpdate(InquiryVO ivo);

}
