package com.earlyou.catbudget.userinfo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.catbudget.biz.UserinfoBiz;
import com.earlyou.catbudget.vo.UserinfoVO;

@SpringBootTest
class SelectAllTest {
	
	@Autowired
	UserinfoBiz biz;

	@Test
	void contextLoads() {
		List<UserinfoVO> list = null;
		try {
			list = biz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (UserinfoVO obj : list) {
			System.out.println(obj);
		}
	}
	
}
