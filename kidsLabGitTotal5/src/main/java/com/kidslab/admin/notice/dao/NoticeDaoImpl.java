package com.kidslab.admin.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.notice.vo.NoticeVO;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	@Autowired
	private SqlSession session;

	// 글 목록
	@Override
	public List<NoticeVO> noticeList(NoticeVO nvo) {
		// TODO Auto-generated method stub
		return session.selectList("noticeList", nvo);
	}

	// 전체 레코드 건수
	@Override
	public int noticeListCnt(NoticeVO nvo) {
		// TODO Auto-generated method stub
		return (Integer) session.selectOne("noticeListCnt");
	}

	// 글 입력
	@Override
	public int noticeInsert(NoticeVO nvo) {
		// TODO Auto-generated method stub
		return session.insert("noticeInsert", nvo);
	}

	// 글 상세보기
	@Override
	public NoticeVO noticeDetail(NoticeVO nvo) {
		// TODO Auto-generated method stub
		return (NoticeVO) session.selectOne("noticeDetail", nvo);
	}

	// 글 수정
	@Override
	public int noticeUpdate(NoticeVO nvo) {
		// TODO Auto-generated method stub
		return session.update("noticeUpdate", nvo);
	}

	@Override
	public int noticeDelete(int notice_no) {
		// TODO Auto-generated method stub
		return session.delete("noticeDelete", notice_no);
	}

	@Override
	public List<NoticeVO> mainNoticeList() {
		// TODO Auto-generated method stub
		return session.selectList("mainNoticeList");
	}

}
