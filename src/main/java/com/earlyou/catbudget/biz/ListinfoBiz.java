package com.earlyou.catbudget.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.catbudget.mapper.ListinfoMapper;
import com.earlyou.catbudget.vo.ListinfoVO;
import com.earlyou.catbudget.vo.PaymentVO;

@Service
public class ListinfoBiz {
	
	@Autowired
	ListinfoMapper dao;
	
	public List<PaymentVO> getbypage(ListinfoVO v) throws Exception {
		return dao.selectbypage(v);
	}
	
	public int getlength(ListinfoVO v) throws Exception {
		return dao.selectlength(v);
	}
	
	public List<PaymentVO> getbydaterange(ListinfoVO v) throws Exception {
		return dao.selectbydaterange(v);
	}
	
	public int getlengthbydaterange(ListinfoVO v) throws Exception {
		return dao.selectlengthbydaterange(v);
	}
}
