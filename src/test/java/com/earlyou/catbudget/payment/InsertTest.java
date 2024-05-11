package com.earlyou.catbudget.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.PaymentBiz;
import com.earlyou.catbudget.vo.PaymentVO;

@SpringBootTest
class InsertTest {
	
	@Autowired
	PaymentBiz biz;
	
	@Test
	void contextLoads() {
		PaymentVO obj = new PaymentVO("admin", "2024-05-12", 0, "갈비찜", 160000, "C\\user\\early\\test.jpg", "");
		try {
			biz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
