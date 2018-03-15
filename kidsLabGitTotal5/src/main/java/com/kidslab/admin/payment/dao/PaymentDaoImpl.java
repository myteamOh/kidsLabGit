package com.kidslab.admin.payment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.payment.vo.PaymentVO;

@Repository
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	private SqlSession session;

	// 결제 목록
	@Override
	public List<PaymentVO> paymentList(PaymentVO pvo) {
		// TODO Auto-generated method stub
		return session.selectList("paymentList", pvo);
	}

	@Override
	public int paymentListCnt(PaymentVO pvo) {
		// TODO Auto-generated method stub
		return (Integer) session.selectOne("paymentListCnt");
	}

	@Override
	public int paymentUpdate(PaymentVO pvo) {
		// TODO Auto-generated method stub
		return (Integer) session.update("paymentUpdate", pvo);
	}

	@Override
	public PaymentVO paymentDetail(PaymentVO pvo) {
		// TODO Auto-generated method stub
		return (PaymentVO) session.selectOne("paymentDetail", pvo);
	}

}
