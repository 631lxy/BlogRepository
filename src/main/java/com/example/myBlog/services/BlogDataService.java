package com.example.myBlog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.models.Account;
import com.example.myBlog.models.BlogData;
import com.example.myBlog.repositories.BlogDataRepository;

@Service
public class BlogDataService {

	@Autowired
	BlogDataRepository repository;

	// 获得一个人持有的所有博客方法
	public List<BlogData> findBlogsByUser(Account account) {
		List<BlogData> blogs = repository.findAllByAccount(account);
			return blogs;
		}

	
	
	// 创建一篇新博客方法
	public boolean creatBlog(String title, String content, Account account) {
		if (title != null && content != null) {
			repository.save(new BlogData(title, content, account));
			return true;
		} else {
			return false;
		}
	}

	// 删除一篇博客方法
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	//更新某篇博客方法
//	public boolean updateBlogById(Long id) {
//		repository.deleteBlogById(id);
//		repository.save(new BlogData(title, content, account));
//		return true;
//	}
	
	//获取controller里使用的id
	public BlogData findById(Long id) {
		return repository.findById(id).get();
	}
}
