package com.kidslab.admin.faq.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.faq.dao.FaqDao;
import com.kidslab.admin.faq.vo.FaqVO;

@Service
@Transactional
public class FaqServiceImpl implements FaqService {

	Logger logger = Logger.getLogger(FaqServiceImpl.class);

	@Autowired
	private FaqDao faqDao;

	// Faq 목록
	@Override
	public List<FaqVO> faqList(FaqVO fvo) {
		// TODO Auto-generated method stub

		List<FaqVO> faqList = null;

		faqList = faqDao.faqList(fvo);
		return faqList;
	}

	// 전체 레코드 수
	@Override
	public int faqListCnt(FaqVO fvo) {
		// TODO Auto-generated method stub
		return faqDao.faqListCnt(fvo);
	}

	// faq 작성
	@Override
	public int faqInsert(FaqVO fvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = faqDao.faqInsert(fvo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// faq 상세보기
	@Override
	public FaqVO faqDetail(FaqVO fvo) {
		// TODO Auto-generated method stub
		FaqVO detail = null;
		detail = faqDao.faqDetail(fvo);
		return detail;
	}

	// faq 수정
	@Override
	public int faqUpdate(FaqVO fvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = faqDao.faqUpdate(fvo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// faq 삭제
	@Override
	public int faqDelete(int faq_no) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = faqDao.faqDelete(faq_no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

}
