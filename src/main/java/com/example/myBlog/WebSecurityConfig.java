package com.example.myBlog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.myBlog.services.AccountService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests()
				.antMatchers("/register","/CSS/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Autowired
	AccountService accountService;
	
	// Account的列表
	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> resultList = new ArrayList<>();
		for (String username : accountService.userIterator().keySet()) {
			UserDetails user = User.withDefaultPasswordEncoder().username(username)
					.password(accountService.userIterator().get(username)).roles("USER")
					.build();
			resultList.add(user);
			}
			return new InMemoryUserDetailsManager(resultList);
		}
}
