package com.kidslab.admin.reply.dao;

import java.util.List;

import com.kidslab.admin.reply.vo.ReplyVO;

public interface ReplyDao {
	// 댓글 리스트
	public List<ReplyVO> replyList(Integer inquiry_no);

	// 댓글 작성
	public int replyInsert(ReplyVO rvo);

	// 댓글 수정
	public int replyUpdate(ReplyVO rvo);

	// 댓글 삭제
	public int replyDelete(int inquiry_reply_no);
}
