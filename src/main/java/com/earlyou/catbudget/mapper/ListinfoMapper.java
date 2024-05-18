package com.earlyou.catbudget.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.catbudget.vo.ListinfoVO;
import com.earlyou.catbudget.vo.PaymentVO;

@Repository
@Mapper
public interface ListinfoMapper {
	public List<PaymentVO> selectbypage(ListinfoVO obj) throws Exception;
	public List<PaymentVO> selectbydate(ListinfoVO obj) throws Exception;
	public int selectlength(ListinfoVO obj) throws Exception;
}
