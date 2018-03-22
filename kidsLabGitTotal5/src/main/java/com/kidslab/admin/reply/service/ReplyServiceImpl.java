package com.kidslab.admin.reply.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.reply.dao.ReplyDao;
import com.kidslab.admin.reply.vo.ReplyVO;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

	Logger logger = Logger.getLogger(ReplyServiceImpl.class);

	@Autowired
	private ReplyDao replyDao;

	// 댓글 목록 구현
	@Override
	public List<ReplyVO> replyList(Integer inquiry_no) {
		// TODO Auto-generated method stub
		List<ReplyVO> replyList = null;
		replyList = replyDao.replyList(inquiry_no);

		return replyList;
	}

	// 댓글 입력
	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = replyDao.replyInsert(rvo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// 댓글 수정
	@Override
	public int replyUpdate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			result = replyDao.replyUpdate(rvo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// 댓글 삭제
	@Override
	public int replyDelete(int inquiry_reply_no) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = replyDao.replyDelete(inquiry_reply_no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

}
