package com.example.myBlog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.models.Account;
import com.example.myBlog.repositories.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository repository;

	public boolean validateAccount(String username, String password) {
		Account account = repository.findByUsername(username);
		if (account == null) {
			return false;
		} else {
			if (account.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean createAccount(String username, String password) {
		if (repository.findByUsername(username) == null) {
			repository.save(new Account(username, password));
			return true;
		} else {
			return false;
		}
	}
}
