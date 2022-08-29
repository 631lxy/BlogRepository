package com.example.myBlog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.myBlog.services.AccountService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;

	@GetMapping("/login")
	public String getLoginPage() {
		return "LoginPage.html";
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
		mav.addObject("username", username);
		mav.addObject("passwordNotMatch", false);
		if (accountService.validateAccount(username, password)) {
			mav.setViewName("BlogPage.html");
			return mav;
		} else {
			mav.addObject("passwordNotMatch", true);
			mav.setViewName("LoginPage.html");
			return mav;
		}
	}

	@GetMapping("/register")
	public String GetRegisterPage() {
		return "RegisterPage.html";
	}

	@PostMapping("/register")
	public ModelAndView register(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
		mav.addObject("registerNotSuccess", false);
		if (accountService.createAccount(username, password)) {
			mav.setViewName("LoginPage.html");
		} else {
			mav.addObject("registerNotSuccess", true);
			mav.setViewName("RegisterPage.html");
		}
		return mav;
	}

	@GetMapping("/")
	public String index() {
		return "redirect:/blogPage";
	}
}
