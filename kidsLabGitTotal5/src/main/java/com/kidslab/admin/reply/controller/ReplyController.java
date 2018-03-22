package com.kidslab.admin.reply.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kidslab.admin.reply.service.ReplyService;
import com.kidslab.admin.reply.vo.ReplyVO;
import com.kidslab.client.inquiry.service.InquiryService;

@RestController
@RequestMapping(value = "/admin/replies")
public class ReplyController {

	Logger logger = Logger.getLogger(ReplyController.class);

	@Autowired
	private ReplyService replyService;

	@Autowired
	private InquiryService inquiryService;

	/*************************************************************
	 * 댓글 글목록 구현하기
	 * 
	 * @return List<ReplyVO> 참고 : @PathVariable는 URI의 경로에서 원하는 데이터를 추출하는 용도의 어노테이션.
	 *         현재 요청 URL : http://localhost:8080/replies/all/게시판글번호.do 예전 요청 URL :
	 *         http://localhost:8080/replies/replyList.do?b_num=게시판글번호
	 *************************************************************/
	@RequestMapping(value = "/all/{inquiry_no}.do", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("inquiry_no") Integer inquiry_no) {

		ResponseEntity<List<ReplyVO>> entity = null;

		try {
			entity = new ResponseEntity<>(replyService.replyList(inquiry_no), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;

	}

	/***************************
	 * 댓글 글쓰기 구현
	 ***************************/
	@RequestMapping(value = "/replyInsert")
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO rvo) {

		logger.info("replyInsert 호출 성공");
		ResponseEntity<String> entity = null;
		int result;
		try {
			result = replyService.replyInsert(rvo);

			if (result == 1) {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	/*******************
	 * 댓글 수정 구현하기
	 * 
	 * @return 참고 : REST 방식에서 UPDATE 작업은 PUT, PATCH방식을 이용해서 처리. 전체 데이터를 수정하는 경우에는
	 *         PUT을 이용하고, 일부의 데이터를 수정하는 경우에는 PATCH를 이용.
	 *******************/
	@RequestMapping(value = "/{inquiry_reply_no}.do", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> replyUpdate(@PathVariable("inquiry_reply_no") Integer inquiry_reply_no,
			@RequestBody ReplyVO rvo) {

		logger.info("replyUpdate 호출");
		ResponseEntity<String> entity = null;

		try {
			rvo.setInquiry_reply_no(inquiry_reply_no);
			replyService.replyUpdate(rvo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/*******************
	 * 댓글 삭제 구현하기
	 * 
	 * @return 참고 : REST 방식에서 DELETE 작업은 DELETE방식을 이용해서 처리.
	 *******************/

	public ResponseEntity<String> replyDelete(@PathVariable("inquiry_reply_no") Integer inquiry_reply_no) {

		logger.info("replyDelete 호출 성공");
		ResponseEntity<String> entity = null;

		try {
			replyService.replyDelete(inquiry_reply_no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
