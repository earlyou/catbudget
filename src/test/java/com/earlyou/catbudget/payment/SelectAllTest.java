package com.earlyou.catbudget.payment;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.PaymentBiz;
import com.earlyou.catbudget.vo.PaymentVO;

@SpringBootTest
class SelectAllTest {
	
	@Autowired
	PaymentBiz biz;

	@Test
	void contextLoads() {
		List<PaymentVO> list = null;
		try {
			list = biz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (PaymentVO obj : list) {
			System.out.println(obj);
		}
	}
	
}
