package com.earlyou.catbudget.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.PaymentBiz;
import com.earlyou.catbudget.vo.PaymentVO;

@SpringBootTest
class SelectTest {
	
	@Autowired
	PaymentBiz biz;

	@Test
	void contextLoads() {
		PaymentVO obj = null;
		try {
			obj = biz.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(obj);
	}
	
}
