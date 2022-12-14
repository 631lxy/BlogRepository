package com.example.myBlog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myBlog.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByUsername(String username);
	Account save(Account account);
	
	List<Account>findAll();
}
