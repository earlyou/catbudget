package com.earlyou.catbudget.vo;

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
	private String regdate;
	private int seq;
	private String detail;
	private int price;
	private String pic;
	private String memo;
	private Boolean del;
	private String dd;
	
	// Constructors
	/**
	 * for Insert
	 */
	public PaymentVO(String uid, String regdate, int seq, String detail, int price, String pic, String memo) {
		super();
		this.uid = uid;
		this.regdate = regdate;
		this.seq = seq;
		this.detail = detail;
		this.price = price;
		this.pic = pic;
		this.memo = memo;
	}

	/** 
	 * for selectbydate
	 * @param uid
	 * @param regdate
	 */
	public PaymentVO(String uid, String regdate) {
		super();
		this.uid = uid;
		this.regdate = regdate;
	}

	/**
	 * for update
	 * @param num
	 * @param uid
	 * @param regdate
	 * @param seq
	 * @param detail
	 * @param price
	 * @param pic
	 * @param memo
	 */
	public PaymentVO(int num, String uid, String regdate, int seq, String detail, int price, String pic, String memo) {
		super();
		this.num = num;
		this.uid = uid;
		this.regdate = regdate;
		this.seq = seq;
		this.detail = detail;
		this.price = price;
		this.pic = pic;
		this.memo = memo;
	}
	
	
	
}
