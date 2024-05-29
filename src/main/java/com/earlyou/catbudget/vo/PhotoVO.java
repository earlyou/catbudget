package com.earlyou.catbudget.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoVO {
	// Fields
	private String uploadFilename;   // 업로드 파일명
	private String storeFilename;    // 서버 내부 관리하는 파일명
}
