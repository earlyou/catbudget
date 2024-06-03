package com.earlyou.catbudget.filectrl;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeleteFileTest {
	
	private static String UPLOAD_DIR = "src\\main\\resources\\static\\img\\";
	
	@Test
	void contextLoads() {
		File file = new File(UPLOAD_DIR + "(0)_");
		
		if (file.delete()) {
			System.out.println("Delete Successful");
		} else {
			System.out.println("Delete Failed");
		}
		
	}
	
}
