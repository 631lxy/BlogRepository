package com.example.myBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myBlog.models.Account;
import com.example.myBlog.models.BlogData;

@Repository
public interface BlogDataRepository extends JpaRepository<BlogData, Long> {

	BlogData findByAccount(Account account);

}
