package com.kidslab.client.parentjoin.service;

import javax.servlet.http.HttpSession;

import com.kidslab.client.parent.vo.ParentVO;

public interface ParentJoinService {

	public ParentVO parentSelect(String parentId);

	public int parentIdConfirm(String userId);

	public String sendMail(HttpSession session, String from, String email);

	public int numberCheck(HttpSession session, String email, String certifNum);

	public int parentInsert(ParentVO pvo);

	public boolean parentUpdate(ParentVO pvo);
	
	public int parentWithdraw(ParentVO pvo);

}
