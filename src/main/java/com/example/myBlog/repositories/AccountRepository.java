package com.example.myBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myBlog.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUsername(String username);

	Account save(Account account);

}
