package com.earlyou.catbudget.userinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.UserinfoBiz;
import com.earlyou.catbudget.vo.UserinfoVO;

@SpringBootTest
class SelectTest {
	
	@Autowired
	UserinfoBiz biz;

	@Test
	void contextLoads() {
		UserinfoVO obj = null;
		try {
			obj = biz.get("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(obj);
	}
	
}
