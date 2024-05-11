package com.earlyou.catbudget.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.PaymentBiz;
import com.earlyou.catbudget.vo.PaymentVO;

@SpringBootTest
class UpdateTest {

	@Autowired
	PaymentBiz biz;

	@Test
	void contextLoads() {
		PaymentVO o = null;
		try {
			o = biz.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PaymentVO obj = new PaymentVO(o.getNum(), o.getUid(), o.getRegdate(), o.getSeq(), o.getDetail(), o.getPrice(),
				o.getPic(), "update test", o.getDel(), o.getDd());

		try {
			biz.modify(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
