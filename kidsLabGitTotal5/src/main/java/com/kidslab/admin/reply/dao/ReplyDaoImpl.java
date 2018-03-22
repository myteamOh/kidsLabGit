package com.kidslab.admin.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.reply.vo.ReplyVO;

@Repository
public class ReplyDaoImpl implements ReplyDao {

	@Autowired
	private SqlSession session;

	// 댓글 목록 구현
	@Override
	public List<ReplyVO> replyList(Integer inquiry_no) {
		// TODO Auto-generated method stub
		return session.selectList("replyList", inquiry_no);
	}

	// 댓글 작성
	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		return session.insert("replyInsert", rvo);
	}

	// 글 수정
	@Override
	public int replyUpdate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		return session.update("replyUpdate", rvo);
	}

	// 글 삭제
	@Override
	public int replyDelete(int inquiry_reply_no) {
		// TODO Auto-generated method stub
		return session.update("replyDelete", inquiry_reply_no);
	}

}
