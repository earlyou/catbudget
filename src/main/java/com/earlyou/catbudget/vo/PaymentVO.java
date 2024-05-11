package com.earlyou.catbudget.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentVO {
	
	// Fields
	private int num;
	private String uid;
	private Date regdate;
	private int seq;
	private String detail;
	private int price;
	private String pic;
	private String memo;
	private Boolean del;
	private Date dd;
}
