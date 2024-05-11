package com.earlyou.catbudget.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.PaymentBiz;

@SpringBootTest
class DeleteTest {

	@Autowired
	PaymentBiz biz;
	
	@Test
	void contextLoads() {
		try {
			biz.remove(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
