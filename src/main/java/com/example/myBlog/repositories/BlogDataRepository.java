package com.example.myBlog.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myBlog.models.BlogData;

public interface BlogDataRepository extends JpaRepository<BlogData, Long> {

}
