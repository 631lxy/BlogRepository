package com.example.myBlog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.myBlog.models.Account;
import com.example.myBlog.models.BlogData;
import com.example.myBlog.services.AccountService;
import com.example.myBlog.services.BlogDataService;

@Controller
public class BlogDataController {
	@Autowired
	BlogDataService blogDataService;
	@Autowired
	AccountService accountService;

	// 列表形式展示
	@GetMapping("/blogPage")
	public ModelAndView getBlogs(ModelAndView mav, @AuthenticationPrincipal UserDetails user) {
		Account account = accountService.findByUsername(user.getUsername());
		List<BlogData> blogs = blogDataService.findBlogsByUser(account);
		mav.addObject("blogs", blogs);
		mav.setViewName("BlogPage.html");
		return mav;
	}

	// 进入添加博客页面
	@GetMapping("/addBlogPage")
	public String getAddBlogPage() {
		return "AddBlogPage.html";
	}

	// 追加博客请求
	@PostMapping("/addBlogPage")
	public ModelAndView addBlog(@RequestParam String title, @RequestParam String content,
			@AuthenticationPrincipal UserDetails user, ModelAndView mav) {
		Account account = accountService.findByUsername(user.getUsername());
		if (blogDataService.creatBlog(title, content, account)) {
			mav.setViewName("BlogPage.html");
			List<BlogData> blogs = blogDataService.findBlogsByUser(account);
			mav.addObject("blogs", blogs);
			return mav;
		} else {
			mav.setViewName("AddBlogPage.html");
			return mav;
		}
	}

	// 进入该博客展开页面（查看内容）
	@PostMapping("/searchBlogPage")
	public ModelAndView exhibitBlog(ModelAndView mav, @RequestParam Long id) {
		BlogData blog = blogDataService.findById(id);
		mav.addObject("blog", blog);
		mav.setViewName("SearchBlogPage.html");
		return mav;
	}

	// 删除博客请求
	@PostMapping("/deleteBlogPage")
	public ModelAndView deleteBlog(ModelAndView mav, @RequestParam Long id, @AuthenticationPrincipal UserDetails user) {
		blogDataService.findById(id);
		blogDataService.deleteById(id);
		//列表形式展示
		Account account = accountService.findByUsername(user.getUsername());
		List<BlogData> blogs = blogDataService.findBlogsByUser(account);
		mav.addObject("blogs", blogs);
		mav.setViewName("BlogPage.html");
		return mav;
	}

	//进入编辑博客页面
	@GetMapping("/editBlogPage")
	public ModelAndView getEditBlogPage(@RequestParam Long id, ModelAndView mav) {
		BlogData blog = blogDataService.findById(id);
		mav.addObject("blog", blog);
		mav.setViewName("EditBlogPage.html");
		return mav;
	}

	// 修改（编辑）博客请求
	@PostMapping("/editBlogPage")
	public ModelAndView editBlog(@RequestParam String title, @RequestParam String content, @RequestParam Long id,
			@AuthenticationPrincipal UserDetails user, ModelAndView mav) {
		Account account = accountService.findByUsername(user.getUsername());
		BlogData blog = blogDataService.findById(id);
		blogDataService.updateBlogById(id, title, content, account);
		mav.addObject("blog", blog);
		List<BlogData> blogs = blogDataService.findBlogsByUser(account);
		mav.addObject("blogs", blogs);
		mav.setViewName("BlogPage.html");
		return mav;
	}
}
