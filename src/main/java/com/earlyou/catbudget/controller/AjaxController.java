package com.earlyou.catbudget.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.earlyou.catbudget.biz.ListinfoBiz;
import com.earlyou.catbudget.vo.ListinfoVO;
import com.earlyou.catbudget.vo.PaymentVO;

@RestController
public class AjaxController {

	@Autowired
	ListinfoBiz lbiz;

	@GetMapping("reqlist")
	public Object reqlist(@RequestParam("uid") String uid, @RequestParam("sin") int sin, @RequestParam("ipp") int ipp,
			Model m) {
		List<PaymentVO> list = null;
		ListinfoVO listinfo = new ListinfoVO(uid, sin, ipp);
		try {
			list = lbiz.getbypage(listinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("initreqlistbydate")
	public Object initreqlistbydate(@RequestParam("uid") String uid, @RequestParam("startdate") String startdate,
			@RequestParam("enddate") String enddate, @RequestParam("sin") int sin, @RequestParam("ipp") int ipp,
			Model m) {
		ListinfoVO listinfo = new ListinfoVO(uid, startdate, enddate, sin, ipp);
		ListinfoVO lengthinfo = new ListinfoVO(uid, startdate, enddate);
		List<PaymentVO> list = null;
		int listlength = 0;

		HashMap<String, Object> info = new HashMap<String, Object>();
		HashMap<String, Integer> length = new HashMap<String, Integer>();
		HashMap<String, Object> data = new HashMap<String, Object>();

		try {
			list = lbiz.getbydate(listinfo);
			listlength = lbiz.getlengthbydate(lengthinfo);

			info.put("list", list);
			length.put("length", listlength);
			data.put("info", info);
			data.put("length", length);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	@GetMapping("reqlistbydate")
	public Object reqlistbydate(@RequestParam("uid") String uid, @RequestParam("startdate") String startdate,
			@RequestParam("enddate") String enddate, @RequestParam("sin") int sin, @RequestParam("ipp") int ipp,
			Model m) {
		ListinfoVO listinfo = new ListinfoVO(uid, startdate, enddate, sin, ipp);
		List<PaymentVO> list = null;

		try {
			list = lbiz.getbydate(listinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
