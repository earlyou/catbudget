package com.earlyou.catbudget.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.catbudget.frame.Biz;
import com.earlyou.catbudget.mapper.PaymentMapper;
import com.earlyou.catbudget.vo.PaymentVO;

@Service
public class PaymentBiz implements Biz<Integer, PaymentVO> {

	@Autowired
	PaymentMapper dao;
	
	@Override
	public void register(PaymentVO v) throws Exception {
		dao.insert(v);;
	}

	@Override
	public void modify(PaymentVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public PaymentVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<PaymentVO> get() throws Exception {
		return dao.selectall();
	}

}
