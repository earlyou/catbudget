package com.earlyou.catbudget.controller;

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

}
