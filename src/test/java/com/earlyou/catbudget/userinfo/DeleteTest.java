package com.earlyou.catbudget.userinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.UserinfoBiz;

@SpringBootTest
class DeleteTest {

	@Autowired
	UserinfoBiz biz;
	
	@Test
	void contextLoads() {
		try {
			biz.remove("testID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
