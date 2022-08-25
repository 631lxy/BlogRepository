package com.example.myBlog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.models.BlogData;
import com.example.myBlog.repositories.BlogDataRepository;

@Service
public class BlogDataService {
	
		@Autowired
		BlogDataRepository repository;

		public boolean validateBlogData(String title, String content) {
		return true;
		}
		public boolean createBlog(String title, String content) {
				repository.save(new BlogData(title, content));
				return true;
			
		}	
	}

