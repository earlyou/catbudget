package com.earlyou.catbudget.userinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.UserinfoBiz;
import com.earlyou.catbudget.vo.UserinfoVO;

@SpringBootTest
class InsertTest {
	@Autowired
	UserinfoBiz biz;
	
	@Test
	void contextLoads() {
		UserinfoVO obj = new UserinfoVO("testID", "testPWD");
		try {
			biz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
