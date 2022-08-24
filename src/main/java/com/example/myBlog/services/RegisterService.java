package com.example.myBlog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.models.Account;
import com.example.myBlog.repositories.AccountRepository;

@Service
public class RegisterService {
	@Autowired
	AccountRepository repository;

	public boolean createAccount(String username, String password) {
		if (repository.findByUsername(username) == null) {
			repository.save(new Account(username, password));
			return true;
		} else {
			return false;
		}
	}

}
