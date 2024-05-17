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
public class ListinfoVO {

	// Fields
	private String uid;
	private String startdate;
	private String enddate;
	private int sin;		// start item number
	private int ipp;		// items per page
	
	// Constructors
	/**
	 * for getbypage
	 */
	public ListinfoVO(String uid, int sin, int ipp) {
		super();
		this.uid = uid;
		this.sin = sin;
		this.ipp = ipp;
	}
}
