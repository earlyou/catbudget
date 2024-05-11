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
public class UserinfoVO {

	// Fields
	private String uid;
	private String pwd;
	private boolean del;
	private Date dd;
	
	
	// Constructors
	/*
	 *  for pwd update
	 */
	public UserinfoVO(String uid, String pwd) {
		super();
		this.uid = uid;
		this.pwd = pwd;
	}

	/*
	 *  for deleting a account
	 */
	public UserinfoVO(String uid, boolean del, Date dd) {
		super();
		this.uid = uid;
		this.del = del;
		this.dd = dd;
	}
}
