package com.kidslab.admin.notice.service;

import java.util.List;

import com.kidslab.admin.notice.vo.NoticeVO;

public interface NoticeService {
	// 전체 공지사항 목록 구현
	public List<NoticeVO> noticeList(NoticeVO nvo);

	// 메인 공지사항 목록 구현
	public List<NoticeVO> mainNoticeList();

	// 페이징 추가
	public int noticeListCnt(NoticeVO nvo);

	// 공지사항 등록
	public int noticeInsert(NoticeVO nvo);

	// 공지사항 상세보기
	public NoticeVO noticeDetail(NoticeVO nvo);

	// 공지사항 수정
	public int noticeUpdate(NoticeVO nvo);

	// 공지사항 삭제
	public int noticeDelete(int notice_no);
}
