package com.kidslab.client.parentjoin.service;

public interface ParentJoinService {

	public int parentIdConfirm(String parentId);

	public void sendMail(String subject, String content, String from, String email);

}
