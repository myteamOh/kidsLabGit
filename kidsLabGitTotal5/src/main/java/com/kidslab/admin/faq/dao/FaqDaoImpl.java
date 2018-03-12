package com.kidslab.admin.faq.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.faq.vo.FaqVO;

@Repository
public class FaqDaoImpl implements FaqDao {

	@Autowired
	private SqlSession session;

	// Faq 목록
	@Override
	public List<FaqVO> faqList(FaqVO fvo) {
		// TODO Auto-generated method stub
		return session.selectList("faqList", fvo);
	}

	// 전체 레코드 건수
	@Override
	public int faqListCnt(FaqVO fvo) {
		// TODO Auto-generated method stub
		return (Integer) session.selectOne("faqListCnt");
	}

	// faq 입력
	@Override
	public int faqInsert(FaqVO fvo) {
		// TODO Auto-generated method stub
		return session.insert("sqlInsert", fvo);
	}

	// faq 상세보기
	@Override
	public FaqVO faqDetail(FaqVO fvo) {
		// TODO Auto-generated method stub
		return (FaqVO) session.selectOne("faqDetail", fvo);
	}

	// faq 수정
	@Override
	public int faqUpdate(FaqVO fvo) {
		// TODO Auto-generated method stub
		return session.update("faqUpdate", fvo);
	}

	// faq 삭제
	@Override
	public int faqDelete(int faq_no) {
		// TODO Auto-generated method stub
		return session.delete("faqDelete", faq_no);
	}

}
