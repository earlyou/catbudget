package com.earlyou.catbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.earlyou.catbudget.biz.UserinfoBiz;
import com.earlyou.catbudget.vo.UserinfoVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	UserinfoBiz userinfobiz;

	@GetMapping("/")
	public String main(Model m, HttpSession session) {
		if (session.getAttribute("loginuser") == null) {
			m.addAttribute("main", "auth/login");
		} else {
			m.addAttribute("main", "main/main");
			m.addAttribute("session", session.getAttribute("loginuser"));
		}
		m.addAttribute("navbar", "navbar");
		return "index";
	}

	@GetMapping("/login")
	public String login(Model m, @RequestParam("msg") String msg) {
		if (msg != null && msg.equals("f")) {
			m.addAttribute("msg", "회원정보를 확인해주세요");
		}
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "auth/login");
		return "index";
	}

	@PostMapping("/loginimpl")
	public String register(Model m, @RequestParam("uid") String uid, @RequestParam("pwd") String pwd,
			HttpSession session) {
		UserinfoVO user = null;

		try {
			user = userinfobiz.get(uid);
			if (user != null) {
				if (user.getPwd().equals(pwd)) {
					session.setAttribute("loginuser", user);
					m.addAttribute("loginuser", user);
				} else {
					throw new Exception();
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			return "redirect:/?msg=f";
		}
		return "redirect:/";
	}
}
