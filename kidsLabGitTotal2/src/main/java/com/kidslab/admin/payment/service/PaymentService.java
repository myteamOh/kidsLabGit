package com.kidslab.admin.payment.service;

import java.util.List;

import com.kidslab.admin.payment.vo.PaymentVO;

public interface PaymentService {

	// 결제 목록 구현
	public List<PaymentVO> paymentList(PaymentVO pvo);

	// 페이징 추가
	public int paymentListCnt(PaymentVO pvo);

	// 결제상태 수정
	public int paymentUpdate(PaymentVO pvo);

}
