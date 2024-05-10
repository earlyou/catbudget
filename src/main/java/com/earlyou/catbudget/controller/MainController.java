package com.earlyou.catbudget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "main/main");
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "auth/login");
		return "index";
	}
	
	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "auth/register");
		return "index";
	}
	
	@GetMapping("/forgot")
	public String forgot(Model m) {
		m.addAttribute("sidebar", "sidebar");
		m.addAttribute("main", "auth/forgot");
		return "index";
	}
}
