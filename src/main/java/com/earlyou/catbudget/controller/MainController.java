package com.earlyou.catbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earlyou.catbudget.biz.UserinfoBiz;
import com.earlyou.catbudget.vo.UserinfoVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	UserinfoBiz userinfobiz;

	@GetMapping("/")
	public String main(Model m, HttpSession s, RedirectAttributes r) {
		if (s.getAttribute("session") == null) {
			
			// m.addAttribute와 비슷하지만 POST방식이며 1회성이라 새로고침하면 데이터 소멸
			r.addFlashAttribute("v", "n");
			
			return "redirect:/login";
		} else {
			System.out.println(s.getAttribute("session"));
			m.addAttribute("main", "main/main");
		}
		return "index";
	}

	@GetMapping("/login")
	public String login(Model m, HttpSession s) {
		
		if (s.getAttribute("loginuser") == null) {
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
	public String register(Model m, @RequestParam("uid") String uid, @RequestParam("pwd") String pwd,
			HttpSession s, RedirectAttributes r) {
		
		UserinfoVO user = null;

		try {
			user = userinfobiz.get(uid);
			if (user != null) {
				if (user.getPwd().equals(pwd) && user.getDel().equals(false)) {
					s.setAttribute("session", user);
				} else {
					throw new Exception();
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			r.addFlashAttribute("v", "f");
			return "redirect:/login";
		}
		return "redirect:/";
	}
}
