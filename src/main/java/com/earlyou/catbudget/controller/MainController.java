package com.earlyou.catbudget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earlyou.catbudget.biz.PaymentBiz;
import com.earlyou.catbudget.biz.UserinfoBiz;
import com.earlyou.catbudget.vo.PaymentVO;
import com.earlyou.catbudget.vo.UserinfoVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	UserinfoBiz ubiz;

	@Autowired
	PaymentBiz pbiz;

	@GetMapping("/")
	public String main(Model m, HttpSession s, RedirectAttributes r, @ModelAttribute("startdate") String startdate,
			@ModelAttribute("enddate") String enddate, @ModelAttribute("ipp") int ipp, @ModelAttribute("sin") int sin,
			@ModelAttribute("listsize") int listsize, @ModelAttribute("lastpage") int lastpage) {

		if (s.getAttribute("uid") == null) {

			// m.addAttribute와 비슷하지만 POST방식이며 1회성이라 새로고침하면 데이터 소멸
			r.addFlashAttribute("v", "n");

			return "redirect:/login";
		} else {
			List<PaymentVO> list = null;

			try {
				String uid = s.getAttribute("uid").toString();
				list = pbiz.getbyuid(uid);
				m.addAttribute("listsize", list.size());

			} catch (Exception e) {
				m.addAttribute("main", "main/main");
				return "index";
			}
		}
		m.addAttribute("main", "main/main");
		return "index";
	}

	@GetMapping("/login")
	public String login(Model m, HttpSession s) {

		if (s.getAttribute("uid") == null) {
			if (m.getAttribute("v") == null) {
				m.addAttribute("v", "n");
			}
		} else {
			return "redirect:/";
		}

		m.addAttribute("main", "auth/login");
		return "index";
	}

	@PostMapping("/loginimpl")
	public String register(Model m, @RequestParam("uid") String uid, @RequestParam("pwd") String pwd, HttpSession s,
			RedirectAttributes r) {

		UserinfoVO userinfo = null;

		try {
			userinfo = ubiz.get(uid);
			if (userinfo != null) {
				if (userinfo.getPwd().equals(pwd) && userinfo.getDel().equals(false)) {
					s.setAttribute("uid", userinfo.getUid());
				} else {
					throw new Exception();
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			r.addFlashAttribute("v", "f");
			return "redirect:/login";
//			return new RedirectView("/login");
		}
		r.addAttribute("tt", "test");
		r.addAttribute("test1", "test1");
//		return new RedirectView("/catbudget");
		return "redirect:/";
	}
}
