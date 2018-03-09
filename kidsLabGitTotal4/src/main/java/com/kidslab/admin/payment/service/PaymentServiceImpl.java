package com.kidslab.admin.payment.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.payment.dao.PaymentDao;
import com.kidslab.admin.payment.vo.PaymentVO;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	Logger logger = Logger.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentDao paymentDao;

	// 결제 목록
	@Override
	public List<PaymentVO> paymentList(PaymentVO pvo) {
		// TODO Auto-generated method stub

		List<PaymentVO> paymentList = null;

		// 정렬에 대한 기본값 설정 보류

		paymentList = paymentDao.paymentList(pvo);

		return paymentList;
	}

	// 전체 레코드 수 구현
	@Override
	public int paymentListCnt(PaymentVO pvo) {
		// TODO Auto-generated method stub
		return paymentDao.paymentListCnt(pvo);
	}

	// 결제상태 수정
	@Override
	public int paymentUpdate(PaymentVO pvo) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = paymentDao.paymentUpdate(pvo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

}
