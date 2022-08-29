package com.example.myBlog.services;

import java.util.HashMap;
import java.util.Map;

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

	public Account findByUsername(String username) {
		return repository.findByUsername(username);
	}

	//用户名和密码添加到HashMap
	public Map<String, String> userIterator() {
		Map<String, String> resultMap = new HashMap<>();
		for (Account element : repository.findAll()) {
			resultMap.put(element.getUsername(), element.getPassword());
		}
		return resultMap;
	}
}