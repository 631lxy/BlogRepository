package com.example.myBlog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myBlog.models.Account;
import com.example.myBlog.models.BlogData;

@Repository
public interface BlogDataRepository extends JpaRepository<BlogData, Long> {
	// 根据account查找
	List<BlogData> findAllByAccount(Account account);

	// 保存一篇博客
	BlogData save(BlogData blog);

	// 删除一篇博客
	void deleteById(Long id);
}
