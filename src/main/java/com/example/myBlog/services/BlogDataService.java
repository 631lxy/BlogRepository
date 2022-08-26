package com.example.myBlog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.models.Account;
import com.example.myBlog.models.BlogData;
import com.example.myBlog.repositories.BlogDataRepository;

@Service
public class BlogDataService {

	@Autowired
	BlogDataRepository repository;

	public boolean validateBlog(String title, String content, Account account) {
		BlogData blogs = repository.findByAccount(account);
		if (blogs == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean creatBlog(String title, String content, Account account) {
		if (title != null && content != null) {
			repository.save(new BlogData(title, content, account));
			return true;
		} else {
			return false;
		}
	}
}
