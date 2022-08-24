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
	@GetMapping("/login")
	public String getLoginPage() {
		return "LoginPage.html";
	}

	@Autowired
	AccountService accountService;

	@PostMapping("/myBlog")
	public ModelAndView login(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
		mav.addObject("username", username);
		mav.addObject("isLogin", false);
		if (username.equals("admin") && password.equals("123456")) {
			mav.setViewName("BlogPage.html");
			return mav;
		} else {
			mav.addObject("isLogin", true);
			mav.setViewName("LoginPage.html");
			return mav;
		}
	}
}
