package com.earlyou.catbudget.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.catbudget.vo.PaymentVO;

@Repository
@Mapper
public interface PaymentMapper {
	public void insert(PaymentVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(PaymentVO obj) throws Exception;
	public PaymentVO select(int obj) throws Exception;
	public List<PaymentVO> selectall() throws Exception;
	
	public List<PaymentVO> selectbyuid(String obj) throws Exception;
	public List<PaymentVO> selectbydate(PaymentVO obj) throws Exception;
}
