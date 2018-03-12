package com.kidslab.admin.faq.dao;

import java.util.List;

import com.kidslab.admin.faq.vo.FaqVO;

public interface FaqDao {
	// 전체 FAQ 목록 구현
	public List<FaqVO> faqList(FaqVO fvo);

	// 페이징 추가
	public int faqListCnt(FaqVO fvo);

	// FAQ 등록
	public int faqInsert(FaqVO fvo);

	// FAQ 상세보기
	public FaqVO faqDetail(FaqVO fvo);

	// FAQ 수정
	public int faqUpdate(FaqVO fvo);

	// FAQ 삭제
	public int faqDelete(int faq_no);
}
