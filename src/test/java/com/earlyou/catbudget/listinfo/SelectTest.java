package com.earlyou.catbudget.listinfo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.ListinfoBiz;
import com.earlyou.catbudget.vo.ListinfoVO;
import com.earlyou.catbudget.vo.PaymentVO;

@SpringBootTest
class SelectTest {
	
	@Autowired
	ListinfoBiz lbiz;

	@Test
	void contextLoads() {
		List<PaymentVO> list = null;
		ListinfoVO obj = new ListinfoVO("admin", 0, 10);
		try {
			list = lbiz.getbypage(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(list);
		
	}
	
}
