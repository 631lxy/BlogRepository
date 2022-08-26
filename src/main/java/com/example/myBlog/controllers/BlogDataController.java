package com.example.myBlog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.myBlog.models.Account;
import com.example.myBlog.services.AccountService;
import com.example.myBlog.services.BlogDataService;

@Controller
public class BlogDataController {
	@Autowired
	BlogDataService blogDataService;
	@Autowired
	AccountService accountService;

	@GetMapping("/addBlog")
	public String getAddBlogPage() {
		return "AddBlogPage.html";
	}

	@PostMapping("/addBlog")
	public ModelAndView addBlog(@RequestParam String title, @RequestParam String content, @RequestParam String username,
			ModelAndView mav) {
		Account account = accountService.findByUsername(username);
		if (blogDataService.creatBlog(title, content, account)) {
			mav.setViewName("BlogPage.html");
			return mav;
		} else {
			mav.setViewName("AddBlogPage.html");
			return mav;
		}
	}
}
