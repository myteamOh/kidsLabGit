package com.kidslab.admin.notice.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.notice.dao.NoticeDao;
import com.kidslab.admin.notice.vo.NoticeVO;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	Logger logger = Logger.getLogger(NoticeServiceImpl.class);

	@Autowired
	private NoticeDao noticeDao;

	// 공지사항 목록
	@Override
	public List<NoticeVO> noticeList(NoticeVO nvo) {
		// TODO Auto-generated method stub

		List<NoticeVO> noticeList = null;

		noticeList = noticeDao.noticeList(nvo);
		return noticeList;
	}

	// 전체 레코드 수
	@Override
	public int noticeListCnt(NoticeVO nvo) {
		// TODO Auto-generated method stub
		return noticeDao.noticeListCnt(nvo);
	}

	// 글 입력
	@Override
	public int noticeInsert(NoticeVO nvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = noticeDao.noticeInsert(nvo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// 글 상세
	@Override
	public NoticeVO noticeDetail(NoticeVO nvo) {
		// TODO Auto-generated method stub
		NoticeVO detail = null;
		detail = noticeDao.noticeDetail(nvo);
		return detail;
	}

	// 글 수정
	@Override
	public int noticeUpdate(NoticeVO nvo) {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			result = noticeDao.noticeUpdate(nvo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// 글 삭제
	@Override
	public int noticeDelete(int notice_no) {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			result = noticeDao.noticeDelete(notice_no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

}
