package com.example.myBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogDataController {
	@GetMapping("/myBlog")
	public String getBlogPage() {
		return "BlogPage.html";
	}
}
