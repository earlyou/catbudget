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
public class UserinfoVO {

	// Fields
	private String uid;
	private String pwd;
	private Boolean del;
	private String dd;

	// Constructors
	/*
	 * for pwd update
	 */
	public UserinfoVO(String uid, String pwd) {
		super();
		this.uid = uid;
		this.pwd = pwd;
	}

	/*
	 * for deleting an account
	 */
	public UserinfoVO(String uid, boolean del, String dd) {
		super();
		this.uid = uid;
		this.del = del;
		this.dd = dd;
	}
}
