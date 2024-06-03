package com.earlyou.catbudget.filectrl;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RenameFileTest {
	
	private static String UPLOAD_DIR = "src\\main\\resources\\static\\img\\";
	
	@Test
	void contextLoads() {
		File file = new File(UPLOAD_DIR + "(0)_스케치1.png");
		
		File newFile = new File(UPLOAD_DIR + "RenameTest.png");
		
		boolean result = file.renameTo(newFile);
		
		System.out.println(result);
	}
	
}
