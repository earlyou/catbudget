package com.earlyou.catbudget.payment;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.PaymentBiz;
import com.earlyou.catbudget.vo.PaymentVO;

@SpringBootTest
class SelectByDateTest {

	@Autowired
	PaymentBiz biz;

	@Test
	void contextLoads() {
		String uid = "admin";
		String regdate = "2024-05-01";
		PaymentVO c = new PaymentVO(uid, regdate);
		try {
			List<PaymentVO> l = biz.getbydate(c);
			if (!l.isEmpty()) {
				System.out.println(l);
				System.out.println(l.size());
			} else {
				System.out.println(l.size());		// 0
				System.out.println("list is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
